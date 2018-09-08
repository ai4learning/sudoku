package com.goldfish.concurrent.threadpool;

import com.goldfish.common.log.LogTypeEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by John on 2018/7/1 0001.
 */
//@Component("threadPoolContext")
public class ThreadPoolContext {
    private static Map<String, ThreadPoolExecutor> threadPools = new ConcurrentHashMap<String,ThreadPoolExecutor>(16);

    /**
     *
     * @param name 线程池名称
     * @param isDaemon 是否为守护线程
     * @return
     */
    public static ThreadPoolExecutor getThreadPool(String name, boolean isDaemon) {
        // 存在，则不创建
        if (threadPools.get(name) != null) {
            return threadPools.get(name);
        }
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8, 16, 120, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(50),
            new NamedThreadFactory(name, isDaemon), new ThreadPoolExecutor.CallerRunsPolicy());
        threadPools.put(name, threadPool);
        return threadPool;
    }

    @Override
    protected void finalize() throws Throwable {
        if (threadPools == null || threadPools.isEmpty()) {
            return;
        }
        for (Map.Entry<String, ThreadPoolExecutor> entry : threadPools.entrySet()) {
            ThreadPoolExecutor threadpool = entry.getValue();
            if (threadpool != null && !threadpool.isShutdown()) {
                threadpool.shutdown();
                LogTypeEnum.DEFAULT.info("ThreadPool-"+entry.getKey()+" Shut Down.");
            }
        }
        threadPools = null;
        super.finalize();
    }
}
