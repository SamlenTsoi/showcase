package samlen.tsoi.showcase.web.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 *
 * @author wxw
 * @date 2017-12-22 22:25
 */
@Slf4j
@Configuration
public class ThreadPoolConfiguration {
    @Autowired
    private ThreadPoolConfig poolConfig;

    /**
     * 自定义ThreadExecutor
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor executorService() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolConfig.getCorePoolSize());
        executor.setMaxPoolSize(poolConfig.getMaxPoolSize());
        executor.setQueueCapacity(poolConfig.getQueueCapacity());
        executor.setRejectedExecutionHandler(rejectedExecutionHandler());
        return executor;
    }

    /**
     * 自定义消息抛弃策略
     *
     * @return
     */
    @Bean
    public RejectedExecutionHandler rejectedExecutionHandler() {

        RejectedExecutionHandler rejectedExecutionHandler = (runnable, executor) -> {
            if (!executor.isShutdown()) {
                try {
                    BlockingQueue<Runnable> queue = executor.getQueue();

                    if (!queue.offer(runnable, poolConfig.getMaxWait(), TimeUnit.MILLISECONDS)) {
                        log.error("Max wait time expired to queue task");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RejectedExecutionException("Interrupted", e);
                }
            } else {
                throw new RejectedExecutionException("Executor has been shut down");
            }
        };

        return rejectedExecutionHandler;
    }
}
