package com.lenovohit.module_appointment.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.lartemis_api.model.CommonObj;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.module_appointment.R;

import java.util.List;

/**
 * Created by yuzhijun on 2017/7/5.
 */

public class AppointSonDeptAdapter extends BaseQuickAdapter<CommonObj,BaseViewHolder> {

    public AppointSonDeptAdapter(@LayoutRes int layoutResId, @Nullable List<CommonObj> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonObj item) {
        helper.setText(R.id.tvSonDeptName, CommonUtil.isStrEmpty(item.getTitle())?"科室名称未知":item.getTitle());
    }
}
