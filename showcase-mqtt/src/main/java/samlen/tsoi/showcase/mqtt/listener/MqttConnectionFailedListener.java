package samlen.tsoi.showcase.mqtt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;

/**
 * mqtt连接失败Listener
 *
 * @author wxw
 * @date 2017-11-22 15:33
 */
@Slf4j
public class MqttConnectionFailedListener implements ApplicationListener<MqttConnectionFailedEvent> {
    @Override
    public void onApplicationEvent(MqttConnectionFailedEvent mqttConnectionFailedEvent) {
        log.error("MqttConnectionFailed:"+mqttConnectionFailedEvent.getCause());
    }
}
