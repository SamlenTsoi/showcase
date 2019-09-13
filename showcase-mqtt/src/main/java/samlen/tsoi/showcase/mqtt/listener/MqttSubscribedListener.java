package samlen.tsoi.showcase.mqtt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttSubscribedEvent;

/**
 * mqtt订阅成功Listener
 *
 * @author samlen_tsoi
 * @date 2019/2/2
 */
@Slf4j
public class MqttSubscribedListener implements ApplicationListener<MqttSubscribedEvent> {

    @Override
    public void onApplicationEvent(MqttSubscribedEvent event) {
        log.info("MqttSubscribedEvent-> {}", event);
    }
}
