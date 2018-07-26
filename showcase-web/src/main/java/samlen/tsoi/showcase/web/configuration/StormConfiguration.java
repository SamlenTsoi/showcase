package samlen.tsoi.showcase.web.configuration;

import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author samlen_tsoi
 * @date 2018/7/26
 */
@Configuration
public class StormConfiguration {

    @Bean
    public KafkaSpout<String, String> kafkaSpout() {
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<>(KafkaSpoutConfig.builder("127.0.0.1:" + "9092", "show-web").build());
        return kafkaSpout;
    }
}
