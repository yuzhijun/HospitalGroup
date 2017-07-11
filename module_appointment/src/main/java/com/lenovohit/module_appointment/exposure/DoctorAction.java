package com.lenovohit.module_appointment.exposure;

import android.content.Context;

import com.lenovohit.annotation.Action;
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lrouter_api.core.LRAction;
import com.lenovohit.lrouter_api.core.LRActionResult;
import com.lenovohit.lrouter_api.core.LRouterRequest;
import com.lenovohit.module_appointment.ui.LX_DoctorInfoActivity;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@Action(name = "DoctorAction",provider = "AppoinmentProvider")
public class DoctorAction extends LRAction {
    @Override
    public boolean needAsync(Context context, LRouterRequest requestData) {
        return true;
    }

    @Override
    public LRActionResult invoke(Context context, LRouterRequest requestData) {
        Doctor doctor = (Doctor) requestData.getRequestObject();
        LX_DoctorInfoActivity.startDoctorInfoActivity(context,doctor,"zxyy");
        LRActionResult result = new LRActionResult.Builder()
                .code(LRActionResult.RESULT_SUCESS)
                .msg("success")
                .build();
        return result;
    }
}
