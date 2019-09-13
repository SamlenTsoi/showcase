package samlen.tsoi.showcase.mqtt.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author samlen_tsoi
 * @date 2019/2/2
 */
@AllArgsConstructor
public enum MqttTopicEnum {
    TEST(UUID.randomUUID().toString(), "test");

    @Getter
    @Setter
    private String client;

    @Getter
    @Setter
    private String topic;
}
