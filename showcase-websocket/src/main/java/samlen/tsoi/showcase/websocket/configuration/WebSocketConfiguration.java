package samlen.tsoi.showcase.websocket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;
import samlen.tsoi.showcase.websocket.handler.WebSocketErrorHandler;
import samlen.tsoi.showcase.websocket.listener.WebSocketDisconnectListener;
import samlen.tsoi.showcase.websocket.listener.WebsocketConnectListener;

/**
 * websocket配置类
 *
 * @author samlen_tsoi
 * @date 2018/4/16
 */
@EnableScheduling
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan("samlen.tsoi.showcase")
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
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
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
}
