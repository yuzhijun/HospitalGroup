package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.views.MyItemOne;
import com.lenovohit.lartemis_api.views.WaveHelper;
import com.lenovohit.lartemis_api.views.WaveView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_mine_fragment)
public class MineFragment extends CoreFragment<MainController.MainUiCallbacks> implements MainController.MainUi {
    @BindView(R.id.ivUserAvatar)
    ImageView ivUserAvatar;

    @BindView(R.id.wave)
    WaveView mWaveView;
    WaveHelper mWaveHelper;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvUserPhone)
    TextView tvUserPhone;
    @BindView(R.id.llUserAvatar)
    LinearLayout llUserAvatar;
    @BindView(R.id.tvFocusDoctorNum)
    TextView tvFocusDoctorNum;
    @BindView(R.id.llFocusDoctor)
    LinearLayout llFocusDoctor;
    @BindView(R.id.tvFocusHospitalNum)
    TextView tvFocusHospitalNum;
    @BindView(R.id.llFocusHospital)
    LinearLayout llFocusHospital;
    @BindView(R.id.lrvSwitchPatient)
    MyItemOne lrvSwitchPatient;
    @BindView(R.id.lrvYuYue)
    MyItemOne lrvYuYue;
    @BindView(R.id.lrvDingDan)
    MyItemOne lrvDingDan;
    @BindView(R.id.lrvAddress)
    MyItemOne lrvAddress;
    @BindView(R.id.lrvMyFK)
    MyItemOne lrvMyFK;
    @BindView(R.id.btnConfig)
    MyItemOne btnConfig;
    Unbinder unbinder;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        isShowToolBar(false);
        unbinder = ButterKnife.bind(this, view);
        mWaveHelper = new WaveHelper(mWaveView);
        mWaveView.setShapeType(WaveView.ShapeType.SQUARE);

        lrvSwitchPatient.setItemInfo(R.mipmap.lx_iv_my_switch_patient, "患者管理", "");
        lrvYuYue.setItemInfo(R.mipmap.lx_iv_my_appointment, "预约历史", "");
        lrvDingDan.setItemInfo(R.mipmap.lx_iv_mobile_treatment_history, "诊疗记录", "");
        lrvAddress.setItemInfo(R.mipmap.lx_iv_my_address, "收货地址", "");
        lrvMyFK.setItemInfo(R.mipmap.lx_iv_my_opinion, "我的反馈", "");
        btnConfig.setItemInfo(R.mipmap.lx_iv_my_setting, "设置", "");
    }

    @Override
    protected void handleArguments(Bundle arguments) {

    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
