package com.backend.show.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class MyThreadPoolExecutor {
    private MyThreadPoolExecutor(){}

    private static final ExecutorService executorService;

    static {
        executorService = Executors.newFixedThreadPool(4);
    }

    public static ExecutorService getExecutor() {
        return executorService;
    }
}
