package com.conghongjie.start;

import com.conghongjie.start.state.TaskStateInfo;
import com.conghongjie.start.priority.TaskPriorityInfo;

/**
 * 任务信息
 *
 * @author conghongjie
 */
public class Task {

    /**
     * 输入的信息：
     */
    final public String taskKey;
    final public String[] dependTasks;
    final public Runnable runnable;

    /**
     * 构造函数
     */
    public Task(String taskKey, String[] dependTasks, Runnable runnable) {
        this.taskKey = taskKey;
        if (dependTasks==null){
            dependTasks = new String[]{};
        }
        this.dependTasks = dependTasks;
        this.runnable = runnable;
    }

    /**
     * 临时信息：
     */
    public TaskPriorityInfo priorityInfo;
    public TaskStateInfo stateInfo;


}
