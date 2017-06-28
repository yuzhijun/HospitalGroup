package com.lenovohit.lartemis_api.ui.controller;

import com.google.common.base.Preconditions;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.network.ApiService;

import javax.inject.Inject;

/**
 * Created by yuzhijun on 2017/6/27.
 */

public class SecondController extends BaseController<SecondController.SecondUi,SecondController.SecondUiCallbacks> {
    private ApiService mApiService;

    @Inject
    public  SecondController(ApiService apiService){
        super();
        mApiService = Preconditions.checkNotNull(apiService, "ApiService cannot be null");
    }

    @Override
    protected void onInited() {
        super.onInited();
    }

    @Override
    protected void onSuspended() {
        super.onSuspended();
    }

    @Override
    protected synchronized void populateUi(SecondUi ui) {
        super.populateUi(ui);
    }

    @Override
    protected SecondUiCallbacks createUiCallbacks(SecondUi ui) {
        return new SecondUiCallbacks() {
            @Override
            public void getWeatherInfo() {

            }

            @Override
            public void getWeatherInfo1() {

            }
        };
    }

    public interface SecondUiCallbacks{//给UI界面调用
        void getWeatherInfo();
        void getWeatherInfo1();
    }

    public interface SecondUi extends BaseController.Ui<SecondController.SecondUiCallbacks> {
        //这里面的方式是给UI界面调用的，要activity等UI层实现
        void showToast();
    }

    public interface thirdUi extends SecondUi{
        void showToast1();
    }
}
