package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.lenovohit.hospitalgroup.R.id.et_advice_phone;
import static com.lenovohit.hospitalgroup.R.id.tvPhone;

/**
 * Created by SharkChao on 2017-07-11.
 */
@ContentView(R.layout.lx_mine_advice_activity_add)
public class LX_MyAdviceAddActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MyAdviceAddUi {
    @BindView(R.id.et_advice_opinion)
    EditText mEtAdviceOpinion;
    @BindView(R.id.tvRestNum)
    TextView mTvRestNum;
    @BindView(tvPhone)
    TextView mTvPhone;
    @BindView(et_advice_phone)
    EditText mEtAdvicePhone;
    @BindView(R.id.btn_advice_commit)
    Button mBtnAdviceCommit;
    private Unbinder mBind;
    private final int MAX_LENGTH = 100;
    private int Rest_Length = MAX_LENGTH;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mBind = ButterKnife.bind(this);
        isShowToolBar(true);
        setCenterTitle("新增反馈");
        if (UserData.getTempUser() !=null) {
            mTvPhone.setVisibility(View.GONE);
            mEtAdvicePhone.setVisibility(View.GONE);
            mEtAdvicePhone.setText(UserData.getTempUser().getBaseInfo().getPhoneNumber());
        }
    }

    @Override
    public void initEvent() {
        mEtAdviceOpinion.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (Rest_Length >= 0) {
                    Rest_Length = MAX_LENGTH
                            - mEtAdviceOpinion.getText().length();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                mTvRestNum.setText("还能输入" + Rest_Length + "个字");

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTvRestNum.setText("还能输入" + Rest_Length + "个字");
            }
        });
        mBtnAdviceCommit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mEtAdviceOpinion.getText().length() < 2) {
                    CommonUtil.showSnackBar(mBtnAdviceCommit,"请填写您的反馈意见，方便我们改进!");
                } else if (mEtAdvicePhone.getText().length() < 5) {
                    CommonUtil.showSnackBar(mBtnAdviceCommit,"请留下您的联系方式方便我们联系您!");
                } else {
                    sendMyAdvice();
                }
            }
        });
    }
    public void sendMyAdvice(){
        String content = mEtAdviceOpinion.getText().toString().trim();
        String phone = mEtAdvicePhone.getText().toString().trim();
        getCallbacks().sendMyAdvice(phone,content);
    }
    @Override
    public void getMyAdviceSendCallBack(Result result) {
        if (result!=null && result.getState()>0){
            CommonUtil.showSnackBar(mEtAdviceOpinion,"您的意见我们已经收到,近期会给您反馈,谢谢!");
            finish();
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(mEtAdviceOpinion,error.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
    public static void startMyAdviceAddActivity(Context context){
        context.startActivity(new Intent(context,LX_MyAdviceAddActivity.class));
    }
}
