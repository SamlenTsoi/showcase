package samlen.tsoi.showcase.mqtt.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.ErrorHandler;
import samlen.tsoi.showcase.mqtt.config.MqttConfig;
import samlen.tsoi.showcase.mqtt.handler.MqttErrorHandler;
import samlen.tsoi.showcase.mqtt.handler.TestHandler;

/**
 * MQTT配置信息
 *
 * @author wxw
 * @date 2017-11-20 16:12
 */
@Slf4j
@Configuration
@IntegrationComponentScan
public class MqttConfiguration {

    @Autowired
    private MqttConfig mqttConfig;

    @Autowired
    private TestHandler testhandler;

    @Autowired
    private MqttErrorHandler mqttErrorHandler;

    @Autowired
    private MqttPahoClientFactory mqttPahoClientFactory;

    @Autowired
    private DefaultPahoMessageConverter defaultPahoMessageConverter;

    @Bean
    public MessageProducer wxWillProducer() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(MqttTopicEnum.WX_WILL.getClientId(), mqttPahoClientFactory, MqttTopicEnum.WX_WILL.getTopic());
        adapter.setConverter(defaultPahoMessageConverter);
        adapter.setQos(mqttConfig.getQos());
        adapter.setRecoveryInterval(mqttConfig.getRecoveryInterval());
        adapter.setCompletionTimeout(mqttConfig.getCompletionTimeout());
        adapter.setOutputChannel(testMqttInputChannel());
        adapter.setErrorChannel(mqttErrorChannel());
        return adapter;
    }

    @Bean
    public MessageChannel testMqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "testMqttInputChannel")
    public MessageHandler testInHandler() {
        return testhandler;
    }

    @Bean
    public MessageChannel mqttErrorChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttErrorChannel")
    public ErrorHandler errorMessageHandler() {
        return mqttErrorHandler;
    }
}
