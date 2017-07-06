package com.lenovohit.lartemis_api.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2017-07-04.
 */

public class MyAlertView extends AlertDialog{
    protected MyAlertView(@NonNull Context context) {
        super(context);
    }

    protected MyAlertView(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MyAlertView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
