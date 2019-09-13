package samlen.tsoi.showcase.mqtt.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * 测试处理
 *
 * @author samlen_tsoi
 * @date 2019/2/2
 */
@Slf4j
@Component
public class TestHandler implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("接受到的mq消息 -> {}", message);
    }
}
