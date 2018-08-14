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

    private Integer count = 0;

    /**
     * 同步到kafka
     *
     * @param topic 主题
     * @param mes 消息内容
     * @return
     */
    @GetMapping("syncKafka")
    public Result syncKafka(@RequestParam("topic") String topic,
                            @RequestParam("mes") String mes) {
        kafkaTemplate.send(topic, mes);
        return Result.ok();
    }

    /**
     * 这里使用正则匹配topic，其中【*】之前得加上【.】才能匹配到。
     *
     * @param record
     */
    @KafkaListener(topicPattern = "showcase.*")
    public void listenPattern(ConsumerRecord<String, String> record) {
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            log.info("topic : {}, mes : {}", record.topic(), kafkaMessage.get());
        }
    }

    /**
     * 指定单个topic
     *
     * @param record
     */
    @KafkaListener(topicPattern = "storm-kafka")
    public void listenOne(ConsumerRecord<String, String> record) {
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            String keyword = "关键词";
            if (kafkaMessage.get().contains(keyword)) {
                count += 1;
                log.info("次数:{}", count);
            }
        }
    }
}
