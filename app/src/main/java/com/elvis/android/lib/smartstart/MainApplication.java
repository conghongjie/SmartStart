package com.elvis.android.lib.smartstart;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.elvis.android.lib.smart_start.SmartStart;
import com.elvis.android.lib.smartstart.module.ArticleModule;
import com.elvis.android.lib.smartstart.module.ChatModule;
import com.elvis.android.lib.smartstart.module.MoreModule;
import com.elvis.android.lib.smartstart.module.VideoModule;

/**
 *
 * Created by conghongjie on 2018/5/23.
 */
public class MainApplication extends AbsApplication {


    @Override
    public void buildSmartStart() {
        // 构建启动器
        SmartStart.addModuleStart(ArticleModule.articleModuleStart);
        SmartStart.addModuleStart(VideoModule.videoModuleStart);
        SmartStart.addModuleStart(ChatModule.chatModuleStart);


        SmartStart.addModuleStart(MoreModule.Module_2);
        SmartStart.addModuleStart(MoreModule.Module_6);
        SmartStart.addModuleStart(MoreModule.Module_7);
        SmartStart.addModuleStart(MoreModule.Module_9);
        SmartStart.addModuleStart(MoreModule.Module_3);
        SmartStart.addModuleStart(MoreModule.Module_0);
        SmartStart.addModuleStart(MoreModule.Module_1);
        SmartStart.addModuleStart(MoreModule.Module_8);
        SmartStart.addModuleStart(MoreModule.Module_4);
        SmartStart.addModuleStart(MoreModule.Module_5);


        SmartStart.addModuleStart(MoreModule.Module_d);
        SmartStart.addModuleStart(MoreModule.Module_e);
        SmartStart.addModuleStart(MoreModule.Module_g);
        SmartStart.addModuleStart(MoreModule.Module_j);
        SmartStart.addModuleStart(MoreModule.Module_a);
        SmartStart.addModuleStart(MoreModule.Module_c);
        SmartStart.addModuleStart(MoreModule.Module_i);
        SmartStart.addModuleStart(MoreModule.Module_b);
        SmartStart.addModuleStart(MoreModule.Module_h);
        SmartStart.addModuleStart(MoreModule.Module_f);
    }







}
