package samlen.tsoi.showcase.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlen.tsoi.showcase.web.service.ISyncService;
import samlent.tsoi.showcase.core.dto.Result;

/**
 * @author samlen_tsoi
 * @date 2019/12/23
 **/
@Slf4j
@RestController
@RequestMapping("sync")
public class SyncController {
    @Autowired
    private ISyncService syncService;

    @GetMapping("test")
    public Result test() throws InterruptedException {
        log.info("请求过来, {}", Thread.currentThread().getId());
        syncService.async();
        return Result.ok();
    }
}
