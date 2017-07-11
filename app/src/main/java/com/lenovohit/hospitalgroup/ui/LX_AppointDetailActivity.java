package com.lenovohit.hospitals.ui.one;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.hospitals.R;
import com.lenovohit.hospitals.data.AppointData;
import com.lenovohit.hospitals.data.CommonData;
import com.lenovohit.hospitals.domain.Appoint;
import com.lenovohit.hospitals.domain.Result;
import com.lenovohit.hospitals.utils.Constant;
import com.lenovohit.hospitals.utils.ViewUtil;
import com.lenovohit.hospitals.view.AlertDialog;
import com.lenovohit.hospitals.view.MyScrollerView;
import com.mg.core.base.BaseActivity;
import com.mg.core.net.OperateCode;
import com.mg.core.net.ThreadMessage;

/**
 * 预约成功详情页面
 * Created by yuzhijun on 2016/7/13.
 */
public class LX_AppointDetailActivity extends BaseActivity implements MyScrollerView.OnScrollListener {
    private static final String APPOINTMENT_OBJ = "appointmentOBJ";
    private static final String TAG = "tag";
    private static final String AID = "aid";
    private Appoint appoint;
    private MyScrollerView myScrollerView;
    private LinearLayout appointment_status;
    private LinearLayout top_appointment_status;
    private LinearLayout llCommit;
    private TextView tvAppState;
    private TextView tvHospitalName;
    private TextView tvDepName;
    private TextView tvDoctorName;
    private TextView tvOutpatientType;
    private TextView tvMoney;
    private TextView tvAppTime;
    private TextView tvPatientName;
    private TextView tvPatientPhone;
    private TextView tvHospitalCard;
    private Button btnAppointmentCommit;
    private String tag = "";
    private String aID = "";

    @Override
    protected void initViews() {
        setContentView(R.layout.lx_appoint_detail_activity);

        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.SetTvName("预约详情");
        mBottombar.setVisibility(View.GONE);

        myScrollerView  = (MyScrollerView) findViewById(R.id.myScrollerView);
        appointment_status = (LinearLayout) findViewById(R.id.appointment_status);
        top_appointment_status = (LinearLayout) findViewById(R.id.top_appointment_status);
        llCommit = (LinearLayout) findViewById(R.id.llCommit);
        myScrollerView.setOnScrollListener(this);
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(myScrollerView.getScrollY());
            }
        });
        tag = getIntent().getStringExtra(TAG);
        if (!ViewUtil.isStrEmpty(tag)){
            aID = getIntent().getStringExtra(AID);
        }else{
            appoint = (Appoint) getIntent().getSerializableExtra(APPOINTMENT_OBJ);
        }

        tvAppState = (TextView) top_appointment_status.findViewById(R.id.tvAppState);
        tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
        tvDepName = (TextView) findViewById(R.id.tvDepName);
        tvDoctorName = (TextView) findViewById(R.id.tvDoctorName);
        tvOutpatientType = (TextView) findViewById(R.id.tvOutpatientType);
        tvMoney = (TextView) findViewById(R.id.tvMoney);
        tvAppTime = (TextView) findViewById(R.id.tvAppTime);
        tvPatientName = (TextView) findViewById(R.id.tvPatientName);
        tvPatientPhone = (TextView) findViewById(R.id.tvPatientPhone);
        tvHospitalCard = (TextView) findViewById(R.id.tvHospitalCard);
        btnAppointmentCommit = (Button) findViewById(R.id.btnAppointmentCommit);

        if (ViewUtil.isStrEmpty(tag)){
            initViewContent();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!ViewUtil.isStrEmpty(tag)){
            getAppointmentHistory();
        }
    }

    public void initViewContent(){
        String state = appoint.getState();
        if (!ViewUtil.isStrEmpty(state)){
            if (Constant.CANCEL_APPOINTMENT.equals(state) || Constant.EXPIRED.equals(state)){
                tvAppState.setText(ViewUtil.isStrEmpty(state)?"状态未知":Constant.CANCEL_APPOINTMENT.equals(state)?"已取消":"已过期");
                llCommit.setVisibility(View.GONE);
            }else if(Constant.APPOINTMENT.equals(state)){
                tvAppState.setText(ViewUtil.isStrEmpty(state)?"状态未知":"已预约");
            }else if (Constant.REGISTER.equals(state)){
                tvAppState.setText(ViewUtil.isStrEmpty(state)?"状态未知":"已挂号");
                llCommit.setVisibility(View.GONE);
            }else if(Constant.VISITING.equals(state)){
                tvAppState.setText(ViewUtil.isStrEmpty(state)?"状态未知":"正在就诊");
            }else if(Constant.VISITEND.equals(state)){
                tvAppState.setText(ViewUtil.isStrEmpty(state)?"状态未知":"已就诊");
            }
        }

        tvHospitalName.setText(ViewUtil.isStrEmpty(appoint.getHospitalName())?"":appoint.getHospitalName());
        tvDepName.setText(ViewUtil.isStrEmpty(appoint.getDepName())?"":appoint.getDepName());
        tvDoctorName.setText(ViewUtil.isStrEmpty(appoint.getDoctorName())?"":appoint.getDoctorName());
        tvOutpatientType.setText(ViewUtil.isStrEmpty(appoint.getAppTypeName())?"":appoint.getAppTypeName());
        tvMoney.setText(appoint.getMoney()==0?"0元":appoint.getMoney()+"元");
        tvAppTime.setText(ViewUtil.isStrEmpty(appoint.getAppTime())?"":appoint.getAppTime().length() > 16 ? appoint.getAppTime().substring(0,16):appoint.getAppTime());

        tvPatientName.setText(ViewUtil.isStrEmpty(appoint.getPName())?"":appoint.getPName());
        tvPatientPhone.setText(ViewUtil.isStrEmpty(appoint.getPPhone())?"":appoint.getPPhone());
        tvHospitalCard.setText(ViewUtil.isStrEmpty(appoint.getHospitalCard())?"":appoint.getHospitalCard());
    }

    @Override
    protected void initEvents() {
        btnAppointmentCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(LX_AppointDetailActivity.this).builder()
                        .setTitle("提示")
                        .setMsg("您确定要取消吗？")
                        .setCancelable(false)
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getCancelAppointment();
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
            }
        });
    }

    @Override
    protected void receiveRequest(ThreadMessage threadMessage) {
        hideProgressDialog();
        OperateCode operateCode = threadMessage.getOperateCode();
        switch (operateCode){
            case i_UnAppointment:
                Result result = CommonData.getResult();
                if (!ViewUtil.isNotEmpty(result) || result.getState() <= 0){
                    ViewUtil.showToast(ViewUtil.isNotEmpty(result)?result.getMsg():"出现错误啦,请重新尝试看看",false);
                    return;
                }
                ViewUtil.showToast("取消预约成功",false);
                finish();
                break;
            case i_getAppointmentHistory:
                Appoint tempAppoint = AppointData.getTempAppoint();
                if (!ViewUtil.isNotEmpty(tempAppoint)){
                    ViewUtil.showToast("暂无你的预约详情",false);
                    return;
                }
                appoint = tempAppoint;
                initViewContent();
            default:
                break;
        }

    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, appointment_status.getTop());
        top_appointment_status.layout(0, mBuyLayout2ParentTop, top_appointment_status.getWidth(), mBuyLayout2ParentTop + top_appointment_status.getHeight());
    }

    public void getCancelAppointment(){
        showProgressDialog();
        sendToBackgroud(OperateCode.i_UnAppointment,new String[]{appoint.getAppointmentID(),appoint.getDoAID(),appoint.getHID()});
    }

    public void getAppointmentHistory(){
        showProgressDialog();
        sendToBackgroud(OperateCode.i_getAppointmentHistory,new String[]{aID});
    }

    public static void startAppointDetailActivity(Context context, Appoint appoint){
        Intent intent = new Intent(context,LX_AppointDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(APPOINTMENT_OBJ,appoint);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    //推送过来使用
    public static void startAppointDetailActivity(Context context,String aid,String tag){
        Intent intent = new Intent(context,LX_AppointDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(AID,aid);
        intent.putExtra(TAG,tag);
        context.startActivity(intent);
    }
}
