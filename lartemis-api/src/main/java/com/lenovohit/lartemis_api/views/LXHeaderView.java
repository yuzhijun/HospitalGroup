package com.lenovohit.lartemis_api.views;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovohit.lartemis_api.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 自定义下拉刷新
 * Created by yuzhijun on 2017/6/29.
 */
public class LXHeaderView extends PtrFrameLayout implements PtrUIHandler {
    private TextView tvStatus;
    private ImageView ivStature;
    private ImageView ivBox;
    private ImageView ivAnimation;
    private AnimationDrawable drawable;
    private RefreshDistanceListener listener;
    /**
     * 自开始下拉 0.2倍height内是否执行了缩放，
     */
    private boolean isScale;
    private int viewHeight;

    public void setOnRefreshDistanceListener(RefreshDistanceListener listener) {
        this.listener = listener;
    }

    public LXHeaderView(Context context) {
        super(context);
        initView();
    }

    public LXHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LXHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        View view = View.inflate(this.getContext(), R.layout.lx_refresh_header_view,null);
        tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        ivStature = (ImageView) view.findViewById(R.id.ivStature);
        ivBox = (ImageView) view.findViewById(R.id.ivBox);
        ivAnimation = (ImageView) view.findViewById(R.id.ivAnimation);
        drawable = (AnimationDrawable) ivAnimation.getDrawable();
        setRatioOfHeaderHeightToRefresh(1.0f);
        setHeaderView(view);
        addPtrUIHandler(this);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        drawable.stop();
        ivAnimation.setVisibility(View.GONE);
        ivStature.setVisibility(View.VISIBLE);
        ivBox.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        if(frame.isPullToRefresh()){
            tvStatus.setText("松开刷新...");
        }else{
            tvStatus.setText("下拉刷新...");
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        ivStature.setVisibility(View.GONE);
        ivBox.setVisibility(View.GONE);
        ivAnimation.setVisibility(View.VISIBLE);
        drawable.start();
        tvStatus.setText("更新中...");
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //ptrIndicator.setRatioOfHeaderHeightToRefresh(1.0f);
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
        if(listener != null){
            listener.onPositionChange(currentPos);
        }
        if(viewHeight == 0)
            viewHeight = ptrIndicator.getHeaderHeight();
        float v = currentPos * 1.0f / viewHeight;
        if(v > 1)v= 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //此处防止首次下拉到0.2height时突然缩小
            if(!isScale && v <= 0.2){
                isScale = true;
                setImgScale(0.2f);
            }
            if(v > 0.2){
                setImgScale(v);
            }
        }

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                tvStatus.setText("下拉刷新...");

            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                tvStatus.setText("松开刷新...");
            }
        }
    }

    public interface RefreshDistanceListener{
        void onPositionChange(int currentPosY);
    }

    private void setImgScale(float v) {
        ivStature.setScaleY(v);
        ivStature.setScaleX(v);
        ivBox.setScaleY(v);
        ivBox.setScaleX(v);
    }
}
