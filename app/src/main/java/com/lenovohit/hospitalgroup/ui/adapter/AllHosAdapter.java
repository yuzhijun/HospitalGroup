package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */

public class AllHosAdapter extends BaseQuickAdapter<Hospitals,BaseViewHolder>{
    public AllHosAdapter(@LayoutRes int layoutResId, @Nullable List<Hospitals> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Hospitals item) {
        ((SimpleDraweeView) helper.getView(R.id.ivHospitalImg)).setImageURI(item.getLogoUrl());
        helper.setText(R.id.tvHospitalName,item.getHospitalName() == null ? "未知医院名" : item.getHospitalName());
        helper.setText(R.id.tvHospitalProperty,item.getHospitalType() == null ? "未知类型" : item.getHospitalType());
        helper.setText(R.id.tvHospitalLevel,item.getHospitalLevelName() == null ? "未知等级" : item.getHospitalLevelName());
        helper.setText(R.id.tvIsCollection,"" + item.getFocus());
        helper.setText(R.id.tvPreOrderNum, CommonUtil.isStrEmpty(item.getAppointmentNum())?"预约量 0":"预约量 " + item.getAppointmentNum());
    }
}
