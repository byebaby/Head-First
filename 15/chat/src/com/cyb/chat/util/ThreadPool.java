package com.cyb.chat.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author Cyb
 * 线程池包装
 */
public class ThreadPool {
    private static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new ThreadFactoryBuilder().setNameFormat("chatClient-pool-%d").build());

    private ThreadPool() {
    }

    //add1
    public static ScheduledExecutorService getExecutorService() {
        return executorService;
    }
}
