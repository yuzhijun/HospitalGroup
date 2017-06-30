package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import butterknife.ButterKnife;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_hospital_fragment)
public class HospitalFragment extends CoreFragment<MainController.MainUiCallbacks> implements MainController.MainUi{

    public static HospitalFragment newInstance() {
        return new HospitalFragment();
    }

    @Override
    protected void initViews(final View view, Bundle savedInstanceState) {
        ButterKnife.bind(view);
        setCenterTitle("医院");
        setRightTitleAndIcon("搜索",R.mipmap.lx_iv_search_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showSnackBar(view,"您点击了搜索按钮");
            }
        });
        setLeftTitle("全国",null);
    }

    @Override
    protected void handleArguments(Bundle arguments) {

    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

}
