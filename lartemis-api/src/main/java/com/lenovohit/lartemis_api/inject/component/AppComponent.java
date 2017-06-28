package com.lenovohit.lartemis_api.inject.component;

import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.inject.module.ApiServiceModule;
import com.lenovohit.lartemis_api.network.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yuzhijun on 2017/6/15.
 */
@Singleton
@Component(modules = {
        ApiServiceModule.class
})
public interface AppComponent {
    ApiService getService();

    void inject(LArtemis lArtemis);
}
