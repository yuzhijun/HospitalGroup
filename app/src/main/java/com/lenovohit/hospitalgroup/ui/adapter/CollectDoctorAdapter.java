package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */

public class CollectDoctorAdapter extends BaseQuickAdapter<Doctor,BaseViewHolder>{
    public CollectDoctorAdapter(@LayoutRes int layoutResId, @Nullable List<Doctor> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Doctor item) {
        ((SimpleDraweeView)helper.getView(R.id.ivDoctor)).setImageURI(item.getPhotoUrl());
        helper.setText(R.id.tvDoctorName, CommonUtil.isStrEmpty(item.getDoctorName())?"未知医生姓名":item.getDoctorName());
        helper.setText(R.id.tvDoctorType,CommonUtil.isStrEmpty(item.getJobName())?"未知类型":item.getJobName());
        helper.setText(R.id.tvDoctorKeshi,CommonUtil.isStrEmpty(item.getDepName())?"未知科室":item.getDepName());
        helper.setText(R.id.tvHospitalName,CommonUtil.isStrEmpty(item.getHospitalName())?"未知医院姓名":item.getHospitalName());
        helper.setText(R.id.tvExpert,CommonUtil.isStrEmpty(item.getExpert())?"擅长未知":item.getExpert());
    }

}
