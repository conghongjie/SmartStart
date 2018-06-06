package com.elvis.android.lib.smart_start.obj;


import java.util.ArrayList;

/**
 * Created by conghongjie on 2018/5/25.
 */
public class Node {


    public enum State{
        notStart,
        running,
        finished
    }

    public State state = State.notStart;
    public AbsTask task = null;
    // 优先级计算相关：
    public long takeTime = 1;//任务节点的耗时
    public long finishTime = 0;//从任务链到此节点的最大总耗时
    public long maxFinishTime = -1;//
    public long priority = -1;//优先级（由该节点相关的所有任务链中，最大总耗时决定）

    //双向依赖关系
    public ArrayList<Node> finalDepends = new ArrayList<>();
    public ArrayList<Node> finalDependeds = new ArrayList<>();
    public ArrayList<Node> tempDepends = new ArrayList<>();//可以拆解的
    public ArrayList<Node> tempDependeds = new ArrayList<>();//可以拆解的





}
