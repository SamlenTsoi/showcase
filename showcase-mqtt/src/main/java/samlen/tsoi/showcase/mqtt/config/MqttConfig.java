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
     **/
    private String[] serverURIs;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 密码
     **/
    private String password;

    /**
     * MQTT Qos
     **/
    private Integer qos;

    /**
     * 心跳
     **/
    private Integer keepAliveInterval = 2;

    /**
     * 连接超时
     **/
    private Integer connectionTimeout = 15;

    /**
     * 重连
     **/
    private Integer recoveryInterval = 30000;

    /**
     * 连接完成时间
     **/
    private Integer completionTimeout = 15000;
}
