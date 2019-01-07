package com.cyb.chat.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Cyb
 * 线程池包装
 */
public class ThreadPool {
    private static ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,
            new ThreadFactoryBuilder().setNameFormat("chatClient-pool-%d").build());
    private static ExecutorService threadPoolExecutor = new ThreadPoolExecutor(1, Integer.MAX_VALUE,
            10L, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new ThreadFactoryBuilder()
            .setNameFormat("chatServer-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

    private ThreadPool() {

    }

    public static ScheduledExecutorService getScheduledThreadPoolExecutor() {
        return scheduledThreadPoolExecutor;
    }

    public static ExecutorService getThreadPoolExecutor() {
        return threadPoolExecutor;
    }


}
