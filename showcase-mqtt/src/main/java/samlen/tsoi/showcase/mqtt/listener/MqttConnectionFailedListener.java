package samlen.tsoi.showcase.mqtt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;

/**
 * mqtt连接失败Listener
 *
 * @author samlen_tsoi
 * @date 2019/2/2
 */
@Slf4j
public class MqttConnectionFailedListener implements ApplicationListener<MqttConnectionFailedEvent> {

    @Override
    public void onApplicationEvent(MqttConnectionFailedEvent event) {
        log.error("MqttConnectionFailedEvent-> {}", event);
    }
}
