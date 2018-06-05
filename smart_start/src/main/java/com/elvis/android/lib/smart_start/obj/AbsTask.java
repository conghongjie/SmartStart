package com.elvis.android.lib.smart_start.obj;

import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/5/24.
 */
public abstract class AbsTask {


    public String taskKey = null;
    private ArrayList<String> depends = new ArrayList<>(); //依赖关系
    private Executor executor = null;



    protected AbsTask(String taskKey){
        this.taskKey = taskKey;
    }

    public abstract boolean isIOTask();
    public abstract boolean isApplicationTask();


    public AbsTask addDepend(String taskKey){
        depends.add(taskKey);
        return this;
    }

    public ArrayList<String> getDepends() {
        return depends;
    }


    public AbsTask setExecutor(Executor executor){
        this.executor = executor;
        return this;
    }

    public Executor getExecutor() {
        return executor;
    }





    public interface Executor {
        void execute();
    }

}
