package com.elvis.android.lib.smart_start;


import android.content.Context;

import com.elvis.android.lib.smart_start.obj.IModuleStart;
import com.elvis.android.lib.smart_start.smart.SmartPriority;
import com.elvis.android.lib.smart_start.smart.TaskManager;

/**
 * SmartStart
 *
 * 定位对外的API接口
 *
 *
 *
 * Created by conghongjie on 2018/5/23.
 */

public class SmartStart {


    /**
     * 暴露API
     */

    public static void getDefaultPriorities(String defaultPriorities){
        SmartPriority.getDefaultPriorities(defaultPriorities);
    }

    public static void setContext(Context context) {
        TaskManager.setContext(context);
    }

    public static void addModuleStart(IModuleStart moduleStart) {
        TaskManager.addModuleStart(moduleStart);
    }


    public static void startApplicationTasks() {
        TaskManager.startApplicationTasks();
    }

    public static void waitUntilApplicationTasksFinish() {
        TaskManager.waitUntilApplicationTasksFinish();
    }

    public static void startDelayTasks() {
        TaskManager.startDelayTasks();
    }





}
