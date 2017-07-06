package com.lenovohit.module_appointment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.AppointmentController;

/**
 * Created by yuzhijun on 2017/7/6.
 */
public class LX_AppointmentDocActivity extends CoreActivity<AppointmentController.AppointmentUiCallbacks> implements AppointmentController.AppointmentDocUi{

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController().getAppointmentController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initEvent() {

    }
}
