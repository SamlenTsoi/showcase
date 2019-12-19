package samlen.tsoi.showcase.web;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author samlen_tsoi
 * @date 2019/12/19
 **/
@Slf4j
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(10));
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        log.info("Thread : {}", Thread.currentThread().getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        log.info("结束线程 ： {}", Thread.currentThread().getId());
    }
}
