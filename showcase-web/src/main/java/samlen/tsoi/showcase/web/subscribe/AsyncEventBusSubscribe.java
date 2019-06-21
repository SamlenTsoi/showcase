package samlen.tsoi.showcase.web.subscribe;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.web.entity.dto.AsyncEventBusDTO;

import javax.annotation.PostConstruct;

/**
 * @author samlen_tsoi
 * @date 2018/12/7
 */
@Slf4j
@Component
public class AsyncEventBusSubscribe {
    @Autowired
    private AsyncEventBus asyncEventBus;

    @PostConstruct
    public void register() {
        asyncEventBus.register(this);
    }

    @Subscribe
//    @AllowConcurrentEvents
    public void subscribe(AsyncEventBusDTO asyncEventBusDTO) throws InterruptedException {
        log.info("异步eventBus，线程{}睡眠60秒", Thread.currentThread().getId());
        Thread.sleep(60 * 1000);
        log.info("异步eventBus，线程{}睡眠结束", Thread.currentThread().getId());
    }
 }
