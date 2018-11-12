package com.conghongjie.start.priority;


import com.conghongjie.start.Task;

import java.util.ArrayList;

public class TaskPriorityInfo {

    public long priority;//优先级
    public long takeTime;//任务执行时长

    public final ArrayList<Task> dependTasks = new ArrayList<>();//依赖的任务
    public final ArrayList<Task> dependedTasks = new ArrayList<>();//被依赖的任务

    public long timeToEnd = -1;//节点最早开始执行的时间
    public long takeTimeOfLongestPath = -1;//节点所在最长路径执行时长


}
