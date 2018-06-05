package com.elvis.android.lib.smartstart.module;

/**
 * Created by conghongjie on 2018/6/4.
 */
public class Test {


    public static void doJob(long millis){
        long nowTime = System.currentTimeMillis();
        while (System.currentTimeMillis()<nowTime+millis){
            //程序阻塞指定时间
        }
    }

    public static void doSleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }







}
