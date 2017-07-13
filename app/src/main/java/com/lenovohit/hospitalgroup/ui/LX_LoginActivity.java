package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-07-04.
 */
@ContentView(R.layout.lx_app_login)
public class LX_LoginActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.LoginUi {
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtCode)
    EditText edtCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.btnGetUser)
    Button btnGetUser;
    private TimeCount time;
    private Unbinder bind;
    //type代表从哪个界面跳转到登录页面
    private String type;
    public  static final String LOGIN_TYPE="login_info";
    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        bind = ButterKnife.bind(this);
        isShowToolBar(true);
        setCenterTitle("用户登录");
        setLeftTitleAndIcon("返回", ivBack, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edtPhone.setText("18757574669");
        edtCode.setText("999999");
        //初始化时间控件
        initTime();
        type = getIntent().getStringExtra(LOGIN_TYPE);
    }

    @Override
    public void initEvent() {
        btnGetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.isStrEmpty(getUserPhone())){
                    CommonUtil.showSnackBar(edtCode,"请输入手机号码");
                }else if (CommonUtil.isStrEmpty(getUserCode())){
                    CommonUtil.showSnackBar(edtCode,"请输入验证码");
                }else {
                    getCallbacks().getLoginData(getUserPhone(),getUserCode());
                }
            }
        });
        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.isStrEmpty(getUserPhone())){
                    CommonUtil.showSnackBar(edtCode,"请先输入手机号码");
                }else  if(!CommonUtil.isMobileNO(getUserPhone())){
                    CommonUtil.showSnackBar(edtCode,"请输入正确的手机号码");
                }else {
                    btnCode.setBackgroundResource(R.drawable.btn_gray_button);
                    btnCode.setEnabled(false);
                    time = new TimeCount(59000, 1000);
                    time.start();// 开始计时
                    UserData.setGetYZMDateTime(CommonUtil.AddTimeforNow2());
                    getCallbacks().getLoginCode(getUserPhone(), Constants.SMS_TEMP_CODE);
                }
            }
        });
    }

    public static void startLoginActivity(Context context,String type) {
       Intent intent=new Intent(context, LX_LoginActivity.class);
        intent.putExtra(LOGIN_TYPE,type);
        context.startActivity(intent);
    }


    /**
     * 登录按钮的回调
     * @param user
     */
    @Override
    public void getLoginDataCallBack(User user) {
        if (CommonUtil.isNotEmpty(user)){
            UserData.setTempUser(user);
            Gson gson=new Gson();
            String s = gson.toJson(user);
            CommonUtil.setShardPString("user",s);
        }else {
            CommonUtil.showSnackBar(edtCode,"验证码错误或过期");
        }
    }

    /**
     * 验证码的回调
     * @param result
     */
    @Override
    public void getLoginCodeCallBack(Result result) {
        if (CommonUtil.isNotEmpty(result)){
            if (result.getState() <= 0) {
                UserData.setGetYZMDateTime(null);
                btnCode.setText("重新获取验证码");
                btnCode.setEnabled(true);
                btnCode.setBackgroundResource(R.drawable.lx_btn_complete_select);
                time.cancel();
                CommonUtil.showSnackBar(edtCode,"短信验证失败");
            }
        }
    }

    /**
     * 验证手机的回调，放置在登录按钮的回调中
     * @param result
     */
    @Override
    public void getLoginValidateCallBack(Result result) {
        if (CommonUtil.isNotEmpty(result) && result.getState() > 0 && CommonUtil.isNotEmpty(UserData.getTempUser())){
            UserData.setGetYZMDateTime(null);
            btnCode.setText("重新获取验证码");
            btnCode.setEnabled(true);
            btnCode.setBackgroundResource(R.drawable.lx_btn_complete_select);
            switch (type){
                case Constants.LOGIN_TOP:
                    LX_UserInfoActivity.startUserInfoActivity(LX_LoginActivity.this);
                    finish();
                    break;
                case Constants.LOGIN_COLLECT_HOSPITAL:
                    LX_HospitalsActivity.startHospitalActivity(LX_LoginActivity.this);
                    finish();
                    break;
                case Constants.LOGIN_COLLECT_DOCTOR:
                    LX_DoctorActivity.startDoctorActivity(LX_LoginActivity.this);
                    finish();
                    break;
                case Constants.LOGIN_SWITCH_PATIENT:
                    LX_SwitchPatientActivity.startSwitchPatientActivity(LX_LoginActivity.this);
                    finish();
                    break;
                case Constants.LOGIN_APPOINTMENT_HISTORY:
                    LX_AppointmentHistoryActivity.startAppointmentHistoryActivity(LX_LoginActivity.this);
                    finish();
                    break;
                case Constants.LOGIN_MINE_ADVICE:
                    LX_MyAdviceListActivity.startMyAdviceListActivity(LX_LoginActivity.this);
                    finish();
                case  Constants.LOGIN_QUEUE_UP:
                    finish();
                    break;
            }
        }else{
            CommonUtil.showSnackBar(edtCode,"抱歉,登录失败啦,请重新登录");
        }
    }

    public String getUserPhone() {
       return edtPhone.getText().toString().trim();
    }

    public String getUserCode() {
        return edtCode.getText().toString().trim();
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(btnCode,error.getMessage());
    }

    /**
     * 定时器类
     * */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            btnCode.setEnabled(false);
            btnCode.setText((millisUntilFinished) / 1000 + "秒后重新获取");
            btnCode.setBackgroundResource(R.drawable.btn_gray_button);
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            btnCode.setText("重新获取验证码");
            btnCode.setEnabled(true);
            btnCode.setBackgroundResource(R.drawable.lx_btn_complete_select);
        }
    }
    /**
     * 初始化
     * */
    public void initTime() {
        // 初始化时时间控件
        if (CommonUtil.isNotEmpty(UserData.getGetYZMDateTime())) {
            long a = UserData.getGetYZMDateTime().getTime();
            long b = new Date().getTime();
            if (a > b) {
                time = new TimeCount(a - b, 1000);
                time.start();// 开始计时
            }
        } else {
            time = new TimeCount(59000, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        bind.unbind();
    }
}
