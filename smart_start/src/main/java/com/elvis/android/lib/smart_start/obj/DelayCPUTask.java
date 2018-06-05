package com.elvis.android.lib.smart_start.obj;

/**
 * Created by conghongjie on 2018/5/24.
 */
public class DelayCPUTask extends AbsTask{

    public DelayCPUTask(String taskKey) {
        super(taskKey);
    }

    @Override
    public boolean isIOTask() {
        return false;
    }

    @Override
    public boolean isApplicationTask() {
        return false;
    }
}
