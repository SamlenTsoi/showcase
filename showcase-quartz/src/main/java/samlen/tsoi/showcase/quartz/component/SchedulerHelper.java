package samlen.tsoi.showcase.quartz.component;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.entity.dto.JobConfig;

/**
 * 任务操作类
 *
 * @author samlen_tsoi
 * @date 2017/12/4
 */
@Slf4j
@Component
public class SchedulerHelper {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建任务
     * @param jobConfig
     * @return
     */
    public Boolean createScheduler(JobConfig jobConfig) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(jobConfig.getJobData());
        JobDetail job = JobBuilder.newJob(jobConfig.getJobClass())
                .withIdentity(jobConfig.getId(), jobConfig.getGroupName())
                .withDescription(jobConfig.toString())
                .usingJobData(jobDataMap)
                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobConfig.getCronTime());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobConfig.getId(), jobConfig.getGroupName()) .withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(job,cronTrigger);
            log.info("任务创建成功：{} : {}", jobConfig.getGroupName(), jobConfig.getId());
            return Boolean.TRUE;
        } catch (SchedulerException e) {
            log.info("任务创建失败：{} : {}, cause : {}", jobConfig.getGroupName(), jobConfig.getId(), Throwables.getStackTraceAsString(e));
            return Boolean.FALSE;
        }
    }

    /**
     * 删除任务
     * @param jobConfig
     * @return
     */
    public Boolean deleteScheduler(JobConfig jobConfig) {
        TriggerKey triggerKey = new TriggerKey(jobConfig.getId(), jobConfig.getGroupName());
        try {
            Trigger keyTrigger = scheduler.getTrigger(triggerKey);
            if (keyTrigger != null) {
                scheduler.unscheduleJob(triggerKey);
                log.info("任务删除成功：{} : {}", jobConfig.getGroupName(), jobConfig.getId());
                return Boolean.TRUE;
            } else {
                log.info("任务不存在 ：{} : {}", jobConfig.getGroupName(), jobConfig.getGroupName());
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            log.info("任务删除失败：{} : {}, cause {}", jobConfig.getGroupName(), jobConfig.getId(), Throwables.getStackTraceAsString(e));
            return Boolean.FALSE;
        }
    }

    /**
     * 更新任务
     * @param jobConfig
     * @return
     */
    public Boolean updateScheduler(JobConfig jobConfig) {
        Boolean deleteScheduler = deleteScheduler(jobConfig);
        if (!deleteScheduler) {
            log.info("任务更新失败：{} : {}", jobConfig.getGroupName(), jobConfig.getId());
            return Boolean.FALSE;
        }
        Boolean createScheduler = createScheduler(jobConfig);
        if (!createScheduler) {
            log.info("任务更新失败：{} : {}", jobConfig.getGroupName(), jobConfig.getId());
            return Boolean.FALSE;
        }
        log.info("任务更新成功：{} : {}", jobConfig.getGroupName(), jobConfig.getId());
        return Boolean.TRUE;
    }
}
