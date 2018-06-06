package com.elvis.android.lib.smartstart;

import android.util.Log;

import java.util.Random;

/**
 * Created by conghongjie on 2018/6/4.
 */
public class Test {


    static long total = 33;

    public static void doJob(long millis){
        long nowTime = System.currentTimeMillis();
        while (System.currentTimeMillis()<nowTime+millis){
            //程序阻塞指定时间
            int min=10;
            int max=99;
            Random random = new Random();
            int num = random.nextInt(max)%(max-min+1) + min;
            total = (total+num)%567;
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
