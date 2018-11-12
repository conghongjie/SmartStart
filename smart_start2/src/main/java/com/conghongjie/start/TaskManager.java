package com.conghongjie.start;

import com.conghongjie.start.manager.TaskPoolManager;
import com.conghongjie.start.obj.TaskWrapper;
import com.conghongjie.start.obj.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务执行管理器
 *
 *
 * @author conghongjie
 */
public class SmartStart {



    static final int STATE_NOSTART = 0;
    static final int STATE_RUNNING = 1;
    static final int STATE_STOPPED = 2;

    private int state = STATE_NOSTART;
    private TaskPoolManager taskPoolManager = new TaskPoolManager();
    private ExecutorService mFixedThreadExecutor;

    Runnable finishCallBack;

    public SmartStart(int threadPoolSize, Runnable finishCallBack){
        mFixedThreadExecutor = Executors.newFixedThreadPool(threadPoolSize);
        this.finishCallBack = finishCallBack;
    }


    /**
     * 启动所有任务
     */
    public void start(){
        if (state != STATE_NOSTART){
            throw new RuntimeException("can only be started one time");
        }
        state = STATE_RUNNING;
        // 启动脉冲
        keepRunning();
    }

    /**
     * 结束所有任务（表示不再添加新任务）
     */
    public void stop(){
        if (state != STATE_RUNNING){
            throw new RuntimeException("error");
        }
        state = STATE_STOPPED;
        // 所有任务都完成的回调
        if (taskPoolManager.isAllTaskFinish()){
            if (finishCallBack!=null){
                finishCallBack.run();
            }
        }
    }

    /**
     * 添加任务（可在开始之前，也可以在开始之后,但在stop之前）
     */
    public void addTask(Task task){
        if (state != 0 && state != 1) {
            throw new RuntimeException("has been stopped");
        }
        // 添加任务
        taskPoolManager.addTask(task);
        // 启动脉冲
        keepRunning();
    }

    /**
     * 执行可执行的任务
     */
    private void keepRunning(){
        if (state == STATE_NOSTART){
            return;
        }
        while(true){
            // 找到一个优先级最高的、等待执行的任务
            final TaskWrapper nextTaskToRun = taskPoolManager.getNextTaskToRun();
            if (nextTaskToRun == null){
                break;
            }
            // 扔到线程池中
            mFixedThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // 执行
                    nextTaskToRun.task.runnable.run();
                    // 完成
                    taskPoolManager.onTaskFinish(nextTaskToRun);
                    // 所有任务都完成的回调
                    if (state == STATE_STOPPED && taskPoolManager.isAllTaskFinish()){
                        if (finishCallBack!=null){
                            finishCallBack.run();
                        }
                    }
                }
            });
        }

    }










}
