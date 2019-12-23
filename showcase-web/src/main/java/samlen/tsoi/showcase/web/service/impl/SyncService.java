package samlen.tsoi.showcase.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import samlen.tsoi.showcase.web.service.ISyncService;

/**
 * @author samlen_tsoi
 * @date 2019/12/23
 **/
@Slf4j
@Service
public class SyncService implements ISyncService {
    @Override
    public void async() throws InterruptedException {
        log.info("异步开始, {}", Thread.currentThread().getId());
        Thread.sleep(10000);
        log.info("异步结束, {}", Thread.currentThread().getId());
    }
}
