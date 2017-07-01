package com.lenovohit.lartemis_api.views;

import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lenovohit.lartemis_api.R;
import com.lenovohit.lartemis_api.base.CoreActivity;

/**
 * 自定义加载view
 * Created by yuzhijun on 2017/7/1.
 */
public class LoadingView extends Dialog{
    private LoadingView currentDialog;
    private ImageView ivLoading;

    public LoadingView() {
        super(CoreActivity.currentActivity);
        currentDialog = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lx_loading_view_layout);
        Window window = this.getWindow();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);

        currentDialog.setCancelable(true);
        currentDialog.setCanceledOnTouchOutside(false);

        ivLoading = (ImageView) this.findViewById(R.id.ivLoading);
        ivLoading.setImageResource(R.mipmap.lx_iv_loading_view);
        ivLoading.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        Animation animation = AnimationUtils.loadAnimation(CoreActivity.currentActivity,R.anim.rotate_loading);
        ivLoading.startAnimation(animation);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        CoreActivity.currentActivity.setLoading(false);
    }
}
