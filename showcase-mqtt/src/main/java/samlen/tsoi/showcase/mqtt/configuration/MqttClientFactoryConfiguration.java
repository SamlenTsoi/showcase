package samlen.tsoi.showcase.mqtt.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.ConsumerStopAction;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import samlen.tsoi.showcase.mqtt.config.MqttConfig;

/**
 * MQTT工厂配置
 *
 * @author samlen_tsoi
 * @date 2017-12-22 22:30
 */
@Slf4j
@Configuration
public class MqttClientFactoryConfiguration {
    @Autowired
    private MqttConfig mqttConfig;

    /**
     * 工厂配置
     *
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setKeepAliveInterval(mqttConfig.getKeepAliveInterval());
        factory.setConnectionTimeout(mqttConfig.getConnectionTimeout());
        factory.setServerURIs(mqttConfig.getServerURIs());
        factory.setConsumerStopAction(ConsumerStopAction.UNSUBSCRIBE_NEVER);
        factory.setCleanSession(Boolean.FALSE);
        factory.setUserName(mqttConfig.getUsername());
        factory.setPassword(mqttConfig.getPassword());
        return factory;
    }
}
