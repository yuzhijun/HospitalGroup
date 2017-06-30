package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_mine_fragment)
public class MineFragment  extends CoreFragment<MainController.MainUiCallbacks> implements MainController.MainUi{

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void handleArguments(Bundle arguments) {

    }

    @Override
    protected BaseController getController() {
        return  LArtemis.getInstance().getMainController();
    }

}
