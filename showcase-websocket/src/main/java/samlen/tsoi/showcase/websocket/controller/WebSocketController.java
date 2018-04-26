package samlen.tsoi.showcase.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.redis.constant.RedisConstant;
import samlen.tsoi.showcase.redis.service.RedisService;

import java.util.Date;
import java.util.Set;

/**
 * WebSocket Controller
 *
 * @author samlen_tsoi
 * @date 2018/4/22
 */
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
