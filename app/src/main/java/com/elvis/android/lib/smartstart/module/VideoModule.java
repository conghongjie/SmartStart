package com.elvis.android.lib.smartstart.module;

import com.elvis.android.lib.smart_start.obj.AbsTask;
import com.elvis.android.lib.smart_start.obj.ApplicationCPUTask;
import com.elvis.android.lib.smart_start.obj.IModuleStart;
import com.elvis.android.lib.smartstart.StartConstant;
import com.elvis.android.lib.smartstart.Test;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/6/5.
 */

public class VideoModule {



    public static IModuleStart videoModuleStart = new IModuleStart() {
        @Override
        public void buildTasks(ArrayList<AbsTask> tasks) {
            tasks.add(new ApplicationCPUTask(StartConstant.Video_ApplicationTask_1)
                    .addDepend(StartConstant.Article_ApplicationTask_2)
                    .setExecutor(new AbsTask.Executor() {
                        @Override
                        public void execute() {
                            Test.doJob(200);
                        }
                    })
            );
        }
    };




}
