package samlen.tsoi.showcase.mqtt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttIntegrationEvent;

/**
 * mqtt整合事件Listener
 *
 * @author samlen_tsoi
 * @date 2019/2/2
 */
@Slf4j
public class MqttIntegrationListener implements ApplicationListener<MqttIntegrationEvent> {

    @Override
    public void onApplicationEvent(MqttIntegrationEvent event) {
        log.info("MqttIntegrationEvent-> {}", event);
    }
}
