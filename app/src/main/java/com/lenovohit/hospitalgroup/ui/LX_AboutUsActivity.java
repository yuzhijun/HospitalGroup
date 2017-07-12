package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SharkChao on 2017-07-12.
 */
@ContentView(R.layout.lx_mine_aboutus_activity)
public class LX_AboutUsActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MainUi {
    @BindView(R.id.tv_about_version)
    TextView mTvAboutVersion;
    private Unbinder mBind;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("关于我们");
        mBind = ButterKnife.bind(this);
        mTvAboutVersion.setText(getResources().getString(R.string.about_version)+ CommonUtil.getAppVersionName(this));
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
    public static void startAboutUsActivity(Context context){
        context.startActivity(new Intent(context,LX_AboutUsActivity.class));
    }
}
