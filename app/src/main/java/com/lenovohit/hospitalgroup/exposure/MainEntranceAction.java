package com.lenovohit.hospitalgroup.exposure;

import android.content.Context;
import android.content.Intent;

import com.lenovohit.annotation.Action;
import com.lenovohit.hospitalgroup.ui.LX_SwitchPatientManagerActivity;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lrouter_api.core.LRAction;
import com.lenovohit.lrouter_api.core.LRActionResult;
import com.lenovohit.lrouter_api.core.LRouterRequest;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@Action(name = "MainEntranceAction",provider = "main")
public class MainEntranceAction extends LRAction {
    @Override
    public boolean needAsync(Context context, LRouterRequest requestData) {
        return true;
    }

    @Override
    public LRActionResult invoke(Context context, LRouterRequest requestData) {
        Intent intent = new Intent(context, LX_SwitchPatientManagerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.PUT_TYPE,(String)requestData.getParams().get(Constants.PUT_TYPE));
        context.startActivity(intent);

        LRActionResult result = new LRActionResult.Builder()
                .code(LRActionResult.RESULT_SUCESS)
                .msg("success")
                .build();
        return result;
    }
}
