package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.HospitalData;
import com.lenovohit.lartemis_api.model.Appoint;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.views.AlertDialog;
import com.lenovohit.lartemis_api.views.MyScrollerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.lenovohit.hospitalgroup.R.id.tvDepName;
import static com.lenovohit.hospitalgroup.R.id.tvHospitalCard;
import static com.lenovohit.hospitalgroup.R.id.tvPatientName;

/**
 * 预约成功详情页面
 * Created by yuzhijun on 2016/7/13.
 */
@ContentView(R.layout.lx_mine_appoint_detail_activity)
public class LX_AppointDetailActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.AppointmentHistoryDetailUi, MyScrollerView.OnScrollListener {


    @BindView(R.id.tvHospitalName)
    TextView mTvHospitalName;
    @BindView(tvDepName)
    TextView mTvDepName;
    @BindView(R.id.tvDoctorName)
    TextView mTvDoctorName;
    @BindView(R.id.tvOutpatientType)
    TextView mTvOutpatientType;
    @BindView(R.id.tvMoney)
    TextView mTvMoney;
    @BindView(R.id.tvAppTime)
    TextView mTvAppTime;
    @BindView(tvPatientName)
    TextView mTvPatientName;
    @BindView(R.id.tvPatientPhone)
    TextView mTvPatientPhone;
    @BindView(tvHospitalCard)
    TextView mTvHospitalCard;
    @BindView(R.id.myScrollerView)
    MyScrollerView mMyScrollerView;
    @BindView(R.id.btnAppointmentCommit)
    Button mBtnAppointmentCommit;
    @BindView(R.id.llCommit)
    LinearLayout mLlCommit;
    @BindView(R.id.parent_layout)
    LinearLayout mParentLayout;
    @BindView(R.id.appointment_status)
    LinearLayout mAppointmentStatus;
    @BindView(R.id.top_appointment_status)
    LinearLayout mTopAppointmentStatus;
    private Unbinder mBind;
    private TextView tvAppState;
    private Appoint appoint;
    private static final String APPOINTMENT_OBJ = "appointmentOBJ";
    private static final String TAG = "tag";
    private static final String AID = "aid";
    private String tag = "";
    private String aID = "";
    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mBind = ButterKnife.bind(this);
        isShowToolBar(true);
        setCenterTitle("预约详情");
        tvAppState = (TextView) mTopAppointmentStatus.findViewById(R.id.tvAppState);
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(mMyScrollerView.getScrollY());
            }
        });
        //tag用来标识是否传递对象
        tag = getIntent().getStringExtra(TAG);
        if (!CommonUtil.isStrEmpty(tag)){
            aID = getIntent().getStringExtra(AID);
        }else{
            appoint = (Appoint) getIntent().getSerializableExtra(APPOINTMENT_OBJ);
            initViewContent();
        }
    }

    @Override
    public void initEvent() {
        mBtnAppointmentCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(LX_AppointDetailActivity.this).builder()
                        .setCancelable(false)
                        .setMsg("您确定要取消吗?")
                        .setTitle("提示")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (appoint!= null){
                                    Log.e("tag",appoint.toString());
                                    getCallbacks().unAppoint(appoint.getAID(),appoint.getDoAID(), HospitalData.getCurrentHospital().getHID());
                                }
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        });
    }

    @Override
    public void getAppointDetailCallBack(Appoint tempAppoint) {
        if (tempAppoint!= null){
            this.appoint=tempAppoint;
            initViewContent();
        }
    }

    @Override
    public void unAppointCallback(Result result) {
        if (result!=null && result.getState()>0){
            CommonUtil.showSnackBar(mLlCommit,"取消预约成功!");
        }
    }

    public void initViewContent(){
        String state = appoint.getState();
        if (!CommonUtil.isStrEmpty(state)){
            if (Constants.CANCEL_APPOINTMENT.equals(state) || Constants.EXPIRED.equals(state)){
                tvAppState.setText(CommonUtil.isStrEmpty(state)?"状态未知":Constants.CANCEL_APPOINTMENT.equals(state)?"已取消":"已过期");
                mLlCommit.setVisibility(View.GONE);
            }else if(Constants.APPOINTMENT.equals(state)){
                tvAppState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"已预约");
            }else if (Constants.REGISTER.equals(state)){
                tvAppState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"已挂号");
                mLlCommit.setVisibility(View.GONE);
            }else if(Constants.VISITING.equals(state)){
                tvAppState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"正在就诊");
            }else if(Constants.VISITEND.equals(state)){
                tvAppState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"已就诊");
            }
        }

        mTvHospitalName.setText(CommonUtil.isStrEmpty(appoint.getHospitalName())?"":appoint.getHospitalName());
        mTvDepName.setText(CommonUtil.isStrEmpty(appoint.getDepName())?"":appoint.getDepName());
        mTvDoctorName.setText(CommonUtil.isStrEmpty(appoint.getDoctorName())?"":appoint.getDoctorName());
        mTvOutpatientType.setText(CommonUtil.isStrEmpty(appoint.getAppTypeName())?"":appoint.getAppTypeName());
        mTvMoney.setText(appoint.getMoney()==0?"0元":appoint.getMoney()+"元");
        mTvAppTime.setText(CommonUtil.isStrEmpty(appoint.getAppTime())?"":appoint.getAppTime().length() > 16 ? appoint.getAppTime().substring(0,16):appoint.getAppTime());

        mTvPatientName.setText(CommonUtil.isStrEmpty(appoint.getPName())?"":appoint.getPName());
        mTvPatientPhone.setText(CommonUtil.isStrEmpty(appoint.getPPhone())?"":appoint.getPPhone());
        mTvHospitalCard.setText(CommonUtil.isStrEmpty(appoint.getHospitalCard())?"":appoint.getHospitalCard());
    }



    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mAppointmentStatus.getTop());
        mTopAppointmentStatus.layout(0, mBuyLayout2ParentTop, mTopAppointmentStatus.getWidth(), mBuyLayout2ParentTop + mTopAppointmentStatus.getHeight());
    }

    public void getAppointmentHistory(){
        if (!CommonUtil.isStrEmpty(aID)){
            getCallbacks().getAppointDetail(aID);
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(mTvDepName,error.getMessage());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!CommonUtil.isStrEmpty(tag)){
            getAppointmentHistory();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
    //推送过来使用
    public static void startAppointDetailActivity(Context context, String aid, String tag){
        Intent intent = new Intent(context,LX_AppointDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(AID,aid);
        intent.putExtra(TAG,tag);
        context.startActivity(intent);
    }
    public static void startAppointDetailActivity(Context context, Appoint appoint){
        Intent intent = new Intent(context,LX_AppointDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(APPOINTMENT_OBJ,appoint);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
