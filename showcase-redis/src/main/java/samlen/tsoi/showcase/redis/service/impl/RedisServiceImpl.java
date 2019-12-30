package samlen.tsoi.showcase.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import samlen.tsoi.showcase.redis.service.RedisService;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis Service实现
 *
 * @author wxw
 * @date 2018-02-27 16:27
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 字符串类型将数据存入缓存
     *
     * @param key 键
     * @param val 值
     * @return
     */
    @Override
    public void strSet(String key, String val) {
        stringRedisTemplate.opsForValue().set(key, val);
    }

    /**
     * 字符串类型将数据存入缓存并设置失效时间
     *
     * @param key 键
     * @param val 值
     * @param seconds 有效时间
     * @return
     */
    @Override
    public void strSet(String key, String val, int seconds) {
        stringRedisTemplate.opsForValue().set(key, val, seconds, TimeUnit.SECONDS);
    }

    /**
     * 字符串类型将数据存入缓存并设置失效时间和时间单位
     *
     * @param key
     * @param val
     * @param length
     * @param timeUnit
     */
    @Override
    public void strSet(String key, String val, int length, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, val, length, timeUnit);
    }

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)
     * key键对应的值value对应的ascii码,在offset的位置(从左向右数)变为value
     *
     * @param key
     * @param offset
     * @param val
     */
    @Override
    public Boolean strSetBit(String key, Long offset, Boolean val) {
        return stringRedisTemplate.opsForValue().setBit(key, offset, val);
    }


    /**
     * 设置，当不存在时，设置成功
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Boolean strSetIfAbsent(String key, String val) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, val);
    }

    /**
     * 为多个键分别设置它们的值
     *
     * @param multiMap
     */
    @Override
    public void strMultiSet(Map<String, String> multiMap) {
        stringRedisTemplate.opsForValue().multiSet(multiMap);
    }

    /**
     * 为多个键分别设置它们的值，如果存在则返回false，不存在返回true
     *
     * @param multiMap
     * @return
     */
    @Override
    public Boolean strMultiSetIfAbsent(Map<String, String> multiMap) {
        return stringRedisTemplate.opsForValue().multiSetIfAbsent(multiMap);
    }

    /**
     * 将val追加到key对应的val后
     *
     * @param key
     * @param val
     */
    @Override
    public void strAppend(String key, String val) {
        stringRedisTemplate.opsForValue().append(key, val);
    }

    /**
     * 返回key所对应的value值得长度
     *
     * @param key
     * @return
     */
    @Override
    public Long strSize(String key) {
        return stringRedisTemplate.opsForValue().size(key);
    }

    /**
     * key所对应的val增加delta整数类型
     *
     * @param key
     * @param delta
     * @return
     */
    @Override
    public Long strIncrement(String key, Long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * key所对应的val增加delta浮点类型
     *
     * @param key
     * @param delta
     * @return
     */
    @Override
    public Double strIncrement(String key, Double delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 获取key对应的val
     *
     * @param key
     * @return
     */
    @Override
    public String strGet(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 截取key所对应的value字符串start和end区间的值
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public String strGet(String key, Long start, Long end) {
        return stringRedisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 批量获取key对应的val
     *
     * @param keys
     * @return
     */
    @Override
    public List<String> strMultiGet(List<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 设置键的字符串值并返回其旧值
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public String strGetAndSet(String key, String val) {
        return stringRedisTemplate.opsForValue().getAndSet(key, val);
    }

    /**
     * 获取键对应值的ascii码的在offset处位值
     *
     * @param key
     * @param offset
     * @return
     */
    @Override
    public Boolean strGetBit(String key, Long offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 在列表中index的位置设置value值
     *
     * @param key
     * @param index
     * @param val
     */
    @Override
    public void listSet(String key, Long index, String val) {
        stringRedisTemplate.opsForList().set(key, index, val);
    }

    /**
     * 返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，
     * 其中0是列表的第一个元素（列表的头部），1是下一个元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<String> listRange(String key, Long start, Long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 修剪现有列表，使其只包含指定的指定范围的元素，
     * 起始和停止都是基于0的索引
     *
     * @param key
     * @param start
     * @param end
     */
    @Override
    public void listTrim(String key, Long start, Long end) {
        stringRedisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，
     * 并返回0。当key存储的值不是列表时返回错误。
     *
     * @param key
     * @return
     */
    @Override
    public Long listSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }


    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * 计数参数以下列方式影响操作：
     * count> 0：删除等于从头到尾移动的值的元素。
     * count <0：删除等于从尾到头移动的值的元素。
     * count = 0：删除等于value的所有元素。
     *
     * @param key
     * @param count
     * @param val
     * @return
     */
    @Override
    public Long listRemove(String key, Long count, String val) {
        return stringRedisTemplate.opsForList().remove(key, count, val);
    }

    /**
     * 根据下标获取列表中的值，下标是从0开始的
     *
     * @param key
     * @param index
     * @return
     */
    @Override
    public String listIndex(String key, Long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，
     * 则在执行推送操作之前将其创建为空列表。（从左边插入）
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long listLeftPush(String key, String val) {
        return stringRedisTemplate.opsForList().leftPush(key, val);
    }

    /**
     * 把value值放到key对应列表中pivot值的左面，
     * 如果pivot值存在的话
     *
     * @param key
     * @param pivot
     * @param val
     * @return
     */
    @Override
    public Long listLeftPush(String key, String pivot, String val) {
        return stringRedisTemplate.opsForList().leftPush(key, pivot, val);
    }

    /**
     * 只有存在key对应的列表才能将这个value值
     * 插入到key所对应的列表中
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long listLeftPushIfPresent(String key, String val) {
        return stringRedisTemplate.opsForList().leftPushIfPresent(key, val);
    }

    /**
     * 批量把一个数组插入到列表中
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long listLeftPushAll(String key, String... val) {
        return stringRedisTemplate.opsForList().leftPushAll(key, val);
    }

    /**
     * 批量把一个集合插入到列表中
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long listLeftPushAll(String key, List<String> val) {
        return stringRedisTemplate.opsForList().leftPushAll(key, val);
    }

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，
     * 则在执行推送操作之前将其创建为空列表。（从右边插入）
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long listRightPush(String key, String val) {
        return stringRedisTemplate.opsForList().rightPush(key, val);
    }

    /**
     * 把value值放到key对应列表中pivot值的右面，如果pivot值存在的话
     *
     * @param key
     * @param pivot
     * @param val
     * @return
     */
    @Override
    public Long listRightPush(String key, String pivot, String val) {
        return stringRedisTemplate.opsForList().rightPush(key, pivot, val);
    }

    /**
     * 只有存在key对应的列表才能将这个value值
     * 插入到key所对应的列表中（从右边插入）
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long listRightPushIfPresent(String key, String val) {
        return stringRedisTemplate.opsForList().rightPushIfPresent(key, val);
    }

    /**
     * 批量把一个数组插入到列表中（从右边插入）
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long listRightPushAll(String key, String... val) {
        return stringRedisTemplate.opsForList().rightPushAll(key, val);
    }

    /**
     * 批量把一个集合插入到列表中（从右边插入）
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long listRightPushAll(String key, List<String> val) {
        return stringRedisTemplate.opsForList().rightPushAll(key, val);
    }

    /**
     * 弹出最左边的元素，弹出之后该值在列表中将不复存在
     *
     * @param key
     * @return
     */
    @Override
    public String listLeftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移出并获取列表的第一个元素，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    @Override
    public String listLeftPop(String key, Long timeout, TimeUnit timeUnit) {
        return stringRedisTemplate.opsForList().leftPop(key, timeout, timeUnit);
    }

    /**
     * 弹出最右边的元素，弹出之后该值在列表中将不复存在
     *
     * @param key
     * @return
     */
    @Override
    public String listRightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移出并获取列表的最后一个元素，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    @Override
    public String listRightPop(String key, Long timeout, TimeUnit timeUnit) {
        return stringRedisTemplate.opsForList().rightPop(key, timeout, timeUnit);
    }

    /**
     * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回。
     *
     * @param sourceKey
     * @param destinationKey
     * @return
     */
    @Override
    public String listRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
    }

    /**
     * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param sourceKey
     * @param destinationKey
     * @param timeout
     * @param timeUnit
     * @return
     */
    @Override
    public String listRightPopAndLeftPush(String sourceKey, String destinationKey, Long timeout, TimeUnit timeUnit) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, timeUnit);
    }

    /**
     * 确定哈希hashKey是否存在
     *
     * @param key
     * @param hashKey
     * @return
     */
    @Override
    public Boolean hashHasKey(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取key所对应的散列表的key
     *
     * @param key
     * @return
     */
    @Override
    public Set<Object> hashKeys(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     * 根据key获取整个哈希存储的值
     *
     * @param key
     * @return
     */
    @Override
    public List<Object> hashValues(String key) {
        return stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * 获取key所对应的散列表的大小个数
     *
     * @param key
     * @return
     */
    @Override
    public Long hashSize(String key) {
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * 删除给定的哈希hashKeys
     *
     * @param key
     * @param hashKeys
     * @return
     */
    @Override
    public Long hashDelete(String key, Object... hashKeys) {
        return stringRedisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 通过给定的delta增加散列hashKey的值（浮点数）
     *
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    @Override
    public Double hashIncrement(String key, String hashKey, Double delta) {
        return stringRedisTemplate.opsForHash().increment(key, hashKey, delta);
    }


    /**
     * 通过给定的delta增加散列hashKey的值（整型）
     *
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    @Override
    public Long hashIncrement(String key, String hashKey, Long delta) {
        return stringRedisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * 从键中的哈希获取给定hashKey的值
     *
     * @param key
     * @param hashKey
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T hashGet(String key, String hashKey, Class<T> clazz) {
        Object val = stringRedisTemplate.opsForHash().get(key, hashKey);
        return JSON.parseObject(String.valueOf(val), clazz, Feature.AutoCloseSource);
    }

    /**
     * 从键中的哈希获取给定hashKey的值
     *
     * @param key
     * @param hashKey
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> hashGetList(String key, String hashKey, Class<T> clazz) {
        String val = (String) stringRedisTemplate.opsForHash().get(key, hashKey);
        return JSON.parseArray(val, clazz);
    }

    /**
     * 从键中的哈希获取给定hashKey的值
     *
     * @param key
     * @param hashKey
     * @return
     */
    @Override
    public Object hashGet(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 从哈希中批量获取给定hashKey的值
     *
     * @param key
     * @param hashKeyList
     * @return
     */
    @Override
    public List<Object> hashMultiGet(String key, List<Object> hashKeyList) {
        return stringRedisTemplate.opsForHash().multiGet(key, hashKeyList);
    }

    /**
     * 设置散列hashKey的值
     *
     * @param key
     * @param hashKey
     * @param keyVal
     */
    @Override
    public void hashPut(String key, String hashKey, String keyVal) {
        stringRedisTemplate.opsForHash().put(key, hashKey, keyVal);
    }

    /**
     * 仅当hashKey不存在时才设置散列hashKey的值。
     *
     * @param key
     * @param hashKey
     * @param keyVal
     * @return
     */
    @Override
    public Boolean hashPutIfAbsent(String key, String hashKey, String keyVal) {
        return stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, keyVal);
    }

    /**
     * 使用data中提供的多个散列字段设置到key对应的散列表中
     *
     * @param key
     * @param hashData
     */
    @Override
    public void hashPutAll(String key, Map<Object, Object> hashData) {
        stringRedisTemplate.opsForHash().putAll(key, hashData);
    }

    /**
     * 根据key获取整个哈希存储
     *
     * @param key
     * @return
     */
    @Override
    public Map<Object, Object> hashEntries(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 使用Cursor在key的hash中迭代，相当于迭代器
     *
     * @param key
     * @param options
     * @return
     */
    @Override
    public Cursor<Map.Entry<Object, Object>> hashScan(String key, ScanOptions options) {
        return stringRedisTemplate.opsForHash().scan(key, options);
    }

    /**
     * 无序集合中添加元素，返回添加个数
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long setAdd(String key, String... val) {
        return stringRedisTemplate.opsForSet().add(key, val);
    }

    /**
     * 移除集合中一个或多个成员
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long setRemove(String key, Object... val) {
        return stringRedisTemplate.opsForSet().remove(key, val);
    }

    /**
     * 移除并返回集合中的一个随机元素
     *
     * @param key
     */
    @Override
    public void setPop(String key) {
        stringRedisTemplate.opsForSet().pop(key);
    }

    /**
     * 将 val 元素从 sourceKey 集合移动到 destinationKey 集合
     *
     * @param sourceKey
     * @param val
     * @param destinationKey
     * @return
     */
    @Override
    public Boolean setMove(String sourceKey, String val, String destinationKey) {
        return stringRedisTemplate.opsForSet().move(sourceKey, val, destinationKey);
    }

    /**
     * 无序集合的大小长度
     *
     * @param key
     * @return
     */
    @Override
    public Long setSize(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * 判断 val 元素是否是集合 key 的成员
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Boolean setIsMember(String key, String val) {
        return stringRedisTemplate.opsForSet().isMember(key, val);
    }

    /**
     * key对应的无序集合与otherKey对应的无序集合求交集
     *
     * @param key
     * @param otherKey
     * @return
     */
    @Override
    public Set<String> setIntersect(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * key对应的无序集合与多个otherKey对应的无序集合求交集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    @Override
    public Set<String> setIntersect(String key, List<String> otherKeys) {
        return stringRedisTemplate.opsForSet().intersect(key, otherKeys);
    }

    /**
     * key无序集合与otherkey无序集合的交集存储到destKey无序集合中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long setIntersectAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
    }

    /**
     * key对应的无序集合与多个otherKey对应的无序集合求交集存储到destKey无序集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long setIntersectAndStore(String key, List<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * key无序集合与otherKey无序集合的并集
     *
     * @param key
     * @param otherKey
     * @return
     */
    @Override
    public Set<String> setUnion(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().union(key, otherKey);
    }

    /**
     * key无序集合与多个otherKey无序集合的并集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    @Override
    public Set<String> setUnion(String key, List<String> otherKeys) {
        return stringRedisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * key无序集合与otherkey无序集合的并集存储到destKey无序集合中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long setUnionAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * key无序集合与多个otherkey无序集合的并集存储到destKey无序集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long setUnionAndStore(String key, List<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * key无序集合与otherKey无序集合的差集
     *
     * @param key
     * @param otherKey
     * @return
     */
    @Override
    public Set<String> setDifference(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * key无序集合与多个otherKey无序集合的差集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    @Override
    public Set<String> setDifference(String key, List<String> otherKeys) {
        return stringRedisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * key无序集合与otherkey无序集合的差集存储到destKey无序集合中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long setDifferenceAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    /**
     * key无序集合与多个otherkey无序集合的差集存储到destKey无序集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long setDifferenceAndStore(String key, List<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    /**
     * 返回集合中的所有成员
     *
     * @param key
     * @return
     */
    @Override
    public Set<String> setMembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取key无序集合中的一个元素
     *
     * @param key
     * @return
     */
    @Override
    public String setRandomMember(String key) {
        return stringRedisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 获取多个key无序集合中的元素，count表示个数
     *
     * @param key
     * @param count
     * @return
     */
    @Override
    public List<String> setRandomMembers(String key, Long count) {
        return stringRedisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 获取多个key无序集合中的元素（去重），count表示个数
     *
     * @param key
     * @param count
     * @return
     */
    @Override
    public Set<String> setDistinctRandomMembers(String key, Long count) {
        return stringRedisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * 遍历set
     *
     * @param key
     * @param scanOptions
     * @return
     */
    @Override
    public Cursor<String> setScan(String key, ScanOptions scanOptions) {
        return stringRedisTemplate.opsForSet().scan(key, scanOptions);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员个数
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    @Override
    public Long zsetCount(String key, Double min, Double max) {
        return stringRedisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 获取有序集合的成员数
     *
     * @param key
     * @return
     */
    @Override
    public Long zsetSize(String key) {
        return stringRedisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取有序集合的成员数
     *
     * @param key
     * @return
     */
    @Override
    public Long zsetZCard(String key) {
        return stringRedisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 获取指定成员的score值
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Double zsetScore(String key, String val) {
        return stringRedisTemplate.opsForZSet().score(key, val);
    }

    /**
     * 新增一个有序集合，存在的话为false，不存在的话为true
     *
     * @param key
     * @param val
     * @param score
     * @return
     */
    @Override
    public Boolean zsetAdd(String key, String val, Double score) {
        return stringRedisTemplate.opsForZSet().add(key, val, score);
    }

    /**
     * 新增一个有序集合
     *
     * @param key
     * @param tuples
     * @return
     */
    @Override
    public Long zsetAdd(String key, Set<ZSetOperations.TypedTuple<String>> tuples) {
        return stringRedisTemplate.opsForZSet().add(key, tuples);
    }

    /**
     * 从有序集合中移除一个或者多个元素
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long zsetRemove(String key, Object... val) {
        return stringRedisTemplate.opsForZSet().remove(key, val);
    }

    /**
     * 移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Long zsetRemoveRange(String key, Long start, Long end) {
        return stringRedisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 根据指定的score值得范围来移除成员
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    @Override
    public Long zsetRemoveRangeByScore(String key, Double min, Double max) {
        return stringRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key
     * @param val
     * @param delta
     * @return
     */
    @Override
    public Double zsetIncrementScore(String key, String val, Double delta) {
        return stringRedisTemplate.opsForZSet().incrementScore(key, val, delta);
    }

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long zsetRank(String key, String val) {
        return stringRedisTemplate.opsForZSet().rank(key, val);
    }

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public Long zsetReverseRank(String key, String val) {
        return stringRedisTemplate.opsForZSet().rank(key, val);
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员，
     * 其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<String> zsetRange(String key, Long start, Long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员对象，
     * 其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<String>> zsetRangeWithScores(String key, Long start, Long end) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员，并在索引范围内，
     * 其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    @Override
    public Set<String> zsetRangeByScore(String key, Double min, Double max, Long offset, Long count) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员对象，并在索引范围内，
     * 其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<String>> zsetRangeByScoreWithScores(String key, Double min, Double max, Long offset, Long count) {
        return stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员，
     * 其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<String> zsetReverseRange(String key, Long start, Long end) {
        return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员对象，
     * 其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<String>> zsetReverseRangeWithScores(String key, Long start, Long end) {
        return stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    @Override
    public Set<String> zsetReverseRangeByScore(String key, Double min, Double max) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<String>> zsetReverseRangeByScoreWithScores(String key, Double min, Double max) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * 有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    @Override
    public Set<String> zsetReverseRangeByScore(String key, Double min, Double max, Long offset, Long count) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
    }

    /**
     * 有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<String>> zsetReverseRangeByScoreWithScores(String key, Double min, Double max, Long offset, Long count) {
        return stringRedisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * 计算给定的一个有序集的并集，并存储在新的 destKey中，
     * key相同的话会把score值相加
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long zsetUnionAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * 计算给定的多个有序集的并集，并存储在新的 destKey中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long zsetUnionAndStore(String key, List<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 计算给定的一个有序集的交集并将结果集存储在新的有序集合 key 中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long zsetIntersectAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    /**
     * 计算给定的多个有序集的交集并将结果集存储在新的有序集合 key 中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long zsetIntersectAndStore(String key, List<String> otherKeys, String destKey) {
        return stringRedisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * 遍历zset
     *
     * @param key
     * @param options
     * @return
     */
    @Override
    public Cursor<ZSetOperations.TypedTuple<String>> zsetScan(String key, ScanOptions options) {
        return stringRedisTemplate.opsForZSet().scan(key, options);
    }

    /**
     * 删除
     *
     * @param key
     */
    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 批量删除
     *
     * @param keys
     */
    @Override
    public void delete(Collection<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    /**
     * 设置key超时
     *
     * @param key
     * @param timeout
     */
    @Override
    public Boolean expire(String key, Long timeout) {
        return stringRedisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置key超时
     *
     * @param key
     * @param timeout
     */
    @Override
    public Boolean expire(String key, Integer timeout) {
        return stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置key超时
     *
     * @param key
     * @param timeout
     * @param timeUnit
     */
    @Override
    public Boolean expire(String key, Long timeout, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 设置KEY超时
     *
     * @param key
     * @param date
     * @return
     */
    @Override
    public Boolean expireAt(String key, Date date) {
        return stringRedisTemplate.expireAt(key, date);
    }

    /**
     * 获取有效时长
     *
     * @param key
     * @return
     */
    @Override
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    /**
     * 获取有效时长
     *
     * @param key
     * @param timeUnit
     * @return
     */
    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return stringRedisTemplate.getExpire(key, timeUnit);
    }

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 获取Object
     * @param key
     * @return
     */
    @Override
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
