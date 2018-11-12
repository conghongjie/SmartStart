package com.conghongjie.start.state;


import com.conghongjie.start.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 节点状态管理器，只维护节点的状态
 *
 * @author conghongjie
 */
public class TaskStateManager {


    /**
     * 状态池
     */
    HashMap<String, Task> unStartTasks = new HashMap<>();//无法执行（所依赖的任务还未完成）
    HashMap<String, Task> waitingTasks = new HashMap<>();//等待执行
    HashMap<String, Task> runningTasks = new HashMap<>();//正在执行
    HashMap<String, Task> finishedTasks = new HashMap<>();//执行完成


    /**
     * 添加任务
     *
     * @param task
     */
    public void addTask(Task task) {
        synchronized (this) {
            // 初始化状态信息
            TaskStateInfo stateInfo = new TaskStateInfo(task);
            task.stateInfo = stateInfo;
            // 解除自身依赖
            task.stateInfo.dependTasks.removeAll(finishedTasks.keySet());
            // 根据状态，放入状态池
            if (task.stateInfo.dependTasks.size() > 0) {
                unStartTasks.put(task.taskKey, task);
            } else {
                waitingTasks.put(task.taskKey, task);
            }
        }
    }

    /**
     * 任务执行 waiting -> running
     *
     * @param task
     */
    public void onTaskExceted(Task task) {
        synchronized (this) {
            // 将此任务移动到完成状态池
            waitingTasks.remove(task.taskKey);
            runningTasks.put(task.taskKey, task);
        }
    }

    /**
     * 任务结束 running -> finished
     *
     * @param task
     */
    public void onTaskFinish(Task task) {
        synchronized (this) {
            // 将此任务移动到完成状态池
            runningTasks.remove(task.taskKey);
            finishedTasks.put(task.taskKey, task);
            // 解除被依赖Task的依赖
            Iterator<Task> iterator = unStartTasks.values().iterator();
            while (iterator.hasNext()) {
                Task temp = iterator.next();
                temp.stateInfo.dependTasks.remove(task.taskKey);
                if (temp.stateInfo.dependTasks.size() == 0) {
                    iterator.remove();
                    waitingTasks.put(temp.taskKey, temp);
                }
            }
        }
    }

    /**
     * 获取所有等待中的任务
     *
     * @return
     */
    public Collection<Task> getWaitingTasks() {
        return waitingTasks.values();
    }


    /**
     * 判断此时没有任务需要执行
     *
     * @return
     */
    public boolean isAllTaskFinish() {
        synchronized (this){
            if (unStartTasks.isEmpty() && waitingTasks.isEmpty() && runningTasks.isEmpty()) {
                return true;
            }
            return false;
        }
    }

    public int getRunningTaskNum(){
        return runningTasks.size();
    }

    /**
     * 释放资源
     */
    public void release(){
        finishedTasks.clear();
    }

}
