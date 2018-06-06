package com.elvis.android.lib.smartstart.module;

import com.elvis.android.lib.smart_start.obj.AbsTask;
import com.elvis.android.lib.smart_start.obj.ApplicationCPUTask;
import com.elvis.android.lib.smart_start.obj.DelayCPUTask;
import com.elvis.android.lib.smart_start.obj.IModuleStart;
import com.elvis.android.lib.smartstart.StartConstant;
import com.elvis.android.lib.smartstart.Test;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/5.
 */

public class ArticleModule {


    public static IModuleStart articleModuleStart = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Article_ApplicationTask_1)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new ApplicationCPUTask(StartConstant.Article_ApplicationTask_2)
                    .addDepend(StartConstant.Article_ApplicationTask_1)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(100);
                            Test.doSleep(500);
                            Test.doJob(100);
                        }
                    })
            );
            tasks.add(new DelayCPUTask(StartConstant.Article_DelayTask_1)
                    .setExecutor(new AbsTask.Executor() {

                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
            tasks.add(new DelayCPUTask(StartConstant.Article_DelayTask_2)
                    .addDepend(StartConstant.Article_DelayTask_1)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(100);
                            Test.doSleep(500);
                            Test.doJob(100);
                        }
                    })
            );
        }
    };





}
