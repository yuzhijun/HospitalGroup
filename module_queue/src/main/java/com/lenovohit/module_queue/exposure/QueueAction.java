package com.lenovohit.module_queue.exposure;

import android.content.Context;
import android.content.Intent;

import com.lenovohit.annotation.Action;
import com.lenovohit.lrouter_api.core.LRAction;
import com.lenovohit.lrouter_api.core.LRActionResult;
import com.lenovohit.lrouter_api.core.LRouterRequest;
import com.lenovohit.module_queue.ui.LX_QueueUpActivity;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@Action(name = "QueueAction",provider = "QueueProvider")
public class QueueAction extends LRAction {
    @Override
    public boolean needAsync(Context context, LRouterRequest requestData) {
        return true;
    }

    @Override
    public LRActionResult invoke(Context context, LRouterRequest requestData) {
        Intent intent = new Intent(context,LX_QueueUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        LRActionResult result = new LRActionResult.Builder()
                .code(LRActionResult.RESULT_SUCESS)
                .msg("success")
                .build();
        return result;
    }
}
