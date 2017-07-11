package com.lenovohit.hospitalgroup.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.LX_AppointmentHistoryActivity;
import com.lenovohit.hospitalgroup.ui.LX_DoctorActivity;
import com.lenovohit.hospitalgroup.ui.LX_HospitalsActivity;
import com.lenovohit.hospitalgroup.ui.LX_LoginActivity;
import com.lenovohit.hospitalgroup.ui.LX_MyAdviceListActivity;
import com.lenovohit.hospitalgroup.ui.LX_SwitchPatientActivity;
import com.lenovohit.hospitalgroup.ui.LX_UserInfoActivity;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.CommonUser;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;
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
    @BindView(R.id.waveView)
    WaveView mWaveView;
    @BindView(R.id.lrvFocusDoctor)
    MyItemOne lrvFocusDoctor;
    @BindView(R.id.lrvFocusHospital)
    MyItemOne lrvFocusHospital;
    Unbinder unbinder;
    @BindView(R.id.llMineEdit)
    LinearLayout llMineEdit;
    @BindView(R.id.ivUserAvatar)
    SimpleDraweeView ivUserAvatar;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvUserPhone)
    TextView tvUserPhone;
    private WaveHelper mWaveHelper;
    private User tempUser;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        isShowToolBar(false);
        unbinder = ButterKnife.bind(this, view);
        setWaveView();
        //点击编辑跳转到个人设置activity中,需要先判断当前用户是否登录
        llMineEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getTempUser() == null) {
                    //跳转到登录页面
                    LX_LoginActivity.startLoginActivity(getActivity(),Constants.LOGIN_TOP);
                } else {
                    LX_UserInfoActivity.startUserInfoActivity(getActivity());
                }
            }
        });
        lrvFocusHospital.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getTempUser() == null) {
                    //跳转到登录页面
                    LX_LoginActivity.startLoginActivity(getActivity(), Constants.LOGIN_COLLECT_HOSPITAL);
                } else {
                    LX_HospitalsActivity.startHospitalActivity(getActivity());
                }
            }
        });
        lrvFocusDoctor.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getTempUser() == null) {
                    //跳转到登录页面
                    LX_LoginActivity.startLoginActivity(getActivity(), Constants.LOGIN_COLLECT_DOCTOR);
                } else {
                    LX_DoctorActivity.startDoctorActivity(getActivity());
                }
            }
        });
        lrvSwitchPatient.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getTempUser() == null) {
                    //跳转到登录页面
                    LX_LoginActivity.startLoginActivity(getActivity(), Constants.LOGIN_SWITCH_PATIENT);
                } else {
                    LX_SwitchPatientActivity.startSwitchPatientActivity(getActivity());
                }
            }
        });
        lrvYuYue.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getTempUser() == null) {
                    //跳转到登录页面
                    LX_LoginActivity.startLoginActivity(getActivity(), Constants.LOGIN_APPOINTMENT_HISTORY);
                } else {
                    LX_AppointmentHistoryActivity.startAppointmentHistoryActivity(getActivity());
                }
            }
        });
        lrvMyFK.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getTempUser() == null) {
                    //跳转到登录页面
                    LX_LoginActivity.startLoginActivity(getActivity(), Constants.LOGIN_MINE_ADVICE);
                } else {
                    LX_MyAdviceListActivity.startMyAdviceListActivity(getActivity());
                }
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mWaveHelper.cancel();
    }

    private void setWaveView() {
        mWaveView.setShowWave(true);
        mWaveHelper = new WaveHelper(mWaveView);
        mWaveView.setShapeType(WaveView.ShapeType.SQUARE);
        mWaveHelper.start();
    }

    private void setItemInfos() {
        lrvFocusDoctor.isShowingTopLine(false);
        lrvFocusDoctor.setItemInfo(R.mipmap.lx_iv_focus_doctor, "关注的医生", tempUser == null || tempUser.getCollectDoctors() == null || tempUser.getCollectDoctors().size() == 0 ? "0" : tempUser.getCollectDoctors().size() + "");
        lrvFocusHospital.setItemInfo(R.mipmap.lx_iv_focus_hospital, "关注的医院", tempUser == null || tempUser.getCollectHospitals() == null || tempUser.getCollectHospitals().size() == 0 ? "0" : tempUser.getCollectHospitals().size() + "");
        String json = CommonUtil.getShardPStringByKey(Constants.COMM_USER_JSON);
        if (CommonUtil.isStrEmpty(json)){
            lrvSwitchPatient.setItemInfo(R.mipmap.lx_iv_my_switch_patient, "切换患者","");
        }else {
            CommonUser commonUser = new Gson().fromJson(json, CommonUser.class);
            if (commonUser!=null && commonUser.isSelected())
            lrvSwitchPatient.setItemInfo(R.mipmap.lx_iv_my_switch_patient, "切换患者",commonUser.getName());
        }
        lrvYuYue.setItemInfo(R.mipmap.lx_iv_my_appointment, "预约历史", "");
        //隐藏掉诊疗记录
        lrvDingDan.setItemInfo(R.mipmap.lx_iv_mobile_treatment_history, "诊疗记录", "");

        lrvMyFK.setItemInfo(R.mipmap.lx_iv_my_opinion, "我的反馈", "");
        btnConfig.setItemInfo(R.mipmap.lx_iv_my_setting, "设置", "");
    }

    @Override
    public void onResume() {
        super.onResume();
        tempUser = UserData.getTempUser();
        setItemInfos();
        if (tempUser != null) {
            User.UserBaseInfo baseInfo = tempUser.getBaseInfo();
            if (baseInfo != null) {
                tvUserName.setText(CommonUtil.isStrEmpty(baseInfo.getName()) ? "" : CommonUtil.getStarString(baseInfo.getName(),0,1));
                tvUserPhone.setText(CommonUtil.isStrEmpty(baseInfo.getPhoneNumber())?"":CommonUtil.getStarString(baseInfo.getPhoneNumber(),0,baseInfo.getPhoneNumber().length()-3));
                if (!CommonUtil.isStrEmpty(baseInfo.getPhotoUrl()))
                    ivUserAvatar.setImageURI(Uri.parse(baseInfo.getPhotoUrl()));
            }
        } else {
            tvUserName.setText("未登录");
            tvUserPhone.setText("");
            ivUserAvatar.setImageResource(R.mipmap.lx_iv_icon_doctor);
        }

    }
}
