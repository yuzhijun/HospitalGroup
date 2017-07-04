package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-07-04.
 */
@ContentView(R.layout.lx_user_info_edit_activity)
public class LX_UserInfoEditActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.UserInfoEditUi {
    public static String TYPE_NAME="name";
    public static String TYPE_SFZ="idcard";
    public static String type="-1";
    @BindView(R.id.edtContent)
    EditText edtContent;
    @BindView(R.id.tvPrompt)
    TextView tvPrompt;
    @BindView(R.id.btnInfoComplete)
    Button btnInfoComplete;
    @BindView(R.id.llInfoLayout)
    LinearLayout llInfoLayout;
    private Unbinder bind;
    private User tempUser;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        bind = ButterKnife.bind(this);
        //刚进入activity中编辑框显示为原来数据
        tempUser = UserData.getTempUser();
        if (tempUser !=null){
            if (tempUser.getBaseInfo()!=null){
                if (type.equals(TYPE_NAME)){
                    isShowToolBar(true);
                    setCenterTitle("姓名修改");
                    edtContent.setText(tempUser.getBaseInfo().getName());
                }else if (type.equals(TYPE_SFZ)){
                    isShowToolBar(true);
                    setCenterTitle("身份证修改");
                    edtContent.setText(tempUser.getBaseInfo().getIDCard());
                }
            }
        }
    }

    @Override
    public void initEvent() {
        btnInfoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edtContent.getText().toString();
                if (CommonUtil.isStrEmpty(s)){
                    CommonUtil.showSnackBar(edtContent,"所输内容不能为空");
                }else {
                    if (type.equals(TYPE_NAME)){
                        getCallbacks().editUserInfo(tempUser.getBaseInfo().getUID(),s,tempUser.getBaseInfo().getSex(),tempUser.getBaseInfo().getIDCard());
                    }else if (type.equals(TYPE_SFZ)){
                        getCallbacks().editUserInfo(tempUser.getBaseInfo().getUID(),tempUser.getBaseInfo().getName(),tempUser.getBaseInfo().getSex(),s);
                    }
                }
            }
        });
    }
    @Override
    public void editUserInfoCallBack(Result result) {
        if (CommonUtil.isNotEmpty(result)) {
            if (result.getState() <= 0) { // 表示错误
                CommonUtil.showSnackBar(edtContent,result.getMsg());
            } else {
                if (type.equals(TYPE_NAME)) {
                    UserData.getTempUser().getBaseInfo().setName(edtContent.getText().toString());
                } else if (type.equals(TYPE_SFZ)) {
                    UserData.getTempUser().getBaseInfo().setIDCard(edtContent.getText().toString());
                }
                CommonUtil.showSnackBar(edtContent,"修改成功!");
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
    public static void startUserInfoEditActivity(Context context,String type){
        LX_UserInfoEditActivity.type=type;
        context.startActivity(new Intent(context,LX_UserInfoEditActivity.class));
    }

}
