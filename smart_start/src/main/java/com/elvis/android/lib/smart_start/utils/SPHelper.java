package com.elvis.android.lib.smart_start.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;



public class SPHelper {



    Context context;
    public SPHelper(Context context){
        this.context = context;
    }


    /**
     * getSp
     */
    static final String DEFAULT_SP_SETTINGS = "default_sp_settings";
    public SharedPreferences getSp(String spName) {
        if (TextUtils.isEmpty(spName)) {
            spName = DEFAULT_SP_SETTINGS;
        }
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * getEditor
     */
    public SharedPreferences.Editor getEditor(String spName) {
        return getSp(spName).edit();
    }

    /**
     * contains
     */
    public boolean contains(String spName, String key) {
        return getSp(spName).contains(key);
    }

    /**
     * 接口：
     */
    public boolean getBoolean(String spName, String key, boolean defValue) {
        return getSp(spName).getBoolean(key, defValue);
    }

    public void putBoolean(String spName, String key, boolean value) {
        SharedPreferences.Editor editor = getEditor(spName);
        editor.putBoolean(key, value);
        editor.apply();
    }

    public float getFloat(String spName, String key, float defValue) {
        return getSp(spName).getFloat(key, defValue);
    }

    public void putFloat(String spName, String key, float value) {
        SharedPreferences.Editor editor = getEditor(spName);
        editor.putFloat(key, value);
        editor.apply();
    }

    public long getLong(String spName, String key, long defValue) {
        return getSp(spName).getLong(key, defValue);
    }

    public void putLong(String spName, String key, long value) {
        SharedPreferences.Editor editor = getEditor(spName);
        editor.putLong(key, value);
        editor.apply();
    }

    public int getInt(String spName, String key, int defValue) {
        return getSp(spName).getInt(key, defValue);
    }


    public void putInt(String spName, String key, int value) {
        SharedPreferences.Editor editor = getEditor(spName);
        editor.putInt(key, value);
        editor.apply();
    }


    public String getString(String spName, String key, String defValue) {
        return getSp(spName).getString(key, defValue);
    }


    public void putString(String spName, String key, String value) {
        SharedPreferences.Editor editor = getEditor(spName);
        editor.putString(key, value);
        editor.apply();
    }






}
