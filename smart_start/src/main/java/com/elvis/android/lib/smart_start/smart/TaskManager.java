package com.elvis.android.lib.smart_start.smart;


import android.content.Context;
import android.os.Build;
import android.os.Trace;
import android.util.Log;

import com.elvis.android.lib.smart_start.obj.AbsTask;
import com.elvis.android.lib.smart_start.obj.IModuleStart;
import com.elvis.android.lib.smart_start.obj.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Task调度器
 *
 * 1、多线程异步调度
 * 2、长路径优先算法（自学习）
 *
 * Created by conghongjie on 2018/5/24.
 */
public class TaskManager {


    private static Context context;
    public static void setContext(Context context) {
        TaskManager.context = context;
    }

    public static Context getContext() {
        return context;
    }

    // 运行模式
    public enum State{
        NOT_START,
        APPLICATION_STARTED,
        APPLICATION_FINISH,
        DELAY_STARTED,
        DELAY_FINISH
    }

    private static SmartThreadPool smartThreadPool = new SmartThreadPool();

    // 属性
    private static State nowState = State.NOT_START;
    private static HashMap<String,Node> allNodes = new HashMap<>();



    /**
     * 暴露API
     */
    public static void addModuleStart(IModuleStart moduleStart) {
        if (context==null){
            throw new RuntimeException("please set context first");
        }
        ArrayList<AbsTask> tasks = new ArrayList<>();
        moduleStart.buildTasks(tasks);
        TaskManager.addTasks(tasks);
    }

    public static void startApplicationTasks() {
        if (nowState != State.NOT_START){
            throw new RuntimeException("Error State");
        }
        nowState = State.APPLICATION_STARTED;
        smartThreadPool.setUp();
        checkHasTaskToExecute();
    }
    public static void waitUntilApplicationTasksFinish() {
        while (TaskManager.nowState == State.APPLICATION_STARTED){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void startDelayTasks() {
        if (nowState != State.APPLICATION_FINISH){
            throw new RuntimeException("Error State");
        }
        nowState = State.DELAY_STARTED;
        checkHasTaskToExecute();
    }

    /**
     * 构建任务图
     */

    private static void addTasks(ArrayList<AbsTask> tasks) {
        if (tasks==null){
            return;
        }
        for (int i=0;i<tasks.size();i++){
            AbsTask task = tasks.get(i);
            if (task!=null){
                addTask(task);
            }
        }
    }

    private static void addTask(AbsTask task){
        Node node = allNodes.get(task.taskKey);
        if (node==null){
            node = new Node();
            allNodes.put(task.taskKey,node);
        }
        if (node.task!=null){
            throw new RuntimeException("SmartStart出现相同的key："+task.taskKey);
        }
        node.task = task;
        node.priority = SmartPriority.getPriority(task.taskKey);
        for (String dependKey : task.getDepends()){
            Node dependNode = allNodes.get(dependKey);
            if (dependNode==null){
                dependNode = new Node();
                allNodes.put(dependKey,dependNode);
            }
            dependNode.tempDependeds.add(node);
            dependNode.finalDependeds.add(node);
            node.tempDepends.add(dependNode);
            node.finalDepends.add(dependNode);
        }
    }


    /**
     * 核心函数：执行的过程，解开dependList
     */

    private static synchronized void checkHasTaskToExecute() {
        if (nowState!= State.APPLICATION_STARTED && nowState!= State.DELAY_STARTED) {
            return;
        }
        // 找到"无依赖关系"&"未执行"的Task
        ArrayList<Node> ioNodes = new ArrayList<>();
        ArrayList<Node> cpuNodes = new ArrayList<>();
        for (Map.Entry<String, Node> entry : allNodes.entrySet()) {
            Node node = entry.getValue();
            if ((nowState== State.APPLICATION_STARTED && node.task.isApplicationTask()) || (nowState== State.DELAY_STARTED && !node.task.isApplicationTask())){
                if ((node.tempDepends ==null ||node.tempDepends.size()==0) && node.state== Node.State.notStart){
                    if (node.task.isIOTask()){
                        ioNodes.add(node);
                    }else {
                        cpuNodes.add(node);
                    }
                }
            }
        }
        // 执行或判断是否执行结束
        if (ioNodes.size()>0 || cpuNodes.size()>0){
            if (nowState== State.APPLICATION_STARTED){
                executeApplicationCPUTask(cpuNodes);
                executeApplicationIOTask(ioNodes);
            }else {
                executeDelayTask(cpuNodes);
                executeDelayTask(ioNodes);
            }
        }else {
            boolean hasRunningTask = false;
            boolean hasNotStartTask = false;
            for (Map.Entry<String, Node> entry : allNodes.entrySet()) {
                Node node = entry.getValue();
                if ((nowState== State.APPLICATION_STARTED && node.task.isApplicationTask()) || (nowState== State.DELAY_STARTED && !node.task.isApplicationTask())) {
                    if (node.state == Node.State.notStart) {
                        hasNotStartTask = true;
                    }
                    if (node.state == Node.State.running) {
                        hasRunningTask = true;
                    }
                }
            }
            if (hasRunningTask){
                //继续执行 do nothing
            } else if (!hasNotStartTask){
                //所有任务完成
                if (nowState== State.APPLICATION_STARTED){
                    nowState = State.APPLICATION_FINISH;
                }else if (nowState== State.DELAY_STARTED){
                    nowState = State.DELAY_FINISH;
                    //保存优先级
                    final HashMap<String,Node> priorityNodes = new HashMap<>();
                    priorityNodes.putAll(allNodes);
                    allNodes.clear();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SmartPriority.computePriority(priorityNodes);
                            //smartThreadPool.shutdown();
                        }
                    }).start();
                }
            } else {
                //有依赖问题
                String error = "";
                for (Map.Entry<String, Node> entry : allNodes.entrySet()) {
                    Node node = entry.getValue();
                    if (node.state== Node.State.notStart){
                        error = error+ "["+node.task.taskKey+"]";
                    }
                }
                throw new RuntimeException(error + "这些启动Task无法执行，请检查依赖关系！");
            }
        }
    }

    private static void executeDelayTask(ArrayList<Node> nodes) {
        for (final Node ioNode:nodes) {
            ioNode.state = Node.State.running;
            smartThreadPool.executeLimitRunnable(new Runnable() {
                @Override
                public void run() {
                    doExecuteTask(ioNode);
                }
            });
        }
    }


    private static int nowRunningCPUTask = 0;
    private static void executeApplicationCPUTask(ArrayList<Node> cpuNodes){
        while (nowRunningCPUTask<smartThreadPool.cpuCoreNumber && cpuNodes.size()>0){
            final Node cpuNode = getMaxPriorityNode(cpuNodes);
            if (cpuNode!=null) {
                cpuNodes.remove(cpuNode);
                nowRunningCPUTask++;
                cpuNode.state = Node.State.running;
                smartThreadPool.executeCPURunnable(new Runnable() {
                    @Override
                    public void run() {
                        doExecuteTask(cpuNode);
                        nowRunningCPUTask--;
                    }
                });
            }
        }
    }
    private static void executeApplicationIOTask(ArrayList<Node> ioNodes){
        for (final Node ioNode:ioNodes) {
            ioNode.state = Node.State.running;
            smartThreadPool.executeIORunnable(new Runnable() {
                @Override
                public void run() {
                    doExecuteTask(ioNode);
                }
            });
        }
    }

    private static void doExecuteTask(Node node){
        Log.e("ElvissS","---:"+node.task.taskKey);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.beginSection(node.task.taskKey);
        }
        long start = System.currentTimeMillis();
        node.task.getExecutor().execute();
        node.takeTime = System.currentTimeMillis() - start;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.endSection();
        }
        for (Node depended : node.tempDependeds) {
            depended.tempDepends.remove(node);
        }
        Node maxFinishDependNodeWithSameTask = getMaxFinishDependNodeWithSameTask(node);
        if (maxFinishDependNodeWithSameTask!=null) {
            node.finishTime = maxFinishDependNodeWithSameTask.finishTime + node.takeTime;
        } else {
            node.finishTime = node.takeTime;
        }
        node.state = Node.State.finished;
        checkHasTaskToExecute();
    }


    /**
     * 辅助函数：
     */

    //获取列表中，优先级最高的Node
    private static Node getMaxPriorityNode(ArrayList<Node> nodes){
        if (nodes==null || nodes.size()==0){
            return null;
        }
        Node max = null;
        for (Node node : nodes){
            if (max == null ||node.priority>max.priority){
                max = node;
            }
        }
        return max;
    }

    //获取列表中，finishTime最大的节点
    private static Node getMaxFinishDependNodeWithSameTask(Node node){
        // 只找到跟本node同一属性的节点（隔离ApplicationTask与DelayTask）
        ArrayList<Node> newTempDepends= new ArrayList<>();
        for (Node temp :node.finalDepends){
            if (node.task.isApplicationTask() && temp.task.isApplicationTask()){
                newTempDepends.add(temp);
            }
            if (!node.task.isApplicationTask() && !temp.task.isApplicationTask()){
                newTempDepends.add(temp);
            }
        }
        if (newTempDepends.size()==0){
            return null;
        }
        Node max = null;
        for (Node temp : newTempDepends){
            if (max == null ||temp.finishTime>max.finishTime){
                max = temp;
            }
        }
        return max;
    }




}
