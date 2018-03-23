package samlen.tsoi.showcase.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.common.pojo.dto.Result;

import java.util.Optional;

/**
 * @author samlen_tsoi
 * @date 2018/3/23
 */
@Slf4j
@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("syncKafka")
    public Result syncKafka(@RequestParam("topic") String topic,
                            @RequestParam("mes") String mes) {
        kafkaTemplate.send(topic, mes);
        return Result.ok();
    }

    @KafkaListener(topicPattern = "showcase.*")
    public void listen(ConsumerRecord<String, String> record) {
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            log.info("topic : {}, mes : {}", record.topic(), kafkaMessage.get());
        }
    }

}
