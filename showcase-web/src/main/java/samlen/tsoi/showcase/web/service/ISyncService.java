package samlen.tsoi.showcase.web.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author samlen_tsoi
 * @date 2019/12/23
 **/
public interface ISyncService {
    /**
     * 异步调用
     */
    @Async
    void async() throws InterruptedException;
}
