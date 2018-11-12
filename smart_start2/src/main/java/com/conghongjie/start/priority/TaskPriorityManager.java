package com.conghongjie.start.manager;


import com.conghongjie.start.obj.TaskWrapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 只负责计算优先级相关的工作
 *
 * 节点的优先级 = 节点及节点后至少还需要多少时间 = 节点所在最长路径的执行时长 - 节点最早开始执行的时间
 */
public class TaskPriorityManager {


    public static class TaskPriorityInfo {

        public String taskKey;//key

        public long priority;//优先级
        public long takeTime;//任务执行时长

        public final ArrayList<TaskPriorityInfo> dependPriorityControllers = new ArrayList<>();//依赖的任务
        public final ArrayList<TaskPriorityInfo> dependedPriorityControllers = new ArrayList<>();//被依赖的任务

        public long getNodeEarliestTime;//节点最早开始执行的时间
        public long getNodeLongestTime;//节点所在最长路径执行时长

    }



    /** 所有的节点，方便查找 */
    HashMap<String,TaskPriorityInfo> allNodes = new HashMap<>();


    /**
     * 添加任务
     * @param priorityController
     */
    public void addNode(TaskPriorityInfo priorityController){
        synchronized (this) {
            // 非法检查
            if (allNodes.get(priorityController.taskKey) != null) {
                throw new RuntimeException("taskKey has be used");
            }
            // 保存此任务
            TaskWrapper node = new TaskWrapper(task);
            allTasks.put(task.taskKey, node);
        }
    }





    /**
     * 计算并保存优先级
     * @param taskWrappers
     */
    void computeAndSavePriority(ArrayList<TaskWrapper> taskWrappers){
        // 清除所有task的优先级信息

        // 检查taskTime是否>=0

        // 计算所有节点的最早开始执行的时间

        // 计算所有节点所在最长路径执行时长

        // 计算优先级

        // 保存优先级

    }

    /**
     * 初始化优先级
     * @param taskWrapper
     */
    void initPriority(TaskWrapper taskWrapper){
        // 读取优先级缓存

        // 设置优先级或者默认优先级

    }

    /**
     * 获取最高优先级的任务
     */
    public TaskWrapper getMaxPriorityTask(ArrayList<TaskWrapper> taskWrappers){
        return null;
    }



    /**
     * 计算节点最早开始执行的时间
     */
    long getNodeEarliestTime(){
        return 1;
    }

    /**
     * 计算节点所在最长路径执行时长
     */
    long getNodeLongestTime(){
        return 1;
    }


}
