package com.conghongjie.start.state;

import com.conghongjie.start.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskStateInfo {

    public List<String> dependTasks; //依赖的任务列表

    public TaskStateInfo(Task task) {
        dependTasks = new ArrayList<>();
        for (int i=0;i<task.dependTasks.length;i++){
            dependTasks.add(task.dependTasks[i]);
        }
    }

}
