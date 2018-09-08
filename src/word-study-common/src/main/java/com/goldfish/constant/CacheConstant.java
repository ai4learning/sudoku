package com.goldfish.constant;


/**
 * 缓存相关常量
 * @author zhangshibin
 *
 */
public class CacheConstant {

	/**
	 * 异步任务信息超时时间，1小时
	 */
	public static final int TASK_CACHE_EXPIRE = 60*60*1;

    /**
     * 用户信息缓存时间
     */
	public static final int USER_CACHE_EXPIRE=60*60*1;


	public static String ANSY_TASK="kaw_ansyTask_";

    /**
     * 用户信息cache key
     */
    public static String USER_CACHE_KEY="user_cache_key_";
	
	/**
	 * 获取异步任务缓存key
	 * @param taskId
	 * @return
	 */
	public static String getAnsyTaskKey(String taskId){
		return ANSY_TASK+taskId;
	}

    /**
     *
     */

    public static  String getUserCacheKey(String pin){
        return USER_CACHE_KEY+pin;
    }
}
