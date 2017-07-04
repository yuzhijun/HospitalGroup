package com.lenovohit.lartemis_api.ui.controller;

import com.google.common.base.Preconditions;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.network.ApiService;

import javax.inject.Inject;

/**
 * Created by yuzhijun on 2017/6/27.
 */

public class AppointmentController extends BaseController<AppointmentController.AppointmentUi,AppointmentController.AppointmentUiCallbacks> {
    private ApiService mApiService;

    @Inject
    public AppointmentController(ApiService apiService){
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
    protected synchronized void populateUi(AppointmentUi ui) {
        super.populateUi(ui);
    }

    @Override
    protected AppointmentUiCallbacks createUiCallbacks(AppointmentUi ui) {
        return new AppointmentUiCallbacks() {

        };
    }

    public interface AppointmentUiCallbacks{//给UI界面调用

    }

    public interface AppointmentUi extends BaseController.Ui<AppointmentController.AppointmentUiCallbacks> {
        //这里面的方式是给UI界面调用的，要activity等UI层实现
    }

    public interface AppointmentMainUi extends AppointmentUi{

    }
}
