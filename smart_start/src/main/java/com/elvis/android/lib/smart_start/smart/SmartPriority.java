package com.elvis.android.lib.smart_start.smart;


import android.util.Log;

import com.elvis.android.lib.smart_start.obj.Node;
import com.elvis.android.lib.smart_start.utils.SPHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 智能决策Task的执行优先级
 *
 * - 每次执行完，计算Task优先级，保存
 * - 每次启动，读取Task优先级
 *
 * Created by conghongjie on 2018/5/24.
 */
public class SmartPriority {



    /**
     * 获取优先级：
     */
    private static boolean isLoad = false;
    private static HashMap<String,Integer> prioritys = new HashMap<>();
    private static void loadPrioritys(){
        JSONObject jsonObject = getSP();
        Iterator iterator = jsonObject.keys();
        String key;
        while(iterator.hasNext()){
            key = (String) iterator.next();
            try {
                prioritys.put(key,jsonObject.getInt(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public static int getPriority(String key){
        if (!isLoad){
            synchronized (prioritys){
                if (!isLoad){
                    loadPrioritys();
                    isLoad = true;
                }
            }
        }
        Integer p = prioritys.get(key);
        return p==null?1:p;
    }


    /**
     * 计算优先级
     */
    public static synchronized void computePriority(HashMap<String,Node> allNodes){
        JSONObject jsonObject = getSP();
        for (Map.Entry<String, Node> entry : allNodes.entrySet()) {
            Node node = entry.getValue();
            if (node.maxFinishTime==-1){
                node.maxFinishTime = getMaxFinishTimeInDependeds(node);
            }
            try {
                jsonObject.put(node.task.taskKey,node.maxFinishTime-node.finishTime+node.takeTime);
                Log.e("ElvisdD",node.task.taskKey+":::"+(node.maxFinishTime-node.finishTime+node.takeTime));
                Log.d("ElvisdD","maxFinishTime:"+node.maxFinishTime);
                Log.d("ElvisdD","finishTime:"+node.finishTime);
                Log.d("ElvisdD","takeTime:"+node.takeTime);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        setSp(jsonObject);
    }

    static long getMaxFinishTimeInDependeds(Node node){
        // 只找到跟本node同一属性的节点（隔离ApplicationTask与DelayTask）
        ArrayList<Node> newTempDependeds= new ArrayList<>();
        for (int i=0;i<node.tempDependeds.size();i++){
            Node temp = node.tempDependeds.get(i);
            if (node.task.isApplicationTask() && temp.task.isApplicationTask()){
                newTempDependeds.add(temp);
            }
            if (!node.task.isApplicationTask() && !temp.task.isApplicationTask()){
                newTempDependeds.add(temp);
            }
        }
        if (newTempDependeds.size()==0){
            return node.finishTime;
        }else {
            long max = 0;
            for (int i=0;i<newTempDependeds.size();i++){
                Node temp = node.tempDependeds.get(i);
                if (temp.maxFinishTime==-1){
                    temp.maxFinishTime = getMaxFinishTimeInDependeds(temp);
                }
                if (temp.maxFinishTime>max){
                    max = temp.maxFinishTime;
                }
            }
            return max;
        }
    }


    /**
     * SP
     */
    static final String SP = "Smart_Priority_SP";
    static final String SP_KEY = "Smart_Priority_SP";

    private static JSONObject getSP() {
        SPHelper spHelper = new SPHelper(TaskManager.getContext());
        String jsonObjectString = spHelper.getString(SP,SP_KEY,"{}");
        try {
            return new JSONObject(jsonObjectString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void setSp(JSONObject jsonObject){
        SPHelper spHelper = new SPHelper(TaskManager.getContext());
        spHelper.putString(SP,SP_KEY,jsonObject.toString());
    }










}
