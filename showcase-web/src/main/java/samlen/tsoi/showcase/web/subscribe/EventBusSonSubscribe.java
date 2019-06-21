package samlen.tsoi.showcase.web.subscribe;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import samlen.tsoi.showcase.web.entity.dto.EventBusDTO;
import samlen.tsoi.showcase.web.entity.dto.EventBusSonDTO;

import javax.annotation.PostConstruct;

/**
 * @author samlen_tsoi
 * @date 2018/12/7
 */
@Slf4j
@Component
public class EventBusSonSubscribe {
    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void register() {
        eventBus.register(this);
    }

    @Subscribe
    public void subscribe(EventBusSonDTO eventBusSonDTO) throws InterruptedException {
        log.info("son");
    }
 }
