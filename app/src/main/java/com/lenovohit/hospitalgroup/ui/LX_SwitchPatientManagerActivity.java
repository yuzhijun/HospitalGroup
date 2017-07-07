package com.lenovohit.hospitalgroup.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.CommonUser;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-07-07.
 */
@ContentView(R.layout.lx_mine_switch_patient_manage_activity)
public class LX_SwitchPatientManagerActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.SwitchPatientMangerUi {

    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtVerifyCode)
    EditText edtVerifyCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.btnPatientManage)
    Button btnPatientManage;
    @BindView(R.id.lvHospitalCard)
    ListView lvHospitalCard;
    @BindView(R.id.llSelectHospitalCard)
    LinearLayout llSelectHospitalCard;
    private String stringExtra;
    private Unbinder bind;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        bind = ButterKnife.bind(this);
        stringExtra = getIntent().getStringExtra(Constants.PUT_TYPE);
        if (!CommonUtil.isStrEmpty(stringExtra)) {

        }
    }

    @Override
    public void initEvent() {
        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtPhone.getText().toString();
                if (CommonUtil.isStrEmpty(phone)){
                    CommonUtil.showSnackBar(edtPhone,"手机号不能为空!");
                }else if (!CommonUtil.isMobileNO(phone)){
                    CommonUtil.showSnackBar(edtPhone,"请输入正确的手机号码");
                }else{
                    //在此处获取验证码
                }
            }
        });
        btnPatientManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtPhone.getText().toString();
                if (CommonUtil.isStrEmpty(phone)){
                    CommonUtil.showSnackBar(edtPhone,"手机号不能为空");
                }else if(!CommonUtil.isMobileNO(phone)){
                    CommonUtil.showSnackBar(edtPhone,"请输入正确的手机号码");
                }else {
                    //点击获取新增就诊者

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void getVerifyCodeCallBack(Result result) {

    }

    @Override
    public void verifyCodeCallBack(Result result) {

    }

    @Override
    public void getPatientListCallBack(List<CommonUser> list) {

    }
}
