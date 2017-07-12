package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;

/**
 * Created by SharkChao on 2017-07-12.
 */
@ContentView(R.layout.lx_mine_declare_activity)
public class LX_DeclareActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MainUi{
    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("声明");
    }

    @Override
    public void initEvent() {

    }
    public static void startDeclareActivity(Context context){
        context.startActivity(new Intent(context,LX_DeclareActivity.class));
    }
}
