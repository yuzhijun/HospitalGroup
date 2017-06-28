package com.lenovohit.lartemis_api.core;

import android.app.Application;

import com.lenovohit.lartemis_api.inject.component.AppComponent;
import com.lenovohit.lartemis_api.inject.component.DaggerAppComponent;
import com.lenovohit.lartemis_api.inject.module.ApiServiceModule;
import com.lenovohit.lartemis_api.ui.controller.MainController;

import javax.inject.Inject;

/**
 * 程序入口初始化类
 * Created by yuzhijun on 2017/6/27.
 */
public class LArtemis {
    private static LArtemis sInstance;
    private Application mApplication;
    private AppComponent appComponent;
    @Inject
    MainController mMainController;

    public MainController getMainController() {
        return mMainController;
    }

    private LArtemis(){
    }

    public static synchronized LArtemis getInstance(){
        if (null == sInstance){
            synchronized (LArtemis.class){
                if (null == sInstance){
                    sInstance = new LArtemis();
                }
            }
        }
        return sInstance;
    }

    public void init(Application application){
        this.mApplication = application;

        setupGraph();
    }

    private void setupGraph() {
        appComponent = DaggerAppComponent.builder()
                .apiServiceModule(new ApiServiceModule())
                .build();
        appComponent.inject(this);
    }

    public Application getApplication() {
        return mApplication;
    }
}
