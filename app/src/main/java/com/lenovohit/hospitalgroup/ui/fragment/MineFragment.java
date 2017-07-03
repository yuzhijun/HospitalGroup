package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;
import android.view.View;

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

import static com.lenovohit.hospitalgroup.R.id.waveView;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_mine_fragment)
public class MineFragment extends CoreFragment<MainController.MainUiCallbacks> implements MainController.MainUi {

    @BindView(R.id.lrvSwitchPatient)
    MyItemOne lrvSwitchPatient;
    @BindView(R.id.lrvYuYue)
    MyItemOne lrvYuYue;
    @BindView(R.id.lrvDingDan)
    MyItemOne lrvDingDan;
    @BindView(R.id.lrvMyFK)
    MyItemOne lrvMyFK;
    @BindView(R.id.btnConfig)
    MyItemOne btnConfig;
    @BindView(waveView)
    WaveView mWaveView;
    @BindView(R.id.lrvFocusDoctor)
    MyItemOne lrvFocusDoctor;
    @BindView(R.id.lrvFocusHospital)
    MyItemOne lrvFocusHospital;
    Unbinder unbinder;
    private WaveHelper mWaveHelper;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        isShowToolBar(false);
        unbinder = ButterKnife.bind(this, view);
        lrvFocusDoctor.setItemInfo(R.mipmap.lx_iv_focus_doctor,"关注的医生","");
        lrvFocusDoctor.isShowingTopLine(false);
        lrvFocusHospital.setItemInfo(R.mipmap.lx_iv_focus_hospital,"关注的医院","");
        lrvSwitchPatient.setItemInfo(R.mipmap.lx_iv_my_switch_patient, "患者管理", "");
        lrvYuYue.setItemInfo(R.mipmap.lx_iv_my_appointment, "预约历史", "");
        lrvDingDan.setItemInfo(R.mipmap.lx_iv_mobile_treatment_history, "诊疗记录", "");
        lrvMyFK.setItemInfo(R.mipmap.lx_iv_my_opinion, "我的反馈", "");
        btnConfig.setItemInfo(R.mipmap.lx_iv_my_setting, "设置", "");
        mWaveView.setShowWave(true);
        mWaveHelper = new WaveHelper(mWaveView);
        mWaveView.setShapeType(WaveView.ShapeType.SQUARE);
        mWaveView.setAmplitudeRatio(0.5f);
        mWaveView.setWaterLevelRatio(2.0f);
        mWaveHelper.start();
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
        mWaveHelper.cancel();
    }


}
