package samlen.tsoi.showcase.cloud.provider.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 *
 * @author samlen_tsoi
 * @date 2018/9/10
 */
@Slf4j
@Configuration
@EnableEurekaClient
@EnableFeignClients(value = {"samlen.tsoi.showcase.cloud"})
public class WebConfiguration implements WebMvcConfigurer {
}
