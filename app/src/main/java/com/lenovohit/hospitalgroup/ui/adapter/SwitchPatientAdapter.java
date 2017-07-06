package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.model.CommonUser;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import java.util.List;

/**
 * Created by Administrator on 2017-07-06.
 */

public class SwitchPatientAdapter extends BaseQuickAdapter<CommonUser,BaseViewHolder>{
    public SwitchPatientAdapter(@LayoutRes int layoutResId, @Nullable List<CommonUser> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonUser item) {
       helper.setText(R.id.tvHosName, CommonUtil.isStrEmpty(item.getHospitalName())?"":item.getHospitalName());
        helper.setText(R.id.tvPatientName,CommonUtil.isStrEmpty(item.getName())?"":item.getName());
        helper.setText(R.id.tvPatientSex,CommonUtil.isStrEmpty(item.getSex())?"":item.getSexName());
        helper.setText(R.id.tvPatientPhoneNumber,CommonUtil.isStrEmpty(item.getPhoneNumber())?"":item.getPhoneNumber());
        helper.setText(R.id.tvPatientJZCard,CommonUtil.isStrEmpty(item.getHospitalCard())?"":"就诊卡号:"+item.getHospitalCard());
        helper.setText(R.id.tvPatientIDCard,CommonUtil.isStrEmpty(item.getIDCard())?"":"身份证号:"+item.getIDCard());
        ImageView ivCheckBox = helper.getView(R.id.ivCheckedBox);
        if (item.isSelected()){
            ivCheckBox.setImageResource(R.mipmap.lx_iv_check_on);
        }else {
            ivCheckBox.setImageResource(R.mipmap.lx_iv_check_out);
        }
    }
}
