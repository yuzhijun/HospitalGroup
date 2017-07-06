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

public class CollectHosAdapter extends BaseQuickAdapter<Hospitals,BaseViewHolder>{
    public CollectHosAdapter(@LayoutRes int layoutResId, @Nullable List<Hospitals> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Hospitals item) {
        ((SimpleDraweeView)helper.getView(R.id.hospitalIv)).setImageURI(item.getLogoUrl());
        helper.setText(R.id.hospitalNameTv, CommonUtil.isStrEmpty(item.getHospitalName())?"未知医院名称":item.getHospitalName());
        helper.setText(R.id.tvHospitalLevel,CommonUtil.isStrEmpty(item.getHospitalLevelName())?"未知等级":item.getHospitalLevelName());
        helper.setText(R.id.tvHospitalProperty,CommonUtil.isStrEmpty(item.getHospitalType())?"未知类型":item.getHospitalType());
        helper.setText(R.id.metersTv,"暂无数据");
        helper.setText(R.id.attentionNumTv,"暂无数据");
    }

}
