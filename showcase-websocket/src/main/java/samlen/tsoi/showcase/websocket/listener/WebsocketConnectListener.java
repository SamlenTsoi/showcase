package samlen.tsoi.showcase.websocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import samlen.tsoi.showcase.redis.constant.RedisConstant;
import samlen.tsoi.showcase.redis.service.RedisService;

/**
 * websocket连接监听
 *
 * @author samlen_tsoi
 * @date 2018/4/26
 */
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
