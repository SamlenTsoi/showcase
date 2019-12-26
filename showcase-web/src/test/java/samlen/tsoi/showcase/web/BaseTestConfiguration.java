package samlen.tsoi.showcase.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 测试配置类
 *
 * @author samlen_tsoi
 * @date 2018-03-20
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@MapperScan("samlen.tsoi.showcase.web.mapper")
@ComponentScan(basePackages = {"samlen.tsoi.showcase"})
public class BaseTestConfiguration {
}
