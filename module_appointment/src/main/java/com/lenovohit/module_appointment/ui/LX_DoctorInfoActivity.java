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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lartemis_api.model.DoctorAppoint;
import com.lenovohit.lartemis_api.model.DoctorArrangeShow;
import com.lenovohit.lartemis_api.ui.controller.AppointmentController;
import com.lenovohit.lartemis_api.utils.BlurUtil;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.utils.SuplusUtil;
import com.lenovohit.module_appointment.R;
import com.lenovohit.module_appointment.ui.adapter.AppointmentDocArrangeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 医生主页
 * Created by yuzhijun on 2017/7/6.
 */
public class LX_DoctorInfoActivity extends CoreActivity<AppointmentController.AppointmentUiCallbacks> implements AppointmentController.AppointmentDocInfoUi{
    public static final String DOCTOR = "DOCTOR";
    public static final String TAG = "TAG";

    private AppBarLayout app_bar_layout;
    private Toolbar mToolbar;
    private LinearLayout llLeft;
    private LinearLayout head_layout;
    private LinearLayout llFocus;
    private TextView tvDocTypeLevel;
    private TextView tvDoctorName;
    private TextView tvSkilled;
    private TextView tvDoctorDetail;
    private TextView tvFocus;
    private TextView tvAppointNum;
    private TextView tvMoney;
    private TextView tvFocusContent;
    private SimpleDraweeView sdvDoctorLogo;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView rvDocAppointment;
    private GridLayoutManager mGridLayoutManager;
    private AppointmentDocArrangeAdapter mDocArrangeAdapter;
    private List<DoctorArrangeShow> mDoctorArrangeShows = new ArrayList<>();
    private List<DoctorAppoint> mDoctorAppoints = new ArrayList<>();

    private String tag;
    private Doctor doctor;


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
        doctor = (Doctor) getIntent().getExtras().getParcelable(DOCTOR);

        app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        llLeft = (LinearLayout) findViewById(R.id.llBackLeft);
        llFocus = (LinearLayout) findViewById(R.id.llFocus);
        tvDocTypeLevel = (TextView) findViewById(R.id.tvDocTypeLevel);
        tvDoctorName = (TextView) findViewById(R.id.tvDoctorName);
        tvSkilled = (TextView) findViewById(R.id.tvSkilled);
        tvDoctorDetail = (TextView) findViewById(R.id.tvDoctorDetail);
        tvFocus = (TextView) findViewById(R.id.tvFocus);
        tvAppointNum = (TextView) findViewById(R.id.tvAppointNum);
        tvMoney = (TextView) findViewById(R.id.tvMoney);
        tvFocusContent = (TextView) findViewById(R.id.tvFocusContent);
        sdvDoctorLogo = (SimpleDraweeView) findViewById(R.id.sdvDoctorLogo);

        tvDoctorName.setText(CommonUtil.isStrEmpty(doctor.getDoctorName())?"未知姓名":doctor.getDoctorName());
        tvDocTypeLevel.setText(CommonUtil.isStrEmpty(doctor.getJobName()+doctor.getDepName())?"暂无明确职称":doctor.getJobName()+" "+doctor.getDepName());
        sdvDoctorLogo.setImageURI(doctor.getPhotoUrl());
        tvSkilled.setText(CommonUtil.isStrEmpty(doctor.getExpert())?"暂无擅长说明":doctor.getExpert());
        tvDoctorDetail.setText(CommonUtil.isStrEmpty(doctor.getInfo())?"暂无医生简介,等待医生录入":doctor.getInfo());

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
                    mCollapsingToolbarLayout.setTitle(CommonUtil.isStrEmpty(doctor.getDoctorName())?"未知姓名":doctor.getDoctorName());
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });

        //设置排班布局
        try {
            rvDocAppointment = (RecyclerView) findViewById(R.id.rvDocAppointment);
            mGridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false);
            rvDocAppointment.setLayoutManager(mGridLayoutManager);
            mDocArrangeAdapter = new AppointmentDocArrangeAdapter(R.layout.lx_grid_doctor_arrange_item,SuplusUtil.getDoctorArrangeShows(mDoctorAppoints));
            rvDocAppointment.setAdapter(mDocArrangeAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取医生基本信息和医生排班信息
        getCallbacks().getDoctorBase(doctor.getHID(),doctor.getDepCode(),doctor.getDoctorCode(),
                (null == UserData.getTempUser() || null == UserData.getTempUser().getBaseInfo())?"0":UserData.getTempUser().getBaseInfo().getUID());
        getCallbacks().getDoctorAppoint(doctor.getHID(),doctor.getDoctorCode(),doctor.getDepCode(),tag);
    }

    @Override
    public void initEvent() {
        llLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        llFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public static void startDoctorInfoActivity(Context context, Doctor doctor, String tag){
        Intent intent = new Intent(context,LX_DoctorInfoActivity.class);
//        int myProcessId = ProcessUtil.getMyProcessId();
//        String processName = ProcessUtil.getProcessName(context, myProcessId);
//        if (processName.equals("com.lenovohit.hospitalgroup:module_appointment")){
//        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DOCTOR,doctor);
        bundle.putSerializable(TAG,tag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void getDoctorAppointCallBack(List<DoctorAppoint> response) {
        if (null == response || response.size() <= 0) return;
        try {
            mDocArrangeAdapter.getData().clear();
            mDoctorArrangeShows = SuplusUtil.getDoctorArrangeShows(response);
            mDocArrangeAdapter.setNewData(mDoctorArrangeShows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDoctorBaseCallBack(Doctor doctor) {
        if (null == doctor)return;

        tvFocus.setText(CommonUtil.isStrEmpty(doctor.getFocus())?"0":doctor.getFocus());
        tvAppointNum.setText(CommonUtil.isStrEmpty(doctor.getAppointmentNum())?"0":doctor.getAppointmentNum());
        tvMoney.setText(CommonUtil.isStrEmpty(doctor.getOnlineMoney())?"":doctor.getOnlineMoney());
        if (!CommonUtil.isStrEmpty(doctor.getIsCollection()) && Constants.IS_COLLECTION.equalsIgnoreCase(doctor.getIsCollection())){
            tvFocusContent.setText("已关注");
        }else{
            tvFocusContent.setText("未关注");
        }
    }
}
