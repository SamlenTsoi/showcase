package samlen.tsoi.showcase.cloud.consumer.configuration;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * web配置
 *
 * @author samlen_tsoi
 * @date 2018/9/10
 */
@Configuration
@EnableEurekaClient
@EnableFeignClients(value = {"samlen.tsoi.showcase.cloud"})
public class WebConfiguration {
}
