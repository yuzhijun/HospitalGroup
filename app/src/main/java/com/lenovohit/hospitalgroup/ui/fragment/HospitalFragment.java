package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_hospital_fragment)
public class HospitalFragment extends CoreFragment<MainController.MainUiCallbacks> implements MainController.MainUi{

    public static HospitalFragment newInstance() {
        return new HospitalFragment();
    }

    @Override
    protected void initViews(View view,Bundle savedInstanceState) {
        Button button = (Button) view.findViewById(R.id.click);
        final LinearLayout llLayout= (LinearLayout) view.findViewById(R.id.llLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showSnackBarToIcon( llLayout, "大庆龙南医院",CommonUtil.HINT);
            }
        });
    }

    @Override
    protected void handleArguments(Bundle arguments) {

    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void showToast() {

    }
}
