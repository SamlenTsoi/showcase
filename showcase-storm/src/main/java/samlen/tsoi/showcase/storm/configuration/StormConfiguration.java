package samlen.tsoi.showcase.storm.configuration;

import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * storm配置类
 *
 * @author samlen_tsoi
 * @date 2018/7/26
 */
@Configuration
public class StormConfiguration {

    @Bean
    public KafkaSpout<String, String> kafkaSpout() {
        //参数一：kafka地址，参数二：订阅的topic
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<>(KafkaSpoutConfig.builder("127.0.0.1:9092", "storm-kafka").build());
        return kafkaSpout;
    }
}
