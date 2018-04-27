package samlen.tsoi.showcase.quartz.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.quartz.component.SchedulerHelper;
import samlen.tsoi.showcase.quartz.entity.dto.JobConfig;
import samlen.tsoi.showcase.quartz.job.UserJob;

/**
 * 用户任务Controller
 *
 * @author samlen_tsoi
 * @date 2017/12/15
 */
@Slf4j
@RestController
@RequestMapping("userJob")
public class UserJobController {

    @Autowired
    private SchedulerHelper schedulerHelper;

    @PostMapping("add")
    public void createJob(@RequestParam("cron") String cron,
                          @RequestParam("id") String id,
                          @RequestParam("group") String group,
                          @RequestParam("extra") String extra) {
        JobConfig jobConfig = new JobConfig();
        jobConfig.setCronTime(cron);
        jobConfig.setGroupName(group);
        jobConfig.setId(id);
        jobConfig.setJobData(ImmutableMap.of("extra", extra));
        jobConfig.setJobClass(UserJob.class);
        schedulerHelper.createScheduler(jobConfig);
    }

    @PostMapping("delete")
    public void deleteJob(@RequestParam("id") String id,
                          @RequestParam("group") String group) {
        JobConfig jobConfig = new JobConfig();
        jobConfig.setId(id);
        jobConfig.setGroupName(group);
        schedulerHelper.deleteScheduler(jobConfig);
    }

    @PostMapping("update")
    public void updateJob(@RequestParam("cron") String cron,
                          @RequestParam("id") String id,
                          @RequestParam("group") String group,
                          @RequestParam("extra") String extra) {
        JobConfig jobConfig = new JobConfig();
        jobConfig.setCronTime(cron);
        jobConfig.setGroupName(group);
        jobConfig.setId(id);
        jobConfig.setJobData(ImmutableMap.of("extra", extra));
        jobConfig.setJobClass(UserJob.class);
        schedulerHelper.updateScheduler(jobConfig);
    }
}
