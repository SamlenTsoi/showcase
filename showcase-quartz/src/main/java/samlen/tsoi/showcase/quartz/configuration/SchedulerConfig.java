package samlen.tsoi.showcase.quartz.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @author samlen_tsoi
 */
@Slf4j
@Configuration
@SuppressWarnings("ALL")
public class SchedulerConfig {

    @Bean
    public Properties properties() throws IOException {
        Properties prop = new Properties();
        prop.load(new ClassPathResource("/quartz.properties").getInputStream());
        return prop;
    }

    @Autowired
    private SpringJobFactory springJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //是否覆盖就任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //延迟多久启动
        schedulerFactoryBean.setStartupDelay(10);
        //设置基本的配置
        schedulerFactoryBean.setQuartzProperties(properties());
        //是否自动启动
        schedulerFactoryBean.setAutoStartup(Boolean.TRUE);
        //必须设置，具体的任务实例才能交给spring管理
        schedulerFactoryBean.setJobFactory(springJobFactory);
        return schedulerFactoryBean;
    }
}
