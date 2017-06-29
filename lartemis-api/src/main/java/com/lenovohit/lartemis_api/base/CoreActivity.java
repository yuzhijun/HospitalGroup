package com.lenovohit.lartemis_api.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.lartemis_api.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.utils.DensityUtil;

/**
 * Created by yuzhijun on 2017/6/16.
 */
public abstract class CoreActivity<UC> extends BaseActivity<UC> {
    private TextView tvTitle;
    private Button btnLeft;
    private Button btnRight;
    private Toolbar mToolbar;
    private LinearLayout mLLContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
        initView(savedInstanceState);
        initEvent();
    }

    public abstract void initView(@Nullable Bundle savedInstanceState);
    public abstract void initEvent();

    @Override
    protected int getLayoutId() {
        for (Class c = getClass(); c != Context.class; c = c.getSuperclass()) {
            ContentView annotation = (ContentView) c.getAnnotation(ContentView.class);
            if (annotation != null) {
                return annotation.value();
            }
        }
        return 0;
    }
    protected void addFragment(int containerViewId, Fragment fragment , String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(containerViewId, fragment , tag);
        fragmentTransaction.commit();
    }

    /**
     * 1.设置沉浸式
     * 2.初始化toolbar中控件
     */
    public void setToolbar(){
        super.setContentView(R.layout.layout_base_activity);
        //用来设置沉浸式状态栏
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        mLLContent= (LinearLayout) findViewById(R.id.content);

        getLayoutInflater().inflate(getLayoutId(),this.mLLContent);
    }

    protected void addFragment(int containerViewId, Fragment fragment , String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(containerViewId, fragment , tag);
        fragmentTransaction.commit();
    }

    protected void isShowToolBar(boolean isShow){
        mToolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    protected void isShowLeft(boolean isShow){
        btnLeft.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    protected void isShowRight(boolean isShow){
        btnRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    protected void setCenterTitle(String title){
        tvTitle.setText(title == null || "".equalsIgnoreCase(title) ? "" : title);
    }
    protected  void setRightTitle(String title, View.OnClickListener listener){
        if (title != null && !"".equalsIgnoreCase(title)){
            btnRight.setVisibility(View.VISIBLE);
            btnRight.setText(title);
            btnRight.setOnClickListener(listener);
        }
    }
    protected void setRightIcon(@DrawableRes int icon, View.OnClickListener listener){
        btnRight.setVisibility(View.VISIBLE);
        btnRight.setBackgroundResource(icon);
        ViewGroup.LayoutParams linearParams = btnRight.getLayoutParams();
        linearParams.height = DensityUtil.dip2px(this,26);
        linearParams.width = DensityUtil.dip2px(this,26);
        btnRight.setLayoutParams(linearParams);
        btnRight.setOnClickListener(listener);
    }
    protected  void setLeftTitle(String title, View.OnClickListener listener){
        if (title != null && !"".equalsIgnoreCase(title)){
            btnLeft.setVisibility(View.VISIBLE);
            btnLeft.setText(title);
            btnLeft.setOnClickListener(listener);
        }
    }
    protected  void setLeftTitleColor(int resId){
        int color=getResources().getColor(resId);
        btnLeft.setVisibility(View.VISIBLE);
        btnLeft.setTextColor(color);
    }
    protected  void setRightTitleColor(int resId){
        int color=getResources().getColor(resId);
        btnLeft.setVisibility(View.VISIBLE);
        btnLeft.setTextColor(color);
    }
    protected void setLeftIcon(@DrawableRes int icon, View.OnClickListener listener){
        btnLeft.setVisibility(View.VISIBLE);
        btnLeft.setBackgroundResource(icon);
        ViewGroup.LayoutParams linearParams = btnLeft.getLayoutParams();
        linearParams.height = DensityUtil.dip2px(this,26);
        linearParams.width = DensityUtil.dip2px(this,26);
        btnLeft.setLayoutParams(linearParams);
        btnLeft.setOnClickListener(listener);
    }
    protected Button getLeftButton(){
        return btnLeft;
    }
    protected Button getRightButton(){
        return btnRight;
    }
}
