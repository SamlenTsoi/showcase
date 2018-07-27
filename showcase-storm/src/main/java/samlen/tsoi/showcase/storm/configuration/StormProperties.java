package samlen.tsoi.showcase.storm.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * storm配置
 *
 * @author samlen_tsoi
 * @date 2018/7/27
 */
@Data
@ConfigurationProperties(prefix = "storm")
public class StormProperties {

    /**
     * kafka地址
     */
    private String kafkaHost;

    /**
     * kafka主题
     */
    private String[] kafkaTopics;

    /**
     * mqtt地址
     */
    private String mqttHost;

    /**
     * mqtt账号
     */
    private String mqttUsername;

    /**
     * mqtt密码
     */
    private String mqttPassword;

    /**
     * mqtt主题
     */
    private String[] mqttTopics;
}
