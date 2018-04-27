package samlen.tsoi.showcase.quartz.job;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

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

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("用户定时任务开始");
        Stopwatch stopwatch = Stopwatch.createStarted();
        //任务执行具体内容忽略
        //。。。
        stopwatch.stop();
        log.info("用户定时任务结束，共花费 ：{}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
