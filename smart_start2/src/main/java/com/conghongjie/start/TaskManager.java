package com.conghongjie.start;

import android.content.Context;
import android.util.Log;

import com.conghongjie.start.priority.TaskPriorityManager;
import com.conghongjie.start.state.TaskStateManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 任务执行管理器
 *
 * @author conghongjie
 */
public class TaskManager {


    /**
     * 属性
     */
    static final int STATE_NOSTART = 0;
    static final int STATE_RUNNING = 1;
    static final int STATE_STOPPED = 2;

    private int state = STATE_NOSTART;//状态

    Context mContext;

    private ExecutorService mFixedThreadExecutor;//线程池

    Runnable finishCallBack;//完成回调

    /**
     * 优先级管理
     */
    private TaskPriorityManager taskPriorityManager = new TaskPriorityManager();

    /**
     * 状态管理
     */
    private TaskStateManager taskStateManager = new TaskStateManager();


    /**
     * 构造函数
     *
     * @param context
     * @param threadPoolSize
     * @param finishCallBack
     */
    public TaskManager(Context context,int threadPoolSize, Runnable finishCallBack) {
        mContext = context;
        mFixedThreadExecutor = Executors.newCachedThreadPool();;
//        new ThreadPoolExecutor(threadPoolSize, threadPoolSize, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
//            int i = 0;
//
//            @Override
//            public Thread newThread(Runnable runnable) {
//                i++;
//                return new Thread("smart_start_thread_pool_" + i);
//            }
//        });
        this.finishCallBack = finishCallBack;
    }


    /**
     * 启动所有任务
     */
    public void start() {
        if (state != STATE_NOSTART) {
            throw new RuntimeException("can only be started one time");
        }
        state = STATE_RUNNING;
        // 启动脉冲
        keepRunning();
    }

    /**
     * 结束所有任务（表示不再添加新任务）
     */
    public void stop() {
        if (state != STATE_RUNNING) {
            throw new RuntimeException("error");
        }
        state = STATE_STOPPED;
        // 所有任务都完成的回调
        tryDoFinishedJob();
    }

    /**
     * 添加任务（可在开始之前，也可以在开始之后,但在stop之前）
     */
    public void addTask(Task task) {
        if (state != 0 && state != 1) {
            throw new RuntimeException("has been stopped");
        }
        // 状态管理器中添加任务
        taskStateManager.addTask(task);
        // 优先级管理器中添加任务
        taskPriorityManager.addTask(mContext,task);
        // 启动脉冲
        keepRunning();
    }

    /**
     * 执行可执行的任务
     */
    private void keepRunning() {
        if (state == STATE_NOSTART) {
            return;
        }
        synchronized (this){
            while (true) {
                // 找到一个优先级最高的、等待执行的任务
                final Task task = taskPriorityManager.getMaxPriorityTask(taskStateManager.getWaitingTasks());
                if (task == null) {
                    return;
                }
                taskStateManager.onTaskExceted(task);
                mFixedThreadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        // 执行
                        long start = System.currentTimeMillis();
                        task.runnable.run();
                        Log.e("StartStart",task.taskKey+":"+(System.currentTimeMillis()-start));
                        taskPriorityManager.setTaskTakeTime(task,System.currentTimeMillis()-start);
                        taskStateManager.onTaskFinish(task);
                        keepRunning();
                        // 所有任务都完成的回调
                        tryDoFinishedJob();
                    }
                });
            }
        }
    }


    /**
     * 检查是否结束了
     */
    private void tryDoFinishedJob() {
        if (state == STATE_STOPPED && taskStateManager.isAllTaskFinish()) {
            mFixedThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // 回调
                    if (finishCallBack != null) {
                        finishCallBack.run();
                    }
                    // 计算优先级
                    taskPriorityManager.computeAndSavePriority(mContext);
                    // 释放资源
                    taskPriorityManager.release();
                    taskStateManager.release();
                    mFixedThreadExecutor.shutdown();
                }
            });
        }
    }


}
