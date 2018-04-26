package samlen.tsoi.showcase.redis.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import samlen.tsoi.showcase.redis.BaseTest;
import samlen.tsoi.showcase.redis.service.RedisService;

/**
 * @author samlen_tsoi
 * @date 2018/4/26
 */
@Slf4j
public class RedisServiceTest extends BaseTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void putStr() {
        redisService.hashPut("redis", "samlen", "tsoi");
    }
}
