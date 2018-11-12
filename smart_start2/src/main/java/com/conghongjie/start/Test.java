package com.conghongjie.start;

import android.content.Context;

import java.util.Random;

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


    public static void main(Context context){
        TaskManager taskManager = new TaskManager(context,3,null);
        taskManager.addTask(new Task("1", new String[]{}, new Runnable() {
            @Override
            public void run() {
                doJob(100);
            }
        }));

        taskManager.addTask(new Task("2", new String[]{}, new Runnable() {
            @Override
            public void run() {
                doJob(500);
            }
        }));
        taskManager.addTask(new Task("3", new String[]{}, new Runnable() {
            @Override
            public void run() {
                doJob(500);
            }
        }));
        taskManager.addTask(new Task("4", new String[]{}, new Runnable() {
            @Override
            public void run() {
                doJob(100);
            }
        }));
        taskManager.addTask(new Task("5", new String[]{}, new Runnable() {
            @Override
            public void run() {
                doJob(200);
            }
        }));
        taskManager.addTask(new Task("6", new String[]{"1","2"}, new Runnable() {
            @Override
            public void run() {
                doJob(200);
            }
        }));
        taskManager.addTask(new Task("7", new String[]{"3"}, new Runnable() {
            @Override
            public void run() {
                doJob(1000);
            }
        }));
        taskManager.addTask(new Task("8", new String[]{"4","5"}, new Runnable() {
            @Override
            public void run() {
                doJob(300);
            }
        }));
        taskManager.addTask(new Task("9", new String[]{"6","7"}, new Runnable() {
            @Override
            public void run() {
                doJob(100);
            }
        }));
        taskManager.addTask(new Task("10", new String[]{"7"}, new Runnable() {
            @Override
            public void run() {
                doJob(1900);
            }
        }));
        taskManager.addTask(new Task("11", new String[]{"8","9","10"}, new Runnable() {
            @Override
            public void run() {
                doJob(500);
            }
        }));
        taskManager.addTask(new Task("12", new String[]{"8"}, new Runnable() {
            @Override
            public void run() {
                doJob(400);
            }
        }));
        taskManager.addTask(new Task("13", new String[]{"11","12"}, new Runnable() {
            @Override
            public void run() {
                doJob(100);
            }
        }));
        taskManager.start();
        taskManager.stop();
    }

}
