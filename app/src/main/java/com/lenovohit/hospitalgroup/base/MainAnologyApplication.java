package com.lenovohit.hospitalgroup.base;

import com.lenovohit.annotation.Application;
import com.lenovohit.lrouter_api.base.AnologyApplication;

/**
 * Created by yuzhijun on 2017/6/28.
 */
@Application(name = "com.lenovohit.hospitalgroup",priority = 999)
public class MainAnologyApplication extends AnologyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
