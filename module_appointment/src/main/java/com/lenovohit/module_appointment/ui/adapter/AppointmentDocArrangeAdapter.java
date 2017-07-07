package com.lenovohit.module_appointment.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.lartemis_api.model.DoctorArrangeShow;

import java.util.List;

/**
 * Created by yuzhijun on 2017/7/7.
 */
public class AppointmentDocArrangeAdapter extends BaseQuickAdapter<DoctorArrangeShow,BaseViewHolder> {

    public AppointmentDocArrangeAdapter(@LayoutRes int layoutResId, @Nullable List<DoctorArrangeShow> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorArrangeShow item) {

    }
}
