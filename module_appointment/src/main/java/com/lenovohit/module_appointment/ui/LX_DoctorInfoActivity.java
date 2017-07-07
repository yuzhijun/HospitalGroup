package com.lenovohit.module_appointment.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.DoctorAppoint;
import com.lenovohit.lartemis_api.ui.controller.AppointmentController;
import com.lenovohit.lartemis_api.utils.BlurUtil;
import com.lenovohit.module_appointment.R;
import com.lenovohit.module_appointment.ui.adapter.AppointmentDocArrangeAdapter;

import java.util.List;

/**
 * 医生主页
 * Created by yuzhijun on 2017/7/6.
 */
public class LX_DoctorInfoActivity extends CoreActivity<AppointmentController.AppointmentUiCallbacks> implements AppointmentController.AppointmentDocInfoUi{
    public static final String HOSP_ID = "HOSP_ID";
    public static final String DOCTOR_CODE = "DOCTOR_CODE";
    public static final String DEP_CODE ="DEP_CODE";
    public static final String TAG = "TAG";

    private AppBarLayout app_bar_layout;
    private Toolbar mToolbar;
    private LinearLayout head_layout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView rvDocAppointment;
    private GridLayoutManager mGridLayoutManager;
    private AppointmentDocArrangeAdapter mDocArrangeAdapter;

    private String tag;
    public String hospID;
    public String doctorCode;
    public String depCode;

    @Override
    protected int getLayoutId() {
        return R.layout.lx_appointment_doc_info_activity;
    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController().getAppointmentController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(false);
        tag = getIntent().getExtras().getString(TAG);
        hospID = getIntent().getExtras().getString(HOSP_ID);
        doctorCode = getIntent().getExtras().getString(DOCTOR_CODE);
        depCode = getIntent().getExtras().getString(DEP_CODE);

        app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        head_layout = (LinearLayout) findViewById(R.id.head_layout);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.lx_banner_main_page);
        head_layout.setBackgroundDrawable(new BitmapDrawable(BlurUtil.doBlur(bitmap, 20, 10)));
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctlDoctorInfo);
        mCollapsingToolbarLayout.setContentScrim(new BitmapDrawable(BlurUtil.doBlur(bitmap, 20, 10)));
        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -head_layout.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle("陈诗艺");
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });

        rvDocAppointment = (RecyclerView) findViewById(R.id.rvDocAppointment);
        mGridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false);
        rvDocAppointment.setLayoutManager(mGridLayoutManager);

    }

    @Override
    public void initEvent() {

    }

    public static void startDoctorInfoActivity(Context context, String hid, String doctorCode, String depCode, String tag){
        Intent intent = new Intent(context,LX_DoctorInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(HOSP_ID,hid);
        bundle.putString(DOCTOR_CODE,doctorCode);
        bundle.putString(DEP_CODE,depCode);
        bundle.putString(TAG,tag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void getDoctorAppointCallBack(List<DoctorAppoint> response) {

    }
}
