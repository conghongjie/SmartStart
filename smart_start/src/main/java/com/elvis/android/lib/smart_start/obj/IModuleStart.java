package com.elvis.android.lib.smart_start.obj;

import java.util.ArrayList;

/**
 *
 * 模块初始化和启动的时机：
 * 1、Application onCreate完成之前执行
 * 2、主Activity 加载完成之后执行
 *
 * Created by conghongjie on 2018/5/23.
 */
public interface IModuleStart {

    void buildTasks(ArrayList<AbsTask> tasks);

}
