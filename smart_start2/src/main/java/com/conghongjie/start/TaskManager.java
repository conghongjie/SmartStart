package com.conghongjie.start;

import android.content.Context;
import android.os.Trace;
import android.util.Log;

import com.conghongjie.start.priority.TaskPriorityManager;
import com.conghongjie.start.state.TaskStateManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    long startTime = 0; //启动时间

    long endTime = 0; //完成时间

    Context mContext;

    int threadPoolSize;

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
        this.threadPoolSize = threadPoolSize;
        mFixedThreadExecutor = Executors.newFixedThreadPool(threadPoolSize);
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
        startTime = System.currentTimeMillis();
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
        synchronized (TaskManager.this) {
            if (state != 0 && state != 1) {
                throw new RuntimeException("has been stopped");
            }
            // 状态管理器中添加任务
            taskStateManager.addTask(task);
            // 优先级管理器中添加任务
            taskPriorityManager.addTask(mContext, task);
            // 启动脉冲
            keepRunning();
        }
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
                if (taskStateManager.getRunningTaskNum()>=threadPoolSize){
                    return;
                }
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
                        Trace.beginSection(task.taskKey);
                        task.runnable.run();
                        Trace.endSection();
                        taskPriorityManager.setTaskTakeTime(task,System.currentTimeMillis()-start);
                        synchronized (TaskManager.this){
                            taskStateManager.onTaskFinish(task);
                        }
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
                    // 打印耗时
                    endTime = System.currentTimeMillis();
                    Log.e("StartStart","任务总耗时："+(endTime-startTime));
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
