package com.lenovohit.module_appointment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.HospitalData;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.AppointmentController;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.lrouter_api.base.LRouterAppcation;
import com.lenovohit.lrouter_api.core.LRouterRequest;
import com.lenovohit.lrouter_api.core.LocalRouter;
import com.lenovohit.lrouter_api.core.callback.IRequestCallBack;
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
    private View notDataView;
    private List<Hospitals> mHospitalses = new ArrayList<>();
    private String stringExtra;

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
        stringExtra = getIntent().getStringExtra(Constants.PUT_TYPE);
        if (stringExtra.equals(Constants.PUT_TYPE_SWITCH_PATIENT)){
            setCenterTitle("新增就诊者");
        }else if (stringExtra.equals(Constants.PUT_TYPE_APPOINTMENT)){
            setCenterTitle("手机预约");
        }
        rvHosList = (RecyclerView) findViewById(R.id.rvHosList);
        rvHosList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvHosList.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL));
        mHosAdapter = new AppointmentHosAdapter(R.layout.lx_hos_list_recyclerview,mHospitalses);
        rvHosList.setAdapter(mHosAdapter);

        notDataView = getLayoutInflater().inflate(R.layout.lx_empty_view, (ViewGroup) rvHosList.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCallbacks().getSearchHospital("");
            }
        });

        getCallbacks().getSearchHospital("");
    }

    @Override
    public void initEvent() {
        mHosAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (stringExtra.equals(Constants.PUT_TYPE_APPOINTMENT)){
                    HospitalData.setCurrentHospital((Hospitals)adapter.getData().get(position));
                    LX_AppointmentMainActivity.startAppointmentMain(LX_AppointmentHosActivity.this,((Hospitals)adapter.getData().get(position)).getHID());
                }else if (stringExtra.equals(Constants.PUT_TYPE_SWITCH_PATIENT)){
                    //跳转到对应的添加患者列表
                    try {
                        LocalRouter.getInstance(LRouterAppcation.getInstance())
                                .navigation(CoreActivity.currentActivity, LRouterRequest.getInstance(CoreActivity.currentActivity)
                                        .processName("com.lenovohit.hospitalgroup")
                                        .provider("main")
                                        .action("MainEntranceAction")
                                        .param(Constants.PUT_TYPE, ((Hospitals)adapter.getData().get(position)).getHID()))
                                .setCallBack(new IRequestCallBack() {
                                    @Override
                                    public void onSuccess(final String result) {
                                    }
                                    @Override
                                    public void onFailure(Exception e) {
                                    }
                                });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        });
    }

    @Override
    public void getSearchHospitalCallBack(List<Hospitals> response) {
        if (null == response || response.size() <= 0){
            mHosAdapter.setEmptyView(notDataView);
            return;
        }

        mHosAdapter.getData().clear();
        mHosAdapter.setNewData(response);
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        mHosAdapter.setEmptyView(notDataView);
    }
}
