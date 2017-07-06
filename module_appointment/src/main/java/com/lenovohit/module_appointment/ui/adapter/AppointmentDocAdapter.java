package com.lenovohit.module_appointment.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.module_appointment.R;

import java.util.List;

/**
 * Created by yuzhijun on 2017/7/6.
 */
public class AppointmentDocAdapter extends BaseQuickAdapter<Doctor,BaseViewHolder> {

    public AppointmentDocAdapter(@LayoutRes int layoutResId, @Nullable List<Doctor> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Doctor item) {
        ((SimpleDraweeView)helper.getView(R.id.svDoctor)).setImageURI(item.getPhotoUrl());
        helper.setText(R.id.tvDoctorName, CommonUtil.isStrEmpty(item.getDoctorName())?"未知医生":item.getDoctorName());
        helper.setText(R.id.tvDoctorType,CommonUtil.isStrEmpty(item.getJobName())?"":item.getJobName());
        helper.setText(R.id.tvDoctorDept,CommonUtil.isStrEmpty(item.getDepName())?"":item.getDepName());
        helper.setText(R.id.tvExpert,CommonUtil.isStrEmpty(item.getExpert())?"擅长:暂无明确的擅长领域":item.getExpert());
    }
}
