package com.lenovohit.hospitalgroup.base;

import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lrouter_api.base.LRouterAppcation;

/**
 * 系统基application
 * Created by yuzhijun on 2017/6/28.
 */
public class MyApplication extends LRouterAppcation {

    @Override
    public void onCreate() {
        super.onCreate();
        LArtemis.getInstance().init(this);
    }

    @Override
    public boolean needMultipleProcess() {
        return true;
    }
}
