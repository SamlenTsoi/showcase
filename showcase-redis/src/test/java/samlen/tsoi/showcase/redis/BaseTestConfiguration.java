package samlen.tsoi.showcase.redis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 测试配置类
 *
 * @author LeeJohn
 * @date 2018-03-20
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"samlen.tsoi.showcase.redis"})
public class BaseTestConfiguration {
}
