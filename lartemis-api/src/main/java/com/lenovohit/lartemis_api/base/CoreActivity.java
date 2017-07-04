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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.lartemis_api.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.utils.DensityUtil;
import com.lenovohit.lartemis_api.views.LoadingView;

import static com.lenovohit.lartemis_api.R.id.btnLeft;
import static com.lenovohit.lartemis_api.R.id.btnRight;
import static com.lenovohit.lartemis_api.R.id.llLeft;
import static com.lenovohit.lartemis_api.R.id.llRight;
import static com.lenovohit.lartemis_api.R.id.tvLeft;
import static com.lenovohit.lartemis_api.R.id.tvRight;

/**
 * Created by yuzhijun on 2017/6/16.
 */
public abstract class CoreActivity<UC> extends BaseActivity<UC> {
    public static CoreActivity currentActivity;
    public static int ivBack=R.mipmap.lx_iv_top_back;
    private volatile boolean isLoading;
    private LoadingView progressDialog;
    private TextView mTvTitle;
    private ImageView mBtnLeft;
    private ImageView mBtnRight;
    private Toolbar mToolbar;
    private LinearLayout mLLContent;
    private TextView mTvLeft;
    private TextView mTvRight;
    private LinearLayout mLlLeft;
    private LinearLayout mLlRight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = this;
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
    private  void setToolbar(){
        super.setContentView(R.layout.layout_base_activity);
        //用来设置沉浸式状态栏
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = (TextView) findViewById(R.id.toolbar_title);
        mBtnRight = (ImageView) findViewById(btnRight);
        mBtnLeft = (ImageView) findViewById(btnLeft);
        mLLContent= (LinearLayout) findViewById(R.id.content);
        mLlLeft= (LinearLayout) findViewById(llLeft);
        mLlRight= (LinearLayout) findViewById(llRight);
        mTvLeft=(TextView) findViewById(tvLeft);
        mTvRight=(TextView) findViewById(tvRight);
        getLayoutInflater().inflate(getLayoutId(),this.mLLContent);
    }



    protected void isShowToolBar(boolean isShow){
        mToolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    protected void isShowLeft(boolean isShow){
        mBtnLeft.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    protected void isShowRight(boolean isShow){
        mBtnRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    protected void setCenterTitle(String title){
        mTvTitle.setText(title == null || "".equalsIgnoreCase(title) ? "" : title);
    }
    protected  void setRightTitle(String title, View.OnClickListener listener){
        if (title != null && !"".equalsIgnoreCase(title)){
            mTvRight.setVisibility(View.VISIBLE);
            mTvRight.setText(title);
            mBtnRight.setVisibility(View.GONE);
            mLlRight.setOnClickListener(listener);
        }
    }
    protected void setRightTitleAndIcon(String title,@DrawableRes int icon, View.OnClickListener listener){
        if (title != null && !"".equalsIgnoreCase(title)){
            mTvRight.setVisibility(View.VISIBLE);
            mTvRight.setText(title);
        }
        mBtnRight.setVisibility(View.VISIBLE);
        mBtnRight.setImageResource(icon);
        ViewGroup.LayoutParams linearParams = mBtnRight.getLayoutParams();
        linearParams.height = DensityUtil.dip2px(this,15);
        linearParams.width = DensityUtil.dip2px(this,15);
        mBtnRight.setLayoutParams(linearParams);
        mLlRight.setOnClickListener(listener);
    }
    protected  void setLeftTitle(String title, View.OnClickListener listener){
        if (title != null && !"".equalsIgnoreCase(title)){
            mTvLeft.setVisibility(View.VISIBLE);
            mTvLeft.setText(title);
            mBtnLeft.setVisibility(View.GONE);
            mLlLeft.setOnClickListener(listener);
        }
    }
    protected  void setLeftTitleColor(int resId){
        int color=getResources().getColor(resId);
        mTvLeft.setVisibility(View.VISIBLE);
        mTvLeft.setTextColor(color);
    }
    protected  void setRightTitleColor(int resId){
        int color=getResources().getColor(resId);
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setTextColor(color);
    }
    protected void setLeftTitleAndIcon(String title,@DrawableRes int icon, View.OnClickListener listener){
        if (title != null && !"".equalsIgnoreCase(title)){
            mTvLeft.setVisibility(View.VISIBLE);
            mTvLeft.setText(title);
        }
        mBtnLeft.setVisibility(View.VISIBLE);
        mBtnLeft.setBackgroundResource(icon);
        ViewGroup.LayoutParams linearParams = mBtnLeft.getLayoutParams();
        linearParams.height = DensityUtil.dip2px(this,15);
        linearParams.width = DensityUtil.dip2px(this,15);
        mBtnLeft.setLayoutParams(linearParams);
        mLlLeft.setOnClickListener(listener);
    }

    public void showProgressDialog() {
        if(!this.isLoading) {
            this.hideProgressDialog();
            this.isLoading = true;
            this.progressDialog = new LoadingView();
            this.progressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if(this.progressDialog != null) {
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.progressDialog = null;
        }
        this.isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    protected ImageView getLeftButton(){
        return mBtnLeft;
    }
    protected ImageView getRightButton(){
        return mBtnRight;
    }
//    public static int getIdByName(String var0, CommonUtil.ClassType var1) {
//        String var2 = LArtemis.getInstance().getApplication().getPackageName();
//        int var4 = 0;
//        String var5 = "";
//        switch($SWITCH_TABLE$com$mg$core$base$BaseActivity$ClassType()[var1.ordinal()]) {
//            case 1:
//                var5 = "id";
//                break;
//            case 2:
//                var5 = "layout";
//                break;
//            case 3:
//                var5 = "string";
//                break;
//            case 4:
//                var5 = "drawable";
//                break;
//            case 5:
//                var5 = "color";
//                break;
//            case 6:
//                var5 = "anim";
//                break;
//            case 7:
//                var5 = "style";
//        }
//
//        try {
//            Class[] var8 = Class.forName(var2 + ".R").getClasses();
//            Class var9 = null;
//
//            int var3;
//            for(var3 = 0; var3 < var8.length; ++var3) {
//                if(var8[var3].getName().split("\\$")[1].equals(var5)) {
//                    var9 = var8[var3];
//                    break;
//                }
//            }
//
//            if(var9 != null) {
//                try {
//                    var4 = var9.getField(var0).getInt(var9);
//                } catch (Exception var6) {
//                    if(var5.equals("drawable")) {
//                        var5 = "mipmap";
//
//                        for(var3 = 0; var3 < var8.length; ++var3) {
//                            if(var8[var3].getName().split("\\$")[1].equals(var5)) {
//                                var9 = var8[var3];
//                                break;
//                            }
//                        }
//
//                        if(var9 != null) {
//                            var4 = var9.getField(var0).getInt(var9);
//                        }
//                    }
//                }
//            }
//        } catch (Exception var7) {
//            var7.printStackTrace();
//        }
//
//        return var4;
//    }
}
