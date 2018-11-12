package com.conghongjie.start.priority;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class TaskPrioritySaver {


    private static final String SP_NAME = "Smart_Start";

    private static final String PRIORITY_KEY = "Priority";

    public static JSONObject getPriorities(Context context) {
        String priorities = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(PRIORITY_KEY, "{}");
        try {
            return new JSONObject(priorities);
        } catch (JSONException e) {
        }
        return new JSONObject();
    }

    public static void savePriorities(Context context, JSONObject jsonObject) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(PRIORITY_KEY, jsonObject.toString());
        editor.apply();
    }


}
