package com.elvis.android.lib.smartstart;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.elvis.android.lib.smart_start.SmartStart;
import com.elvis.android.lib.smartstart.module.ArticleModule;
import com.elvis.android.lib.smartstart.module.ChatModule;
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
    }







}
