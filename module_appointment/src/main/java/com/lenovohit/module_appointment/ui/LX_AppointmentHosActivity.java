package com.lenovohit.module_appointment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.ui.controller.AppointmentController;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.module_appointment.R;
import com.lenovohit.module_appointment.ui.adapter.AppointmentHosAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机预约医院列表页
 * Created by yuzhijun on 2017/7/4.
 */
public class LX_AppointmentHosActivity extends CoreActivity<AppointmentController.AppointmentUiCallbacks> implements AppointmentController.AppointmentHosUi {

    private RecyclerView rvHosList;
    private AppointmentHosAdapter mHosAdapter;
    private List<Hospitals> mHospitalses = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.lx_appointment_hos_list_activity;
    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController().getAppointmentController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("手机预约");
        rvHosList = (RecyclerView) findViewById(R.id.rvHosList);
        rvHosList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvHosList.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL));
        mHosAdapter = new AppointmentHosAdapter(R.layout.lx_hos_list_recyclerview,mHospitalses);
        rvHosList.setAdapter(mHosAdapter);

        getCallbacks().getSearchHospital("");
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void getSearchHospitalCallBack(List<Hospitals> response) {
        if (null == response || response.size() <= 0){
            return;
        }

        mHosAdapter.getData().clear();
        mHosAdapter.setNewData(response);
    }
}
