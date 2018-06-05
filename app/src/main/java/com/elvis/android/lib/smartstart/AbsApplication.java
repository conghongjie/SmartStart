package com.elvis.android.lib.smartstart;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.elvis.android.lib.smart_start.SmartStart;

/**
 * Created by conghongjie on 2018/6/5.
 */

public abstract class AbsApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 初始化启动器
        SmartStart.setContext(base);
        // 构建启动器
        buildSmartStart();
        // 开始执行ApplicationTasks
        SmartStart.startApplicationTasks();
    }


    public abstract void buildSmartStart();


    private static Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        // 等待ApplicationTasks执行结束
        SmartStart.waitUntilApplicationTasksFinish();
        // 5s后开始执行DelayTasks
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SmartStart.startDelayTasks();
            }
        },5000);
    }
}
