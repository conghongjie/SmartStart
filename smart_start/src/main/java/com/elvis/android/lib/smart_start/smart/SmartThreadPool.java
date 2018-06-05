package com.elvis.android.lib.smart_start.smart;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 智能构建ThreadPool
 *
 * 1、根据Cpu核数构建ThreadPool
 *
 * Created by conghongjie on 2018/5/24.
 */
public class SmartThreadPool {

    public int cpuCoreNumber = 8;// cpu核数
    // 普通线程池
    private ExecutorService mFixedThreadExecutor;
    // IO线程池
    private ExecutorService mCachedThreadExecutor;
    // 限制醒线程池
    private ExecutorService mLimitFixedThreadExecutor;

    public void setUp() {
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();// cpu核数
        mFixedThreadExecutor = Executors.newFixedThreadPool(cpuCoreNumber);
        mCachedThreadExecutor = Executors.newCachedThreadPool();
        mLimitFixedThreadExecutor = Executors.newFixedThreadPool(cpuCoreNumber>3?cpuCoreNumber-2:1);

    }

    public void shutdown() {
        mFixedThreadExecutor.shutdown();
        mCachedThreadExecutor.shutdown();
        mLimitFixedThreadExecutor.shutdown();
    }

    public void executeCPURunnable(Runnable runnable){
        mFixedThreadExecutor.execute(runnable);
    }

    public void executeIORunnable(Runnable runnable){
        mCachedThreadExecutor.execute(runnable);
    }

    public void executeLimitRunnable(Runnable runnable){
        mLimitFixedThreadExecutor.execute(runnable);
    }



}
