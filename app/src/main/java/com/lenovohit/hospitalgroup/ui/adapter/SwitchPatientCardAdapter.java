package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.model.CommonUser;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import java.util.List;

/**
 * Created by Administrator on 2017-07-07.
 */

public class SwitchPatientCardAdapter extends BaseQuickAdapter<CommonUser,BaseViewHolder>{
    public SwitchPatientCardAdapter(@LayoutRes int layoutResId, @Nullable List<CommonUser> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonUser item) {
        helper.setText(R.id.tvHospitalName, CommonUtil.isStrEmpty(item.getHospitalName())?"":item.getHospitalName());
        helper.setText(R.id.tvPatientName,CommonUtil.isStrEmpty(item.getName())?"":item.getName());
        helper.setText(R.id.tvSex,CommonUtil.isStrEmpty(item.getSexName())?"":item.getSexName());
        helper.setText(R.id.tvPhone,CommonUtil.isStrEmpty(item.getPhoneNumber())?"":item.getPhoneNumber());
        helper.setText(R.id.tvHospitalCard,CommonUtil.isStrEmpty(item.getHospitalCard())?"":item.getHospitalCard());
        helper.setText(R.id.tvIDCard,CommonUtil.isStrEmpty(item.getIDCard())?"":item.getIDCard());
    }
}
