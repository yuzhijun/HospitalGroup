package com.lenovohit.module_appointment.ui;

import android.content.Context;
import android.content.Intent;
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
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.AppointmentController;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.module_appointment.R;
import com.lenovohit.module_appointment.ui.adapter.AppointmentDocAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机预约医生列表
 * Created by yuzhijun on 2017/7/6.
 */
public class LX_AppointmentDocActivity extends CoreActivity<AppointmentController.AppointmentUiCallbacks> implements AppointmentController.AppointmentDocUi{

    public static final String HID = "HID";
    public static final String DEPT_CODE = "DEPT_CODE";
    public static final String DEPT_NAME = "DEPT_NAME";
    public static final String TAG = "TAG";
    private View notDataView;
    private RecyclerView rvDocList;
    private AppointmentDocAdapter mDocAdapter;
    private List<Doctor> mDoctors = new ArrayList<>();

    private String hid;
    private String dept_code;
    private String dept_name;
    private String tag;

    @Override
    protected int getLayoutId() {
        return R.layout.lx_appointment_doc_list_activity;
    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController().getAppointmentController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        hid = getIntent().getStringExtra(HID);
        dept_code = getIntent().getStringExtra(DEPT_CODE);
        dept_name = getIntent().getStringExtra(DEPT_NAME);
        tag = getIntent().getStringExtra(TAG);

        isShowToolBar(true);
        setCenterTitle(dept_name);

        rvDocList = (RecyclerView) findViewById(R.id.rvDocList);
        rvDocList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvDocList.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL));
        mDocAdapter = new AppointmentDocAdapter(R.layout.lx_doctor_list_item,mDoctors);
        rvDocList.setAdapter(mDocAdapter);

        notDataView = getLayoutInflater().inflate(R.layout.lx_empty_view, (ViewGroup) rvDocList.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCallbacks().getDepDoctors(hid,dept_code,tag);
            }
        });

        getCallbacks().getDepDoctors(hid,dept_code,tag);
    }

    @Override
    public void initEvent() {
        mDocAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Doctor doctor = mDocAdapter.getData().get(position);
                LX_DoctorInfoActivity.startDoctorInfoActivity(LX_AppointmentDocActivity.this,doctor.getHID(),doctor.getDoctorCode(),doctor.getDepCode(),tag);
            }
        });
    }

    public static void startAppointmentDocActivity(Context context,String hid,String deptCode,String deptName,String tag){
        Intent intent = new Intent(context,LX_AppointmentDocActivity.class);
        intent.putExtra(HID,hid);
        intent.putExtra(DEPT_CODE,deptCode);
        intent.putExtra(DEPT_NAME,deptName);
        intent.putExtra(TAG,tag);
        context.startActivity(intent);
    }

    @Override
    public void getDepDoctorsCallBack(List<Doctor> response) {
        if (null == response || response.size() <= 0){
            mDocAdapter.setEmptyView(notDataView);
            return;
        }

        mDocAdapter.getData().clear();
        mDocAdapter.setNewData(response);
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        mDocAdapter.setEmptyView(notDataView);
    }
}
