package samlen.tsoi.showcase.mqtt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttSubscribedEvent;

/**
 * mqtt订阅成功Listener
 *
 * @author wxw
 * @date 2017-11-22 15:27
 */
@Slf4j
public class MqttSubscribedListener implements ApplicationListener<MqttSubscribedEvent> {
    @Override
    public void onApplicationEvent(MqttSubscribedEvent mqttSubscribedEvent) {
        log.info("MqttSubscribedListener:"+mqttSubscribedEvent.getMessage().toString());
    }
}
