package samlen.tsoi.showcase.websocket.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.websocket.controller.WebSocketController;

/**
 * @author samlen_tsoi
 * @date 2018/4/26
 */
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
