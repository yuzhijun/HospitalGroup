package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.model.MyAdvice;
import com.lenovohit.lartemis_api.utils.CommonUtil;

import java.util.List;

/**
 * Created by SharkChao on 2017-07-11.
 */

public class MyAdviceAdapter extends BaseQuickAdapter<MyAdvice,BaseViewHolder>{

    public MyAdviceAdapter(@LayoutRes int layoutResId, @Nullable List<MyAdvice> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyAdvice item) {
        helper.setText(R.id.advice, CommonUtil.isStrEmpty(item.getContent())?"":item.getContent());
    }
}
