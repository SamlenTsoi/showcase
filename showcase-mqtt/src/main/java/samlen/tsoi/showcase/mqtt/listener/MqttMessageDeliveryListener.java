package samlen.tsoi.showcase.mqtt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttMessageDeliveryEvent;

/**
 * mqtt消息送达Listener
 *
 * @author samlen_tsoi
 * @date 2019/2/2
 */
@Slf4j
public class MqttMessageDeliveryListener implements ApplicationListener<MqttMessageDeliveryEvent> {

    @Override
    public void onApplicationEvent(MqttMessageDeliveryEvent event) {
        log.info("MqttMessageDeliveryEvent-> {}", event);
    }
}
