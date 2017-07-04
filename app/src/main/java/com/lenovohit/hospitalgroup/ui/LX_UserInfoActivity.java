package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.views.MyItemInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-07-04.
 * 患者个人信息设置界面
 */
@ContentView(R.layout.lx_user_info)
public class LX_UserInfoActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MainUi {

    @BindView(R.id.lrvTX)
    RelativeLayout rlAvautar;
    @BindView(R.id.aivInfoHead)
    ImageView aivInfoHead;
    @BindView(R.id.loginOrOutBtn)
    Button loginOrOutBtn;
    @BindView(R.id.lrvXM)
    MyItemInfo lrvXM;
    @BindView(R.id.lrvSFZ)
    MyItemInfo lrvSFZ;
    @BindView(R.id.lrvSJHM)
    MyItemInfo lrvSJHM;
    @BindView(R.id.lrvXB)
    MyItemInfo lrvXB;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        isShowToolBar(true);
        setCenterTitle("个人信息");
        setLeftTitleAndIcon("返回", ivBack, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lrvXM.setItemInfo("姓名","玲玲");
        lrvSFZ.setItemInfo("身份证","142601199709283920");
        lrvSJHM.setItemInfo("手机号码","173830392902");
        lrvXB.setItemInfo("性别","女");
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    public static void startUserInfoActivity(Context context) {
        context.startActivity(new Intent(context, LX_UserInfoActivity.class));
    }

}
