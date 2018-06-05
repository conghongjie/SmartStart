package com.elvis.android.lib.smart_start.obj;

/**
 * Created by conghongjie on 2018/5/31.
 */
public class ApplicationIOTask extends AbsTask{

    protected ApplicationIOTask(String taskKey) {
        super(taskKey);
    }
    @Override
    public boolean isIOTask() {
        return true;
    }


    @Override
    public boolean isApplicationTask() {
        return true;
    }
}
