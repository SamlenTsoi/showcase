package samlen.tsoi.showcase.quartz.configuration;

import org.quartz.JobDetail;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import samlen.tsoi.showcase.quartz.job.QuartzJob;

/**
 * @author samlen_tsoi
 * @date 2018/12/10
 */
@Configuration
@AutoConfigureBefore(QuartzConfiguration.class)
public class QuartJobConfiguration {
    @Bean
    public JobDetailFactoryBean quartzJobDetail() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setDurability(true);
        jobDetail.setRequestsRecovery(true);
        jobDetail.setJobClass(QuartzJob.class);
        return jobDetail;
    }

    @Bean
    public CronTriggerFactoryBean QuartzJobTrigger(JobDetail quartzJobDetail) {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(quartzJobDetail);
        //每分钟执行一次
        triggerFactoryBean.setCronExpression("0 * * * * ?");
        return triggerFactoryBean;
    }
}
