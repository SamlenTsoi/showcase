package samlen.tsoi.showcase.redis.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import samlen.tsoi.showcase.redis.BaseTest;
import samlen.tsoi.showcase.redis.entity.RedisDTO;

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

    @Test
    public void putObject() {
        RedisDTO redisDTO = new RedisDTO();
        redisDTO.setName("samlen_tsoi");
        redisDTO.setAge(25);
        redisService.hashPut("redis", "dto", JSON.toJSONString(redisDTO));
    }

    @Test
    public void getObject() {
        RedisDTO redisDTO = redisService.hashGet("redis", "dto", RedisDTO.class);
        log.info("redisDTO -> {}", JSON.toJSONString(redisDTO));
    }
}
