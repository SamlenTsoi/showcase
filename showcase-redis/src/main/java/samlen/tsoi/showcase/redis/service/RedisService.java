package samlen.tsoi.showcase.redis.service;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis Service接口
 *
 * @author samlen_tsoi
 * @date 2018/4/26
 */
public interface RedisService {
    /**
     * 字符串类型将数据存入缓存
     *
     * @param key 键
     * @param val 值
     * @return
     */
    void strSet(String key, String val);

    /**
     * 字符串类型将数据存入缓存并设置失效时间
     *
     * @param key 键
     * @param val 值
     * @param seconds 有效时间
     * @return
     */
    void strSet(String key, String val, int seconds);

    /**
     * 字符串类型将数据存入缓存并设置失效时间和时间单位
     *
     * @param key
     * @param val
     * @param length
     * @param timeUnit
     */
    void strSet(String key, String val, int length, TimeUnit timeUnit);

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)
     * key键对应的值value对应的ascii码,在offset的位置(从左向右数)变为value
     *
     * @param key
     * @param offset
     * @param val
     */
    Boolean strSetBit(String key, Long offset, Boolean val);


    /**
     * 设置，当不存在时，设置成功
     *
     * @param key
     * @param val
     * @return
     */
    Boolean strSetIfAbsent(String key, String val);

    /**
     * 为多个键分别设置它们的值
     *
     * @param multiMap
     */
    void strMultiSet(Map<String, String> multiMap);

    /**
     * 为多个键分别设置它们的值，如果存在则返回false，不存在返回true
     *
     * @param multiMap
     * @return
     */
    Boolean strMultiSetIfAbsent(Map<String, String> multiMap);

    /**
     * 将val追加到key对应的val后
     *
     * @param key
     * @param val
     */
    void strAppend(String key, String val);

    /**
     * 返回key所对应的value值得长度
     *
     * @param key
     * @return
     */
    Long strSize(String key);

    /**
     * key所对应的val增加delta整数类型
     *
     * @param key
     * @param delta
     * @return
     */
    Long strIncrement(String key, Long delta);

    /**
     * key所对应的val增加delta浮点类型
     *
     * @param key
     * @param delta
     * @return
     */
    Double strIncrement(String key, Double delta);

    /**
     * 获取key对应的val
     *
     * @param key
     * @return
     */
    String strGet(String key);

    /**
     * 截取key所对应的value字符串start和end区间的值
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    String strGet(String key, Long start, Long end);

    /**
     * 批量获取key对应的val
     *
     * @param keys
     * @return
     */
    List<String> strMultiGet(List<String> keys);

    /**
     * 设置键的字符串值并返回其旧值
     *
     * @param key
     * @param val
     * @return
     */
    String strGetAndSet(String key, String val);

    /**
     * 获取键对应值的ascii码的在offset处位值
     *
     * @param key
     * @param offset
     * @return
     */
    Boolean strGetBit(String key, Long offset);

    /**
     * 在列表中index的位置设置value值
     *
     * @param key
     * @param index
     * @param val
     */
    void listSet(String key, Long index, String val);

    /**
     * 返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，
     * 其中0是列表的第一个元素（列表的头部），1是下一个元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> listRange(String key, Long start, Long end);

    /**
     * 修剪现有列表，使其只包含指定的指定范围的元素，
     * 起始和停止都是基于0的索引
     *
     * @param key
     * @param start
     * @param end
     */
    void listTrim(String key, Long start, Long end);

    /**
     * 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，
     * 并返回0。当key存储的值不是列表时返回错误。
     *
     * @param key
     * @return
     */
    Long listSize(String key);

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
    Long listRemove(String key, Long count, String val);

    /**
     * 根据下标获取列表中的值，下标是从0开始的
     *
     * @param key
     * @param index
     * @return
     */
    String listIndex(String key, Long index);

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，
     * 则在执行推送操作之前将其创建为空列表。（从左边插入）
     *
     * @param key
     * @param val
     * @return
     */
    Long listLeftPush(String key, String val);

    /**
     * 把value值放到key对应列表中pivot值的左面，
     * 如果pivot值存在的话
     *
     * @param key
     * @param pivot
     * @param val
     * @return
     */
    Long listLeftPush(String key, String pivot, String val);

    /**
     * 只有存在key对应的列表才能将这个value值
     * 插入到key所对应的列表中
     *
     * @param key
     * @param val
     * @return
     */
    Long listLeftPushIfPresent(String key, String val);

    /**
     * 批量把一个数组插入到列表中
     *
     * @param key
     * @param val
     * @return
     */
    Long listLeftPushAll(String key, String... val);

    /**
     * 批量把一个集合插入到列表中
     *
     * @param key
     * @param val
     * @return
     */
    Long listLeftPushAll(String key, List<String> val);

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，
     * 则在执行推送操作之前将其创建为空列表。（从右边插入）
     *
     * @param key
     * @param val
     * @return
     */
    Long listRightPush(String key, String val);

    /**
     * 把value值放到key对应列表中pivot值的右面，如果pivot值存在的话
     *
     * @param key
     * @param pivot
     * @param val
     * @return
     */
    Long listRightPush(String key, String pivot, String val);

    /**
     * 只有存在key对应的列表才能将这个value值
     * 插入到key所对应的列表中（从右边插入）
     *
     * @param key
     * @param val
     * @return
     */
    Long listRightPushIfPresent(String key, String val);

    /**
     * 批量把一个数组插入到列表中（从右边插入）
     *
     * @param key
     * @param val
     * @return
     */
    Long listRightPushAll(String key, String... val);

    /**
     * 批量把一个集合插入到列表中（从右边插入）
     *
     * @param key
     * @param val
     * @return
     */
    Long listRightPushAll(String key, List<String> val);

    /**
     * 弹出最左边的元素，弹出之后该值在列表中将不复存在
     *
     * @param key
     * @return
     */
    String listLeftPop(String key);

    /**
     * 移出并获取列表的第一个元素，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    String listLeftPop(String key, Long timeout, TimeUnit timeUnit);

    /**
     * 弹出最右边的元素，弹出之后该值在列表中将不复存在
     *
     * @param key
     * @return
     */
    String listRightPop(String key);

    /**
     * 移出并获取列表的最后一个元素，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    String listRightPop(String key, Long timeout, TimeUnit timeUnit);

    /**
     * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回。
     *
     * @param sourceKey
     * @param destinationKey
     * @return
     */
    String listRightPopAndLeftPush(String sourceKey, String destinationKey);

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
    String listRightPopAndLeftPush(String sourceKey, String destinationKey, Long timeout, TimeUnit timeUnit);

    /**
     * 确定哈希hashKey是否存在
     *
     * @param key
     * @param hashKey
     * @return
     */
    Boolean hashHasKey(String key, String hashKey);

    /**
     * 获取key所对应的散列表的key
     *
     * @param key
     * @return
     */
    Set<Object> hashKeys(String key);

    /**
     * 根据key获取整个哈希存储的值
     *
     * @param key
     * @return
     */
    List<Object> hashValues(String key);

    /**
     * 获取key所对应的散列表的大小个数
     *
     * @param key
     * @return
     */
    Long hashSize(String key);

    /**
     * 删除给定的哈希hashKeys
     *
     * @param key
     * @param hashKeys
     * @return
     */
    Long hashDelete(String key, Object... hashKeys);

    /**
     * 通过给定的delta增加散列hashKey的值（浮点数）
     *
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    Double hashIncrement(String key, String hashKey, Double delta);


    /**
     * 通过给定的delta增加散列hashKey的值（整型）
     *
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    Long hashIncrement(String key, String hashKey, Long delta);

    /**
     * 从键中的哈希获取给定hashKey的值
     *
     * @param key
     * @param hashKey
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T hashGet(String key, String hashKey, Class<T> clazz);

    /**
     * 从键中的哈希获取给定hashKey的值
     *
     * @param key
     * @param hashKey
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> hashGetList(String key, String hashKey, Class<T> clazz);

    /**
     * 从键中的哈希获取给定hashKey的值
     *
     * @param key
     * @param hashKey
     * @return
     */
    Object hashGet(String key, String hashKey);

    /**
     * 从哈希中批量获取给定hashKey的值
     *
     * @param key
     * @param hashKeyList
     * @return
     */
    List<Object> hashMultiGet(String key, List<Object> hashKeyList);

    /**
     * 设置散列hashKey的值
     *
     * @param key
     * @param hashKey
     * @param keyVal
     */
    void hashPut(String key, String hashKey, String keyVal);

    /**
     * 仅当hashKey不存在时才设置散列hashKey的值。
     *
     * @param key
     * @param hashKey
     * @param keyVal
     * @return
     */
    Boolean hashPutIfAbsent(String key, String hashKey, String keyVal);

    /**
     * 使用data中提供的多个散列字段设置到key对应的散列表中
     *
     * @param key
     * @param hashData
     */
    void hashPutAll(String key, Map<Object, Object> hashData);

    /**
     * 根据key获取整个哈希存储
     *
     * @param key
     * @return
     */
    Map<Object, Object> hashEntries(String key);

    /**
     * 使用Cursor在key的hash中迭代，相当于迭代器
     *
     * @param key
     * @param options
     * @return
     */
    Cursor<Map.Entry<Object, Object>> hashScan(String key, ScanOptions options);

    /**
     * 无序集合中添加元素，返回添加个数
     *
     * @param key
     * @param val
     * @return
     */
    Long setAdd(String key, String... val);

    /**
     * 移除集合中一个或多个成员
     *
     * @param key
     * @param val
     * @return
     */
    Long setRemove(String key, Object... val);

    /**
     * 移除并返回集合中的一个随机元素
     *
     * @param key
     */
    void setPop(String key);

    /**
     * 将 val 元素从 sourceKey 集合移动到 destinationKey 集合
     *
     * @param sourceKey
     * @param val
     * @param destinationKey
     * @return
     */
    Boolean setMove(String sourceKey, String val, String destinationKey);

    /**
     * 无序集合的大小长度
     *
     * @param key
     * @return
     */
    Long setSize(String key);

    /**
     * 判断 val 元素是否是集合 key 的成员
     *
     * @param key
     * @param val
     * @return
     */
    Boolean setIsMember(String key, String val);

    /**
     * key对应的无序集合与otherKey对应的无序集合求交集
     *
     * @param key
     * @param otherKey
     * @return
     */
    Set<String> setIntersect(String key, String otherKey);

    /**
     * key对应的无序集合与多个otherKey对应的无序集合求交集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    Set<String> setIntersect(String key, List<String> otherKeys);

    /**
     * key无序集合与otherkey无序集合的交集存储到destKey无序集合中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long setIntersectAndStore(String key, String otherKey, String destKey);

    /**
     * key对应的无序集合与多个otherKey对应的无序集合求交集存储到destKey无序集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long setIntersectAndStore(String key, List<String> otherKeys, String destKey);

    /**
     * key无序集合与otherKey无序集合的并集
     *
     * @param key
     * @param otherKey
     * @return
     */
    Set<String> setUnion(String key, String otherKey);

    /**
     * key无序集合与多个otherKey无序集合的并集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    Set<String> setUnion(String key, List<String> otherKeys);

    /**
     * key无序集合与otherkey无序集合的并集存储到destKey无序集合中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long setUnionAndStore(String key, String otherKey, String destKey);

    /**
     * key无序集合与多个otherkey无序集合的并集存储到destKey无序集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long setUnionAndStore(String key, List<String> otherKeys, String destKey);

    /**
     * key无序集合与otherKey无序集合的差集
     *
     * @param key
     * @param otherKey
     * @return
     */
    Set<String> setDifference(String key, String otherKey);

    /**
     * key无序集合与多个otherKey无序集合的差集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    Set<String> setDifference(String key, List<String> otherKeys);

    /**
     * key无序集合与otherkey无序集合的差集存储到destKey无序集合中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long setDifferenceAndStore(String key, String otherKey, String destKey);

    /**
     * key无序集合与多个otherkey无序集合的差集存储到destKey无序集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long setDifferenceAndStore(String key, List<String> otherKeys, String destKey);

    /**
     * 返回集合中的所有成员
     *
     * @param key
     * @return
     */
    Set<String> setMembers(String key);

    /**
     * 随机获取key无序集合中的一个元素
     *
     * @param key
     * @return
     */
    String setRandomMember(String key);

    /**
     * 获取多个key无序集合中的元素，count表示个数
     *
     * @param key
     * @param count
     * @return
     */
    List<String> setRandomMembers(String key, Long count);

    /**
     * 获取多个key无序集合中的元素（去重），count表示个数
     *
     * @param key
     * @param count
     * @return
     */
    Set<String> setDistinctRandomMembers(String key, Long count);

    /**
     * 遍历set
     *
     * @param key
     * @param scanOptions
     * @return
     */
    Cursor<String> setScan(String key, ScanOptions scanOptions);

    /**
     * 通过分数返回有序集合指定区间内的成员个数
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Long zsetCount(String key, Double min, Double max);

    /**
     * 获取有序集合的成员数
     *
     * @param key
     * @return
     */
    Long zsetSize(String key);

    /**
     * 获取有序集合的成员数
     *
     * @param key
     * @return
     */
    Long zsetZCard(String key);

    /**
     * 获取指定成员的score值
     *
     * @param key
     * @param val
     * @return
     */
    Double zsetScore(String key, String val);

    /**
     * 新增一个有序集合，存在的话为false，不存在的话为true
     *
     * @param key
     * @param val
     * @param score
     * @return
     */
    Boolean zsetAdd(String key, String val, Double score);

    /**
     * 新增一个有序集合
     *
     * @param key
     * @param tuples
     * @return
     */
    Long zsetAdd(String key, Set<ZSetOperations.TypedTuple<String>> tuples);

    /**
     * 从有序集合中移除一个或者多个元素
     *
     * @param key
     * @param val
     * @return
     */
    Long zsetRemove(String key, Object... val);

    /**
     * 移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Long zsetRemoveRange(String key, Long start, Long end);

    /**
     * 根据指定的score值得范围来移除成员
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Long zsetRemoveRangeByScore(String key, Double min, Double max);

    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key
     * @param val
     * @param delta
     * @return
     */
    Double zsetIncrementScore(String key, String val, Double delta);

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param val
     * @return
     */
    Long zsetRank(String key, String val);

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param val
     * @return
     */
    Long zsetReverseRank(String key, String val);

    /**
     * 通过索引区间返回有序集合成指定区间内的成员，
     * 其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<String> zsetRange(String key, Long start, Long end);

    /**
     * 通过索引区间返回有序集合成指定区间内的成员对象，
     * 其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<String>> zsetRangeWithScores(String key, Long start, Long end);

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
    Set<String> zsetRangeByScore(String key, Double min, Double max, Long offset, Long count);

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
    Set<ZSetOperations.TypedTuple<String>> zsetRangeByScoreWithScores(String key, Double min, Double max, Long offset, Long count);

    /**
     * 通过索引区间返回有序集合成指定区间内的成员，
     * 其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<String> zsetReverseRange(String key, Long start, Long end);

    /**
     * 通过索引区间返回有序集合成指定区间内的成员对象，
     * 其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<String>> zsetReverseRangeWithScores(String key, Long start, Long end);

    /**
     * 有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<String> zsetReverseRangeByScore(String key, Double min, Double max);

    /**
     * 有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<ZSetOperations.TypedTuple<String>> zsetReverseRangeByScoreWithScores(String key, Double min, Double max);

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
    Set<String> zsetReverseRangeByScore(String key, Double min, Double max, Long offset, Long count);

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
    Set<ZSetOperations.TypedTuple<String>> zsetReverseRangeByScoreWithScores(String key, Double min, Double max, Long offset, Long count);
    /**
     * 计算给定的一个有序集的并集，并存储在新的 destKey中，
     * key相同的话会把score值相加
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long zsetUnionAndStore(String key, String otherKey, String destKey);

    /**
     * 计算给定的多个有序集的并集，并存储在新的 destKey中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long zsetUnionAndStore(String key, List<String> otherKeys, String destKey);

    /**
     * 计算给定的一个有序集的交集并将结果集存储在新的有序集合 key 中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long zsetIntersectAndStore(String key, String otherKey, String destKey);

    /**
     * 计算给定的多个有序集的交集并将结果集存储在新的有序集合 key 中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long zsetIntersectAndStore(String key, List<String> otherKeys, String destKey);

    /**
     * 遍历zset
     *
     * @param key
     * @param options
     * @return
     */
    Cursor<ZSetOperations.TypedTuple<String>> zsetScan(String key, ScanOptions options);

    /**
     * 删除
     *
     * @param key
     */
    void delete(String key);

    /**
     * 批量删除
     *
     * @param keys
     */
    void delete(Collection<String> keys);

    /**
     * 设置key超时
     *
     * @param key
     * @param timeout
     */
    Boolean expire(String key, Long timeout);

    /**
     * 设置key超时
     *
     * @param key
     * @param timeout
     */
    Boolean expire(String key, Integer timeout);

    /**
     * 设置key超时
     *
     * @param key
     * @param timeout
     * @param timeUnit
     */
    Boolean expire(String key, Long timeout, TimeUnit timeUnit);

    /**
     * 设置KEY超时
     *
     * @param key
     * @param date
     * @return
     */
    Boolean expireAt(String key, Date date);

    /**
     * 获取有效时长
     *
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 获取有效时长
     *
     * @param key
     * @param timeUnit
     * @return
     */
    Long getExpire(String key, TimeUnit timeUnit);

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 获取Object
     * @param key
     * @return
     */
    Object getObject(String key);
}
