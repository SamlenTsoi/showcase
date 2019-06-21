package samlen.tsoi.showcase.web.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author samlen_tsoi
 * @date 2017/12/1
 */
@Configuration
@ComponentScan("samlen.tsoi.showcase")
@MapperScan("samlen.tsoi.showcase.web.dao")
public class WebConfiguration extends WebMvcAutoConfiguration {
    @Autowired
    private ThreadPoolTaskExecutor executorService;

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

    @Bean
    public AsyncEventBus asyncEventBus() {
        return new AsyncEventBus(executorService);
    }
}
