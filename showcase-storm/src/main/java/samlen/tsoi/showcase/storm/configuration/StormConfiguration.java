package samlen.tsoi.showcase.storm.configuration;

import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.mqtt.common.MqttOptions;
import org.apache.storm.mqtt.mappers.StringMessageMapper;
import org.apache.storm.mqtt.spout.MqttSpout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * storm配置类
 *
 * @author samlen_tsoi
 * @date 2018/7/26
 */
@Configuration
@EnableConfigurationProperties(StormProperties.class)
public class StormConfiguration {

    @Autowired
    private StormProperties stormProperties;

    @Bean
    public KafkaSpout<String, String> kafkaSpout() {
        //参数一：kafka地址，参数二：订阅的topic
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<>(KafkaSpoutConfig.builder(stormProperties.getKafkaHost(), stormProperties.getKafkaTopics()).build());
        return kafkaSpout;
    }

    @Bean
    public MqttSpout mqttSpout() {
        //mqtt配置
        MqttOptions options = new MqttOptions();
        //订阅的topic
        options.setTopics(Arrays.asList(stormProperties.getMqttTopics()));
        //mqtt密码
        options.setPassword(stormProperties.getMqttPassword());
        //mqtt账号
        options.setUserName(stormProperties.getMqttUsername());
        //mqtt地址
        options.setUrl(stormProperties.getMqttHost());
        options.setCleanConnection(false);
        MqttSpout mqttSpout = new MqttSpout(new StringMessageMapper(), options);
        return mqttSpout;
    }
}
