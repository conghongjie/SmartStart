package com.conghongjie.start.obj;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskWrapper {


    public Task task;

    public ArrayList<String> dependTasks; //依赖的任务列表



    // 优先级相关


    public TaskWrapper(Task task){
        this.task = task;
        if (task.dependTasks != null){
            dependTasks = (ArrayList<String>) Arrays.asList(task.dependTasks);
        }else {
            dependTasks = new ArrayList<>();
        }
    }

}
