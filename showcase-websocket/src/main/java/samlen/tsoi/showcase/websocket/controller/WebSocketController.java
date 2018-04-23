package samlen.tsoi.showcase.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.websocket.entity.ClientMessage;
import samlen.tsoi.showcase.websocket.entity.ServerMessage;

/**
 * WebSocket Controller
 *
 * @author samlen_tsoi
 * @date 2018/4/22
 */
@Slf4j
@RestController
public class WebSocketController {

    @MessageMapping("/sendTest")
    @SendTo("/topic/subscribeTest")
    public ServerMessage sendDemo(ClientMessage message) {
        log.info("接收到了信息" + message.getName());
        return new ServerMessage("你发送的消息为:" + message.getName());
    }

    @SubscribeMapping("/subscribeTest")
    public ServerMessage sub() {
        log.info("XXX用户订阅了我。。。");
        return new ServerMessage("感谢你订阅了我。。。");
    }

}
