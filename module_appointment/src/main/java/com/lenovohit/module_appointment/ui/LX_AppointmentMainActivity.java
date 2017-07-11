package com.lenovohit.module_appointment.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import com.lenovohit.lartemis_api.model.CommonObj;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.AppointmentController;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.module_appointment.R;
import com.lenovohit.module_appointment.ui.adapter.AppointParentDeptAdapter;
import com.lenovohit.module_appointment.ui.adapter.AppointSonDeptAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机预约首界面
 * Created by yuzhijun on 2017/6/29.
 */
public class LX_AppointmentMainActivity extends CoreActivity<AppointmentController.AppointmentUiCallbacks> implements AppointmentController.AppointmentMainUi {
    private static final String HID = "hid";
    private RecyclerView rvParentDept;
    private RecyclerView rvSonDept;
    private EmptyView emptyView;
    private AppointParentDeptAdapter mDeptAdapter;
    private AppointSonDeptAdapter mSonDeptAdapter;
    private List<CommonObj> parentDept = new ArrayList<>();
    private List<CommonObj> sonDept = new ArrayList<>();

    private String hid;
    private int recycleViewCanShowHeight;

    @Override
    protected int getLayoutId() {
        return R.layout.lx_appointment_dept_tree_activity;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("手机预约");

        hid = getIntent().getStringExtra(HID);

        rvParentDept = (RecyclerView) findViewById(R.id.rvParentDept);
        rvParentDept.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvParentDept.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL));
        mDeptAdapter = new AppointParentDeptAdapter(R.layout.lx_parent_dept_item,parentDept);
        rvParentDept.setAdapter(mDeptAdapter);

        rvSonDept = (RecyclerView) findViewById(R.id.rvSonDept);
        rvSonDept.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvSonDept.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL));
        mSonDeptAdapter = new AppointSonDeptAdapter(R.layout.lx_son_dept_item,sonDept);
        rvSonDept.setAdapter(mSonDeptAdapter);

        emptyView = new EmptyView(rvParentDept.getContext(), (ViewGroup) rvParentDept.getParent());
        emptyView.setType(EmptyView.TYPE_NO_DATA);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCallbacks().getAllDep(hid);
            }
        });

        getCallbacks().getAllDep(hid);
    }

    @Override
    public void initEvent() {
        //大科室点击事件
        mDeptAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (recycleViewCanShowHeight == 0){
                    recycleViewCanShowHeight = rvParentDept.getHeight();
                }
                if (recycleViewCanShowHeight > 0 && Build.VERSION.SDK_INT > 10){
                    rvParentDept.smoothScrollBy(0, (int) (view.getY() - recycleViewCanShowHeight/2 + view.getPivotY()));
                }
                mDeptAdapter.setSelectedPosition(position);
                mDeptAdapter.notifyDataSetChanged();
                showSonDept(mDeptAdapter.getData().get(position));
            }
        });

        //小科室点击事件
        mSonDeptAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Hospitals hospital = HospitalData.getCurrentHospital();
                LX_AppointmentDocActivity.startAppointmentDocActivity(LX_AppointmentMainActivity.this,hospital.getHID(),mSonDeptAdapter.getData().get(position).getID(),
                        mSonDeptAdapter.getData().get(position).getTitle(),"zxyy");
            }
        });
    }

    //显示小科室
    private void showSonDept(CommonObj commonObj){
        List<CommonObj> sonDepts = commonObj.getChildrens();
        if(null == sonDepts || sonDepts.size() <= 0){
            mSonDeptAdapter.getData().clear();
            List<CommonObj> commonObjs = new ArrayList<>();
            commonObjs.add(commonObj);
            mSonDeptAdapter.setNewData(commonObjs);
            return;
        }

        mSonDeptAdapter.getData().clear();
        mSonDeptAdapter.getData().addAll(sonDepts);
        mSonDeptAdapter.notifyDataSetChanged();
    }

    @Override
    protected BaseController getController() {
        return  LArtemis.getInstance().getMainController().getAppointmentController();
    }

    public static void startAppointmentMain(Context context,String hid){
        Intent intent = new Intent(context,LX_AppointmentMainActivity.class);
        intent.putExtra(HID,hid);
        context.startActivity(intent);
    }

    @Override
    public void getAllDepCallBack(List<CommonObj> response) {
        if (null == response || response.size() <= 0){
            rvSonDept.setVisibility(View.GONE);
            mDeptAdapter.setEmptyView(emptyView.getView());
            return;
        }

        if (response.size() == 1){
            rvParentDept.setVisibility(View.GONE);
            mSonDeptAdapter.getData().clear();
            mSonDeptAdapter.setNewData(response.get(0).getChildrens());
            return;
        }

        rvSonDept.setVisibility(View.VISIBLE);
        mDeptAdapter.getData().clear();
        mDeptAdapter.setNewData(response);
        mDeptAdapter.setSelectedPosition(0);
        showSonDept(response.get(0));
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        rvSonDept.setVisibility(View.GONE);
        mDeptAdapter.setEmptyView(emptyView.getView());
    }
}
