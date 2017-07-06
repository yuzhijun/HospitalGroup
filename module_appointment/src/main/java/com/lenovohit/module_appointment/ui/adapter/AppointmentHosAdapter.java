package com.lenovohit.module_appointment.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.module_appointment.R;

import java.util.List;

/**
 * Created by yuzhijun on 2017/7/4.
 */
public class AppointmentHosAdapter extends BaseQuickAdapter<Hospitals,BaseViewHolder> {

    public AppointmentHosAdapter(@LayoutRes int layoutResId, @Nullable List<Hospitals> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Hospitals item) {
        ((SimpleDraweeView)helper.getView(R.id.svHospitalIcon)).setImageURI(item.getLogoUrl());
        helper.setText(R.id.tvHospitalName, CommonUtil.isStrEmpty(item.getHospitalName())?"未知医院名称":item.getHospitalName());
        helper.setText(R.id.tvHospitalLevel,CommonUtil.isStrEmpty(item.getHospitalLevelName())?"未知等级":item.getHospitalLevelName());
        helper.setText(R.id.tvHospitalProperty,CommonUtil.isStrEmpty(item.getHospitalType())?"未知类型":item.getHospitalType());
        helper.setText(R.id.tvAttentionNum,CommonUtil.isStrEmpty(item.getFocus())?"关注 0":"关注 "+item.getFocus());
        helper.setText(R.id.metersTv,CommonUtil.isStrEmpty(item.getDistance())?"距离0米":"距离 "+item.getDistance()+"米");
    }
}
