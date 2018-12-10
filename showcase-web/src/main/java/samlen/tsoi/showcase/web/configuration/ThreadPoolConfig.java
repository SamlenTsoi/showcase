package samlen.tsoi.showcase.web.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ThreadPool配置信息
 *
 * @author wxw
 * @date 2017-11-20 16:12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "threadPool")
public class ThreadPoolConfig {
    /**
     * 等待时间
     */
    private long maxWait = 3000;

    /**
     * 初始化池大小
     */
    private int corePoolSize = 500;

    /**
     * 最大池大小
     */
    private int maxPoolSize = 5000;

    /**
     * 阻塞队列大小
     */
    private int queueCapacity = 5000;
}
