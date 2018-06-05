package com.elvis.android.lib.smart_start.obj;

/**
 * Created by conghongjie on 2018/5/24.
 */
public class DelayIOTask extends AbsTask{

    public DelayIOTask(String taskKey) {
        super(taskKey);
    }

    @Override
    public boolean isIOTask() {
        return true;
    }

    @Override
    public boolean isApplicationTask() {
        return false;
    }
}
