package com.lenovohit.module_appointment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.AppointmentController;
import com.lenovohit.module_appointment.R;

/**
 * 手机预约首界面
 * Created by yuzhijun on 2017/6/29.
 */
public class LX_AppointmentMainActivity extends CoreActivity<AppointmentController.AppointmentUiCallbacks> implements AppointmentController.AppointmentMainUi {

    @Override
    protected int getLayoutId() {
        return R.layout.lx_appointment_dept_tree_activity;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("手机预约");

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected BaseController getController() {
        return  LArtemis.getInstance().getMainController().getAppointmentController();
    }
}
