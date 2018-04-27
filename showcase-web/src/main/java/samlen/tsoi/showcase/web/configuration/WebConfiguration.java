package samlen.tsoi.showcase.web.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author samlen_tsoi
 * @date 2017/12/1
 */
@EnableWebMvc
@Configuration
@ComponentScan("samlen.tsoi.showcase")
@MapperScan("samlen.tsoi.showcase.web.dao")
public class WebConfiguration {
}
