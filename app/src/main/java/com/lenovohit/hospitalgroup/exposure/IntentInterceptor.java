package com.lenovohit.hospitalgroup.exposure;

import android.content.Intent;

import com.lenovohit.lrouter_api.intercept.StartupInterceptor;

/**
 * Created by yuzhijun on 2017/6/28.
 */
@com.lenovohit.annotation.IntentInterceptor
public class IntentInterceptor extends StartupInterceptor {
    @Override
    public void intentIntercept(Object targetActivity, Intent intent) {

    }
}
