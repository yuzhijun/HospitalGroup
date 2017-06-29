package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_mainhome_fragment)
public class MainHomeFragment extends CoreFragment<MainController.MainUiCallbacks>  implements MainController.MainUi{

    public static MainHomeFragment newInstance() {
        return new MainHomeFragment();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void handleArguments(Bundle arguments) {

    }

    @Override
    protected BaseController getController() {
        return  LArtemis.getInstance().getMainController();
    }

    @Override
    public void showToast() {

    }
}
