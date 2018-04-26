package samlen.tsoi.showcase.websocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import samlen.tsoi.showcase.redis.constant.RedisConstant;
import samlen.tsoi.showcase.redis.service.RedisService;

/**
 * Websocket断开连接监听器
 *
 * @author samlen_tsoi
 * @date 2018/4/26
 */
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
