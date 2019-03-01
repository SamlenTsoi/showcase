package samlen.tsoi.showcase.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import samlen.tsoi.showcase.redis.service.RedisService;

/**
 * @author samlen_tsoi
 * @date 2019-03-01 17:12
 **/
@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    /**
     * 加锁成功标志
     */
    private static final String LOCK_SUCCESS = "OK";

    /**
     * 不存在则设置属性
     */
    private static final String SET_IF_NOT_EXIST = "NX";

    /**
     * 设置过期时间属性
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 存在隐患的加锁方式
     *
     * @param lockKey 加锁字段
     * @param expireTime 过期时间
     * @return
     */
    @PostMapping("fakeLock")
    public Boolean fakeLock(@RequestParam("lockKey") String lockKey, @RequestParam("expireTime") Long expireTime){
        Long expires = System.currentTimeMillis() + expireTime;
        // 对应setnx命令
        if(redisService.strSetIfAbsent(lockKey, expires.toString())){
            // 可以成功设置,也就是key不存在
            return Boolean.TRUE;
        }
        // 判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentValue = redisService.strGet(lockKey);
        log.info("currentValue -> {}", currentValue);
        // 如果锁过期，currentValue不为空且小于当前时间
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            // 获取上一个锁的时间value，并且设置新时间
            // 隐患 ：1. 由于是客户端自己生成过期时间，所以需要强制要求分布式下每个客户端的时间必须同步。
            // 2. 当锁过期的时候，如果多个客户端同时执行jedis.getSet()方法，那么虽然最终只有一个客户端可以加锁，但是这个客户端的锁的过期时间可能被其他客户端覆盖。
            // 3. 锁不具备拥有者标识，即任何客户端都可以解锁。
            String oldValue =redisService.strGetAndSet(lockKey, expires.toString());
            log.info("oldValue -> {}", oldValue);
            // 假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentValue=A(get取的旧的值肯定是一样的),两个线程的value都是B,key都是K.锁时间已经过期了。
            // 而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的value已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                // oldValue不为空且oldValue等于currentValue，也就是校验是不是上个对应的商品时间戳，也是防止并发
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 分布式锁
     *
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public boolean trueLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
