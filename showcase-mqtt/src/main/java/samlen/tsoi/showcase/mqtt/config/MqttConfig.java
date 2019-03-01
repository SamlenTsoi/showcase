package samlen.tsoi.showcase.mqtt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * EMQ配置信息
 *
 * @author wxw
 * @date 2017-11-20 16:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {
    /**
     * mqtt地址
     */
    private String serverURIs;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 密码
     **/
    private String password;

    /**
     * 订阅通道客户端ID
     */
    private String subClientId;

    /**
     * 订阅主题名
     */
    private String[] subTopic;

    /**
     * 发布通道客户端ID
     **/
    private String pubClientId;

    /**
     * 发布主题名
     **/
    private String pubTopic;

    /**
     * MQTT Qos
     **/
    private Integer qos;

    /**
     * 心跳
     **/
    private Integer keepAliveInterval = 15;

    /**
     * 连接超时
     **/
    private Integer connectionTimeout = 10;

    /**
     * 重连
     **/
    private Integer recoveryInterval = 5000;
}
