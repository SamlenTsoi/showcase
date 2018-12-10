package samlen.tsoi.showcase.web.subscribe;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.web.entity.dto.EventBusDTO;

import javax.annotation.PostConstruct;

/**
 * @author samlen_tsoi
 * @date 2018/12/7
 */
@Slf4j
@Component
public class EventBusSubscribe {
    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void register() {
        eventBus.register(this);
    }

    @Subscribe
    public void subscribe(EventBusDTO eventBusDTO) throws InterruptedException {
        log.info("同步eventBus，线程{}睡眠10秒", Thread.currentThread().getId());
        Thread.sleep(10 * 1000);
        log.info("同步eventBus，线程{}睡眠结束", Thread.currentThread().getId());
    }
 }
