# 背景
类似聊天室，当服务端收到信息时，主动推送信息到对应的用户（点到点），不再是使用客户端轮询服务端来获取新信息。

# 整合
使用`springboot`框架，`maven`构建，`idea`开发。

## 依赖
`springboot`：`1.5.8.RELEASE`
```xml
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

## 关键类
`WebSocketConfiguration`：websocket连接基本配置
```java
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp的端点
     *
     * @param stompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        // 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在网页上我们就可以通过这个链接
        // http://localhost:8080/webSocketServer
        // 来和服务器的WebSocket连接
        stompEndpointRegistry.addEndpoint("/showcase/ws").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置信息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 订阅Broker名称
        registry.enableSimpleBroker("/getMsg");
        // 全局使用的消息前缀（客户端订阅路径上会体现出来）
//        registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认是/user/
         registry.setUserDestinationPrefix("/showcase/ws");
    }

    /**
     * WebSocket 连接
     *
     * @return
     */
    @Bean
    public WebsocketConnectListener websocketConnectListener() {
        return new WebsocketConnectListener();
    }

    /**
     * WebSocket 断开连接
     *
     * @return
     */
    @Bean
    public WebSocketDisconnectListener webSocketDisconnectListener() {
        return new WebSocketDisconnectListener();
    }

    /**
     * Websocket Error处理
     *
     * @return
     */
    @Bean
    public StompSubProtocolErrorHandler webSocketHandler() {
        return new WebSocketErrorHandler();
    }
`WebsocketConnectListener`：当websocket连接成功时，把用户(`userId`)与会话(sessionId)的关联关系保存（此处放在redis，使用mysql等也可以），但有新信息时可以通过`userId`来拿到相关的`sessionId`，推送新信息到相关会话中。
```java
@Slf4j
@Component
public class WebsocketConnectListener implements ApplicationListener<SessionConnectEvent> {

    @Autowired
    private RedisService redisService;

    private final String USER_ID = "userId";

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        log.info("WebsocketConnectedListener:"+event.getMessage());
        //获取Session连接信息
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //用户ID
        String userId = sha.getFirstNativeHeader(USER_ID);
        //获取SessionId
        String sessionId = sha.getSessionId();
        //userId -> sessionId
        redisService.setAdd(RedisConstant.WS_USER_TO_SESSION_PRE + userId, sessionId);
        //sessionId -> userId
        redisService.strSet(RedisConstant.WS_SESSION_TO_USER_PRE + sessionId, userId);
    }
}
```
`WebsocketConnectListener`：当会话结束时，清除缓存上的用户与会话关联关系，避免信息的无效推送。
```java
@Slf4j
@Component
public class WebSocketDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    private RedisService redisService;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        log.debug("WebSocketDisconnectListener:"+event.getMessage());
        //获取Session连接信息
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //获取SessionId
        String sessionId = sha.getSessionId();
        String userId = redisService.strGet(RedisConstant.WS_SESSION_TO_USER_PRE + sessionId);
        redisService.setRemove(RedisConstant.WS_USER_TO_SESSION_PRE + userId, sessionId);
        redisService.delete(RedisConstant.WS_SESSION_TO_USER_PRE + sessionId);
    }
}
```
## 前端客户端
```html
    <script th:src="@{/assets/js/jquery.min.js}" src="./js/jquery.min.js"></script>
    <script th:src="@{/assets/js/sockjs.min.js}" src="./js/sockjs.min.js"></script>
    <script th:src="@{/assets/js/stomp.min.js}" src="./js/stomp.min.js"></script>
    <script>
        $(function() {
            alert("连接websocket");
            function init(){
                let stompClient = null;
                var socket = new SockJS('http://localhost:9003/showcase/ws');
                stompClient = Stomp.over(socket);
                stompClient.connect({
                    userId: 1
                }, function (frame) {
                    stompClient.subscribe('/showcase/ws/getMsg', function (greeting) {
                        // alert(greeting);
                    });
                });
            }
            init();
        });
    </script>
```
其中`sockjs.min.js`、`stomp.min.js`为关键js，必须存在。连接的时候会传`userId`到服务端用于表示客户端，具体的值可自定义。

# 测试
浏览器上打开客户端，经测试，本地调试只能用**火狐**，打开调试模式下的控制台,点击确定
![](https://upload-images.jianshu.io/upload_images/3712304-9299d6495d13c74c.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
链接成功后，服务端会把userId与sessionId保存到redis，再起个定时器，每10秒推送当前时间到浏览器即客户端。
`WebsocketTask`：定时任务
```java
@Slf4j
@Component
public class WebsocketTask {

    @Autowired
    private WebSocketController webSocketController;

    @Scheduled(cron = "0/10 * * * * ?")
    public void sendMsg() {
        webSocketController.sendToClient(1L);
    }
}
```
`WebSocketController`：发送消息
```
@Slf4j
@RestController
public class WebSocketController {

   @Autowired
   private RedisService redisService;

   @Autowired
   private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 发送消息到客户端
     *
     * @param userId
     */
   public void sendToClient(Long userId) {
       Set<String> sessionList = redisService.setMembers(RedisConstant.WS_USER_TO_SESSION_PRE + userId);
       sessionList.forEach(sessionId -> {
           log.info("sessionId : {}", sessionId);
           simpMessagingTemplate.convertAndSendToUser(sessionId,"/getMsg",
                   "当前时间为：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
                    createHeaders(sessionId));
       });
   }

    /**
     * 设置消息头
     *
     * @param sessionId
     * @return
     */
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

}
```
此时浏览器的控制台每隔10s就会显示出服务端推过来的消息。
![](https://upload-images.jianshu.io/upload_images/3712304-2128a074eff79818.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 结论
服务端成功推送信息到客户端。

# Demo源码
[showcase](https://github.com/SamlenTsoi/showcase.git)中的`showcase-websocket`。

