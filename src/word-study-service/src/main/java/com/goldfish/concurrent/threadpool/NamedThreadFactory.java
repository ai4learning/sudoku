package com.goldfish.concurrent.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by John on 2018/7/1 0001.
 */
public class NamedThreadFactory implements ThreadFactory {

    private final AtomicInteger threadNum = new AtomicInteger(1);

    private final String prefixName;
    private final boolean isDaemon;
    private final ThreadGroup group;

    public NamedThreadFactory(String prefixName, boolean isDaemon) {
        this.prefixName = prefixName + "-thread-";
        this.isDaemon = isDaemon;
        SecurityManager securityManager = System.getSecurityManager();
        group = (securityManager == null) ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = prefixName + threadNum.getAndIncrement();
        Thread thread = new Thread(group, r, threadName, 0);
        thread.setDaemon(isDaemon);
        return thread;
    }
}
