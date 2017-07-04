package com.lenovohit.module_appointment.exposure;

import android.content.Context;
import android.content.Intent;

import com.lenovohit.annotation.Action;
import com.lenovohit.lrouter_api.core.LRAction;
import com.lenovohit.lrouter_api.core.LRActionResult;
import com.lenovohit.lrouter_api.core.LRouterRequest;
import com.lenovohit.module_appointment.ui.AppointmentMainActivity;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@Action(name = "EntranceAction",provider = "AppoinmentProvider")
public class EntranceAction extends LRAction {
    @Override
    public boolean needAsync(Context context, LRouterRequest requestData) {
        return true;
    }

    @Override
    public LRActionResult invoke(Context context, LRouterRequest requestData) {
        Intent intent = new Intent(context, AppointmentMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        LRActionResult result = new LRActionResult.Builder()
                .code(LRActionResult.RESULT_SUCESS)
                .msg("success")
                .build();
        return result;
    }
}
