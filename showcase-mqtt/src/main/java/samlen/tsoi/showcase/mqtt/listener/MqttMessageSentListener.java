package samlen.tsoi.showcase.mqtt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttMessageSentEvent;

/**
 * mqtt消息发送Listener
 *
 * @author samlen_tsoi
 * @date 2019/2/2
 */
@Slf4j
public class MqttMessageSentListener implements ApplicationListener<MqttMessageSentEvent> {

    @Override
    public void onApplicationEvent(MqttMessageSentEvent event) {
        log.info("MqttMessageSentEvent-> {}", event);
    }
}
