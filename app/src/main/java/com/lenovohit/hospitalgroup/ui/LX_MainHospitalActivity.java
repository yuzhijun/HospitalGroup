package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.HospitalModuleAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.HospitalMainPage;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.RecyclerViewGridDivider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuzhijun on 2017/7/11.
 */
@ContentView(R.layout.lx_app_main_hospital_activity)
public class LX_MainHospitalActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MainHospitalUi{
    private static final String HOSPITAL = "HOSPITAL";
    @BindView(R.id.tvHospitalName)
    TextView tvHospitalName;
    @BindView(R.id.tvFocus)
    TextView tvFocus;
    @BindView(R.id.tvHospitalLevel)
    TextView tvHospitalLevel;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.llPhone)
    LinearLayout llPhone;
    @BindView(R.id.rvModule)
    RecyclerView rvModule;
    @BindView(R.id.tvHospitalDes)
    TextView tvHospitalDes;
    @BindView(R.id.tvTrafficDes)
    TextView tvTrafficDes;

    private EmptyView emptyView;
    private Hospitals mHospitals;
    private GridLayoutManager mGridLayoutManager;
    private HospitalModuleAdapter mModuleAdapter;
    Unbinder mUnbinder;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        mUnbinder = ButterKnife.bind(this);
        mHospitals = (Hospitals) getIntent().getExtras().getSerializable(HOSPITAL);
        setCenterTitle(CommonUtil.isStrEmpty(mHospitals.getHospitalName())?"未知医院名称":mHospitals.getHospitalName());
        tvHospitalName.setText(CommonUtil.isStrEmpty(mHospitals.getHospitalName())?"未知医院名称":mHospitals.getHospitalName());
        tvHospitalLevel.setText(CommonUtil.isStrEmpty(mHospitals.getHospitalLevelName())?"未知医院等级":mHospitals.getHospitalLevelName());

        tvPosition.setFocusableInTouchMode(true);
        tvPosition.requestFocus();

        mGridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        rvModule.setLayoutManager(mGridLayoutManager);
        rvModule.addItemDecoration(new RecyclerViewGridDivider(rvModule.getContext()));
        mModuleAdapter = new HospitalModuleAdapter(R.layout.lx_app_hospital_module_item);
        emptyView = new EmptyView(rvModule.getContext(), (ViewGroup) rvModule.getParent());
        emptyView.setBottomViewUnVisuable();
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setType(EmptyView.TYPE_LOADING);
                getCallbacks().getHospitalInfo(mHospitals.getHID(), UserData.getTempUser() == null || UserData.getTempUser().getBaseInfo() == null ?
                        "0":UserData.getTempUser().getBaseInfo().getUID());
            }
        });
        mModuleAdapter.setEmptyView(emptyView.getView());
        rvModule.setAdapter(mModuleAdapter);

        getCallbacks().getHospitalInfo(mHospitals.getHID(), UserData.getTempUser() == null || UserData.getTempUser().getBaseInfo() == null ?
                "0":UserData.getTempUser().getBaseInfo().getUID());
    }

    @Override
    public void initEvent() {

    }

    public static void startMainHospitalActivity(Context context,Hospitals hospitals){
        Intent intent = new Intent(context,LX_MainHospitalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(HOSPITAL,hospitals);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void getHospitalInfoCallBack(HospitalMainPage response) {
        if (null == response){
            tvPosition.setText("医院的具体地址未知");
            return;
        }

        tvFocus.setText(response.getFocus()+"");
        tvPosition.setText(CommonUtil.isStrEmpty(response.getAddress())?"医院的具体地址未知":response.getAddress());
        tvHospitalDes.setText(CommonUtil.isStrEmpty(response.getHospitalInfo())?"暂无医院具体简介":response.getHospitalInfo());
        tvTrafficDes.setText(CommonUtil.isStrEmpty(response.getPublicTransport())?"暂无具体的公共交通班次信息":response.getPublicTransport());
        if (null == response.getOpenModule() || response.getOpenModule().size() <= 0){
            emptyView.setType(EmptyView.TYPE_NO_DATA);
            return;
        }

        mModuleAdapter.getData().clear();
        mModuleAdapter.setNewData(response.getOpenModule());
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        emptyView.setType(EmptyView.TYPE_ERROR);
    }
}
