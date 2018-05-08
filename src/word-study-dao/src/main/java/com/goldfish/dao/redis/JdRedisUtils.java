package com.goldfish.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: putengfei
 * Date: 13-11-5
 * Time: 下午4:11
 */
public interface JdRedisUtils {
    
    /**
     * 从队列头插入数据
     * 
     * @param key 存储key
     * @param o 存储元素
     * @return true:新增成功、false:新增失败
     */
    public boolean addToListHead(String key, Object o);
    
    /**
     * 从队列尾部获取数据
     * 
     * @param key 存储key
     * @param clazz 存储数据的类类型
     * @return 如果返回空则失败或元素不存在
     */
    
    public Object getFromListTail(String key, Class<?> clazz);
    
    /**
     * 获取list信息
     * 
     * @param key 存储key
     * @param clazz 类类型
     * @return
     */
    public <T> List<T> getListInfo(String key, Class<T> clazz);
    
    /**
     * 返回列表Key的长度
     * 
     * @param key
     * @return 大于或等于0成功，小于0失败
     */
    public long getListSize(String key);
    
    /**
     * 校验key是否存在
     * 
     * @param key
     * @return true:存在，false:不存在，如果抛出异常则默认不存在
     */
    public boolean checkKeyExists(String key);
    
    /**
     * 存储对象
     * 
     * @param key 存储key
     * @param o 被存储的对象
     * @return true：成功，false:失败
     */
    public boolean setObject(String key, Object o);
    
    /**
     * 以有效期的方式存储对象
     * 
     * @param key 存储key
     * @param object 存储对象
     * @param seconds 有效期，秒
     * @return true:保存成功，false:保存失败
     */
    public boolean setObjectByExpire(String key, Object object, int seconds);
    
    /**
     * 根据key获取元素
     * 
     * @param key 存储key
     * @param clazz 类类型
     * @return 如果为空则无数据或抛出异常
     */
    public <T> T getObject(String key, Class<T> clazz);
    
    /**
     * 设置字符串数据至redis
     * 
     * @param key 存储Key
     * @param value 存储的值
     * @return true:设置成功，false；设置失败
     */
    public boolean setString(String key, String value);
    
    /**
     * 设置带有效期的字符串
     * 
     * @param key 存储key
     * @param value 值
     * @param seconds 有效期秒
     * @return true:设置成功，false；设置失败
     */
    public boolean setStringByExpire(String key, String value, int seconds);

    /**
     * 设置key的有效期
     * @param key
     * @param seconds
     * @return
     */
    public boolean expire(String key, int seconds);
    
    /**
     * 根据key获取字符串信息
     * 
     * @param key 存储key
     * @return 如果返回空，则未获取到数据或抛出异常
     */
    public String getString(String key);
    
    /**
     * 删除指定key
     * 
     * @param key
     * @return 如果返回小于0则执行失败
     */
    public long deleteByKey(String key);
    
    /**
     * 新增对象之sortset
     * 
     * @param key
     * @param score
     * @param object
     * @return 如果返回数据小于0则为失败
     */
    public Long addToSortSet(String key, double score, Object object);

    public Long batchAddToSortSet(String key, Map<String, Double> skuMap);

    /**
     * 新增对象至set集合
     * @param key
     * @param object
     * @return
     */
    public Long addToSet(String key, Object object);
    
    /**
     * 获取sortset中的所有数据
     * 
     * @param key 唯一key
     * @param clazz 类对象
     * @param <T>
     * @return
     */
    public <T> List<T> getSortSetInfo(String key, Class<T> clazz);

    /**
     * 获取sortset中的所有数据
     *
     * @param key 唯一key
     * @param clazz 类对象
     * @param <T>
     * @return
     */
    public <T> List<T> getSetInfo(String key, Class<T> clazz);
    
    /**
     * 根据下标从redis中获取元素
     * 
     * @param key 存储key
     * @param clazz 类类型
     * @param index 下标
     * @return 如果返回为空则无数据或抛出异常
     */
    public <T> T getFromSortSetByIndex(String key, Class<T> clazz, long index);
    
    /**
     * 删除sortset中的指定元素
     * 
     * @param key 存储key
     * @param o 需要删除的对象
     * @return true:删除成功，false:删除失败
     */
    public boolean delFromSortSet(String key, Object o);
    
    /**
     * 删除分数在区间范围内的元素
     * 
     * @param key 存储key
     * @param start 开始分数
     * @param end 结束分数
     * @return
     */
    public boolean delFromSortSetByScore(String key, long start, long end);
    
    /**
     * 统计sortset中元素的数量
     * 
     * @param key 存储key
     * @return 大于等于0成功，小于0失败
     */
    public long getSortSetSize(String key);
    
    /**
     * 每次递增key的值
     * 
     * @param key
     * @return 如果大于0自增成功，如果小于0自增失败
     */
    public long incrString(String key);
    
    /**
     * 设置key的过期时间
     * 1、如果生存时间设置成功，返回 1 。
     * 2、当 key不存在或没办法设置生存时间，返回 0 。
     * 
     * @param key
     * @param unixTime
     * @return
     */
    public long expireAt(String key, long unixTime);

    /**
     * 测试有效期
     * @param key
     * @return
     */
    public long ttl(String key);
    
    /**
     * 给对应Hash表名添加键值对
     * @param tableName
     * @param key
     * @param value
     * @return
     */
    public boolean setHset(String tableName, String key, String value);
    
    /**
     * 批量给对应Hash表名添加键值对
     * @param tableName
     * @return
     */
    public boolean setMset(String tableName, Map<String, String> map);
    
    /**
     * 根据Hash表名和键得到值
     * @param tableName
     * @param key
     * @return
     */
    public String getHsetValue(String tableName, String key);
    
    /**
     * 得到Hash表的值的Set
     * @param tableName
     * @return
     */
    public Set<String> getHkeys(String tableName);
    
    /**
     * 根据Hash表名和Key删除该键
     * @param tableName
     * @param key
     * @return
     */
    public boolean hdel(String tableName, String... key);

    public Long zcard(String key);
}
