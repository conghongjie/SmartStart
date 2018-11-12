package com.conghongjie.start.manager;


import com.conghongjie.start.obj.Task;
import com.conghongjie.start.obj.TaskWrapper;

import java.util.HashMap;

/**
 * 节点状态管理器，只维护节点的状态
 * @author conghongjie
 */
public class TaskPoolManager {



    /** 无法执行的任务池（所依赖的任务还未完成） */
    HashMap<String,TaskWrapper> unStartTaskPool = new HashMap<>();

    /** 可执行的任务池 */
    HashMap<String,TaskWrapper> waitingTaskPool = new HashMap<>();

    /** 执行中的任务池 */
    HashMap<String,TaskWrapper> runningTaskPool = new HashMap<>();

    /** 已完成的任务池 */
    HashMap<String,TaskWrapper> finishedTaskPool = new HashMap<>();


    /**
     * 添加任务
     * @param task
     */
    public void addTask(Task task){
        synchronized (this) {
            // 非法检查
            if (allTasks.get(task.taskKey) != null) {
                throw new RuntimeException("taskKey has be used");
            }
            // 保存此任务
            TaskWrapper node = new TaskWrapper(task);
            allTasks.put(task.taskKey, node);
            // 处理依赖关系（把已完成的任务依赖解除）
            node.dependTasks.removeAll(finishedTaskPool.keySet());
            // 决策放入哪个任务池中
            if (node.dependTasks.size() > 0) {
                unStartTaskPool.put(task.taskKey, node);
            } else {
                waitingTaskPool.put(task.taskKey, node);
            }
        }
    }

    /**
     * 获取下一个要执行的任务（优先级最高的等待任务）
     * @return
     */
    public TaskWrapper getNextTaskToRun(){
        synchronized (this) {
            // TODO 找到优先级最高的等待任务
            TaskWrapper taskWrapper;
            if (taskWrapper!=null){
                // 将此任务移动到执行状态池
                waitingTaskPool.remove(taskWrapper.task.taskKey);
                runningTaskPool.put(taskWrapper.task.taskKey,taskWrapper);
                return taskWrapper;
            }
            return null;
        }
    }


    /**
     * 当任务结束时
     * @param taskWrapper
     */
    public void onTaskFinish(TaskWrapper taskWrapper){
        synchronized (this) {
            // 将此任务移动到完成状态池
            runningTaskPool.remove(taskWrapper.task.taskKey);
            finishedTaskPool.put(taskWrapper.task.taskKey,taskWrapper);
        }
    }

    public boolean isAllTaskFinish(){
        if (unStartTaskPool.isEmpty() && waitingTaskPool.isEmpty() && runningTaskPool.isEmpty()){
            return true;
        }
        return false;
    }





}
