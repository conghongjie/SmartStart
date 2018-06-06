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
    public String getDefaultPriorities() {
        return "{\"Module_72\":808,\"Module_71\":1400,\"Module_d4\":200,\"Module_d3\":600,\"Module_d2\":600,\"Module_d1\":600,\"Module_e1\":200,\"Module_i2\":400,\"Module_i1\":200,\"Module_34\":808,\"Module_33\":808,\"Module_32\":200,\"Module_31\":808,\"Module_62\":200,\"Module_61\":400,\"Module_c6\":200,\"Module_c5\":200,\"Module_c4\":200,\"Module_c3\":200,\"Module_c2\":400,\"Module_c1\":400,\"Chat_DelayTask_2\":701,\"Module_h2\":200,\"Module_h1\":200,\"Chat_DelayTask_1\":1601,\"Module_26\":200,\"Module_25\":200,\"Module_24\":600,\"Module_23\":2000,\"Module_22\":808,\"Module_21\":2200,\"Module_51\":1600,\"Module_91\":1200,\"Module_b5\":800,\"Module_b4\":200,\"Module_b3\":200,\"Module_b2\":203,\"Module_b1\":400,\"Module_g2\":200,\"Module_g1\":200,\"Module_15\":600,\"Module_14\":200,\"Module_13\":808,\"Module_12\":600,\"Module_11\":600,\"Module_52\":604,\"Module_82\":200,\"Module_81\":400,\"Video_ApplicationTask_1\":200,\"Article_ApplicationTask_2\":900,\"Article_ApplicationTask_1\":1800,\"Module_a4\":600,\"Module_a3\":400,\"Module_a2\":1000,\"Module_a1\":200,\"Module_f2\":200,\"Module_f1\":400,\"Chat_ApplicationTask_2\":700,\"Chat_ApplicationTask_1\":900,\"Module_j1\":200,\"Module_04\":2400,\"Module_03\":200,\"Module_02\":200,\"Module_01\":2600,\"Article_DelayTask_2\":1401,\"Article_DelayTask_1\":1601,\"Module_41\":600}\n";
    }

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
