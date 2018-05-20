package com.goldfish.dao.cache.redis.impl;

import com.alibaba.fastjson.JSON;
import com.goldfish.dao.cache.redis.RedisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: putengfei
 * Date: 13-11-5
 * Time: 下午4:13
 */

public class LocalRedisUtilsImpl implements RedisUtils {


    private ShardedJedisPool shardedJedisPool;
    
    private JedisPool jedisPool;

    /**
     * 日志
     */
//    private Log log = KaPriceLog.redisOperate;

    /**
     * 从队列头插入数据
     *
     * @param key 存储key
     * @param o   存储元素
     * @return true:新增成功、false:新增失败
     */
    public boolean addToListHead(String key, Object o) {
        ShardedJedis jedis = null;
        try {
            jedis = (ShardedJedis)shardedJedisPool.getResource();
            String jsonStr = JSON.toJSONString(o);
            jedis.set(key, jsonStr);
            return true;
        } catch (Exception e) {
//            log.info("lPush(),队列头放入元素失败", e);
            return false;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 从队列尾部获取数据
     *
     * @param key   存储key
     * @param clazz 存储数据的类类型
     * @return 如果返回空则失败或元素不存在
     */
    public Object getFromListTail(String key, Class<?> clazz) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            String jsonStr = jedis.rpop(key);
            if (StringUtils.isEmpty(jsonStr)) {
                return null;
            }
            return JSON.parseObject(jsonStr, clazz);
        } catch (Exception e) {
//            log.info("getFromListTail(),队列尾获取元素失败", e);
            return null;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 获取list信息
     * @param key  存储key
     * @param clazz  类类型
     * @return
     */
    public <T> List<T> getListInfo(String key, Class<T> clazz){
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            List<T> list = new ArrayList<T>();
            Long size = jedis.llen(key);
            List<String> bis = jedis.lrange(key, 0, size.longValue() - 1);
            for (String bi : bis) {
                list.add(JSON.parseObject(bi, clazz));
            }
            return list;
        } catch (Exception e) {
//            log.info("getListInfo(),获取List失败", e);
            return null;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 返回列表Key的长度
     *
     * @param key
     * @return 大于或等于0成功，小于0失败
     */
    public long getListSize(String key){
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.llen(key);
        } catch (Exception e) {
//            log.info("getListSize(),获取列表长度失败", e);
            return -1;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }


    /**
     * 校验key是否存在
     *
     * @param key
     * @return true:存在，false:不存在，如果抛出异常则默认不存在
     */
    public boolean checkKeyExists(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
//            log.info("MobileRedis->lPush(),队列尾获取元素失败", e);
            return false;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 存储对象
     *
     * @param key 存储key
     * @param o   被存储的对象
     * @return true：成功，false:失败
     */
    public boolean setObject(String key, Object o) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            String json = JSON.toJSONString(o);
            jedis.set(key, json);
            return true;
        } catch (Exception e) {
//            log.info("setObject（）,存储元素失败", e);
            return false;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 以有效期的方式存储对象
     * @param key    存储key
     * @param object     存储对象
     * @param seconds    有效期，秒
     * @return   true:保存成功，false:保存失败
     */
    public boolean setObjectByExpire(String key, Object object, int seconds) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            String json = JSON.toJSONString(object);
            jedis.set(key, json);
            jedis.expire(key, seconds);
            return true;
        } catch (Exception e) {
//            log.info("setObjectByExpire(),设置对象失败", e);
            return false;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 设置key的有效期
     * @param key
     * @param seconds
     * @return
     */
    public boolean expire(String key,int seconds){
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.expire(key,seconds);
            return true;
        } catch (Exception e) {
//            log.info("时间设置失败", e);
            return false;
        }
    }


    /**
     * 根据key获取元素
     *
     * @param key   存储key
     * @param clazz 类类型
     * @return 如果为空则无数据或抛出异常
     */
    public <T> T getObject(String key, Class<T> clazz) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            String json = jedis.get(key);
            if (StringUtils.isEmpty(json)) {
                return null;
            }
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
//            log.info("getObject（）,获取元素失败", e);
            return null;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 设置字符串数据至redis
     *
     * @param key   存储Key
     * @param value 存储的值
     * @return true:设置成功，false；设置失败
     */
    public boolean setString(String key, String value) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
//            log.info("setString（）,设置字符串数据失败", e);
            return false;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 设置带有效期的字符串
     *
     * @param key     存储key
     * @param value   值
     * @param seconds 有效期秒
     * @return true:设置成功，false；设置失败
     */
    public boolean setStringByExpire(String key, String value, int seconds) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, seconds);
            return true;
        } catch (Exception e) {
//            log.info("setStringByExpire(),设置字符串失败", e);
            return false;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 根据key获取字符串信息
     * @param key  存储key
     * @return  如果返回空，则未获取到数据或抛出异常
     */
    public String getString(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
//            log.info("getString,获取字符串失败", e);
            return null;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 删除指定key
     * @param key
     * @return  如果返回小于0则执行失败
     */
    public long deleteByKey(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            if (jedis.exists(key)) {
                return jedis.del(key);
            }
            return -1;
        } catch (Exception e) {
//            log.info("deleteByKey(),删除key失败", e);
            return -1;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 新增对象之sortset
     * @param key
     * @param score
     * @param object
     * @return 如果返回数据小于0则为失败
     */
    public Long addToSortSet(String key,double score, Object object) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            String json = JSON.toJSONString(object);
            return jedis.zadd(key, score, json);
        } catch (Exception e) {
//            log.info("addToSortSet,添加元素至List失败", e);
            return -1l;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    @Override
    public Long batchAddToSortSet(String key, Map<String, Double> skuMap) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.zadd(key,skuMap);
        } catch (Exception e) {
            return -1l;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }

    }

    public Long addToSet(String key, Object object) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            String json = JSON.toJSONString(object);
            return jedis.sadd(key,json);
        } catch (Exception e) {
//            log.info("addToSet,添加元素至List失败", e);
            return -1l;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 获取sortset中的所有数据
     *
     * @param key   唯一key
     * @param clazz 类对象
     * @param <T>
     * @return
     */
    public <T> List<T> getSortSetInfo(String key, Class<T> clazz) {
        ShardedJedis jedis = null;
        try {
            List<T> list = new ArrayList<T>();
            jedis = shardedJedisPool.getResource();
            long size = jedis.zcard(key);
            if (size > 0) {
                Set<String> bis = jedis.zrevrange(key, 0, size - 1);
                for (String bi : bis) {
                    list.add(JSON.parseObject(bi, clazz));
                }
            }
            return list;
        } catch (Exception e) {
//            log.info("getSortSetInfo(),从sortset中获取排行榜失败", e);
            return null;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 获取set中的所有数据
     *
     * @param key   唯一key
     * @param clazz 类对象
     * @param <T>
     * @return
     */
    public <T> List<T> getSetInfo(String key, Class<T> clazz) {
        ShardedJedis jedis = null;
        try {
            List<T> list = new ArrayList<T>();
            jedis = shardedJedisPool.getResource();
            long size = jedis.scard(key);
            if (size > 0) {
                Set<String> bis = jedis.smembers(key);
                for (String bi : bis) {
                    list.add(JSON.parseObject(bi, clazz));
                }
            }
            return list;
        } catch (Exception e) {
//            log.info("getSetInfo(),从set中获取排行榜失败", e);
            return null;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 根据下标从redis中获取元素 （从小到大）
     * @param key  存储key
     * @param clazz   类类型
     * @param index   下标
     * @return   如果返回为空则无数据或抛出异常
     */
    public <T> T getFromSortSetByIndex(String key,Class<T> clazz,long index) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            List<T> list = new ArrayList<T>();
            Set<String> bis = jedis.zrevrange(key, index, index);
            if(CollectionUtils.isEmpty(bis)){
                return null;
            }
            for (String bi : bis) {
                list.add(JSON.parseObject(bi, clazz));
            }
            return list.get(0);
        } catch (Exception e) {
//            log.info("getFromSortSetByIndex(),根据下标获取元素失败", e);
            return null;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }



    /**
     * 删除sortset中的指定元素
     * @param key   存储key
     * @param o      需要删除的对象
     * @return   true:删除成功，false:删除失败
     */
    public boolean delFromSortSet(String key, Object o)  {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            String json = JSON.toJSONString(o);
            jedis.zrem(key, json);
            return true;
        } catch (Exception e) {
//            log.info("delFromSortSet(),删除排行榜中的元素失败", e);
            return false;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 删除分数在区间范围内的元素
     * @param key   存储key
     * @param start  开始分数
     * @param end    结束分数
     * @return
     */
    public boolean delFromSortSetByScore(String key,long start,long end){
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.zremrangeByScore(key, start, end);
            return true;
        } catch (Exception e) {
//            log.info("delFromSortSetByScore(),根据分数删除排行榜中的元素失败", e);
            return false;
        }  finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 统计sortset中元素的数量
     * @param key   存储key
     * @return   大于等于0成功，小于0失败
     */
    public long getSortSetSize(String key)  {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            long size = jedis.zcard(key);
            return size;
        } catch (Exception e) {
//            log.info("getCountOfSortSet(),统计sortset中的元素数量失败", e);
            return -1;
        }  finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 每次递增key的值
     * @param key
     * @return  如果大于0自增成功，如果小于0自增失败
     */
    public long incrString(String key)  {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.incr(key);
        } catch (Exception e) {
//            log.info("incrString(),redis执行变量自增操作失败", e);
            return -1;
        } finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    /** 设置key的过期时间
     *1、如果生存时间设置成功，返回 1 。
     *2、当 key不存在或没办法设置生存时间，返回 0 。
     * @param key
     * @param unixTime
     * @return
     * @throws Exception
     */
    public long expireAt(String key, long unixTime){
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.expireAt(key, unixTime);
        } catch (Exception e) {
            return 0l;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
    }

    public long ttl(String key) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

	public boolean setHset(String tableName, String key, String value) {
		ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.hset(tableName, key, value);
            return true;
        } catch (Exception e) {
//            log.info("getString,获取字符串失败", e);
            return false;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
	}

	public boolean setMset(String tableName, Map<String, String> map) {
		ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.hmset(tableName, map);
            return true;
        } catch (Exception e) {
//            log.info("getString,获取字符串失败", e);
            return false;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
	}

	public String getHsetValue(String tableName, String key) {
		ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.hget(tableName, key);
        } catch (Exception e) {
//            log.info("getString,获取字符串失败", e);
            return null;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
	}

	public Set<String> getHkeys(String tableName) {
		ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.hkeys(tableName);
        } catch (Exception e) {
//            log.info("getString,获取字符串失败", e);
            return null;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
	}

	public boolean hdel(String tableName, String... keys) {
		ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.hdel(tableName, keys);
            return true;
        } catch (Exception e) {
//            log.info("getString,获取字符串失败", e);
            return false;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }
	}

    public Long zcard(String key){
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.zcard(key);
        } catch (Exception e) {
//            log.info("getString,获取字符串失败", e);
            return -1l;
        }finally {
            if(jedis != null){
                shardedJedisPool.returnResource(jedis);
            }
        }

    }

}
