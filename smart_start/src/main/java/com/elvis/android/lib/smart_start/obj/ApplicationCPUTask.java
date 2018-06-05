package com.elvis.android.lib.smart_start.obj;

/**
 * Created by conghongjie on 2018/5/24.
 */
public class ApplicationCPUTask extends AbsTask{

    public ApplicationCPUTask(String taskKey) {
        super(taskKey);
    }

    @Override
    public boolean isIOTask() {
        return false;
    }

    @Override
    public boolean isApplicationTask() {
        return true;
    }
}