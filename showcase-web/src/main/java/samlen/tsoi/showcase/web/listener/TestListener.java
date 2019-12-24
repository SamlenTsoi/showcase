package samlen.tsoi.showcase.web.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.web.event.TestEvent;

/**
 * 测试监听器
 *
 * @author samlen_tsoi
 * @date 2019/12/24
 **/
@Slf4j
@Component
public class TestListener {
    /**
     * 同步监听
     *
     * @param testEvent 事件
     */
    @EventListener
    public void sync(TestEvent testEvent) {
        log.info("同步 -> {}", JSON.toJSONString(testEvent));
    }

    /**
     * 异步监听
     *
     * @param testEvent 事件
     */
    @Async
    @EventListener
    public void async(TestEvent testEvent) {
        log.info("异步 -> {}", JSON.toJSONString(testEvent));
    }
}
