package samlen.tsoi.showcase.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samlent.tsoi.showcase.core.dto.Result;

/**
 * @author samlen_tsoi
 * @date 2019/12/30
 **/
@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {

    @GetMapping("get")
    @Cacheable(value = "samlen:tsoi", key = "#id")
    public Result cache(Integer id) {
        log.info("id : {}", id);
        return Result.ok(id);
    }
}
