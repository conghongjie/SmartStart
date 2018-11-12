package com.conghongjie.start.obj;

import com.conghongjie.start.manager.TaskPriorityManager;

import java.util.ArrayList;

/**
 * 任务信息
 * @author conghongjie
 */
public class Task {

    /** 输入的信息：*/
    public String taskKey;
    public String[] dependTasks;
    public Runnable runnable;

    /** 输入的信息：*/
    TaskPriorityManager.TaskPriorityInfo priorityInfo;
    TaskPriorityManager.TaskPriorityInfo priorityInfo;

    /**
     * 构造函数
     */
    public Task(String taskKey,String[] dependTasks,Runnable runnable){
        this.taskKey = taskKey;
        this.dependTasks = dependTasks;
        this.runnable = runnable;




    }



}
