package samlen.tsoi.showcase.quartz.job;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.entity.po.User;
import samlen.tsoi.showcase.service.UserReadService;

import java.util.concurrent.TimeUnit;

/**
 * 用户任务
 *
 * @author samlen_tsoi
 * @date 2018/3/7
 */
@Slf4j
@Component
public class UserJob implements Job {

    @Autowired
    private UserReadService userReadService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("用户定时任务开始");
        Stopwatch stopwatch = Stopwatch.createStarted();
        PageInfo<User> userPageInfo = userReadService.page(1, 10);
        userPageInfo.getList().forEach(user -> log.info("user : {}", user));
        stopwatch.stop();
        log.info("用户定时任务结束，共花费 ：{}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
