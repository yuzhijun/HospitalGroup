package com.lenovohit.module_queue.ui.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.lartemis_api.model.CommonObj;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.module_queue.R;

/**
 * Created by SharkChao on 2017-07-14.
 * 小科室adapter
 */

public class QueueUpSonAdapter extends BaseQuickAdapter<CommonObj,BaseViewHolder>{

    public QueueUpSonAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonObj item) {
        helper.setText(R.id.tvSonDeptName, CommonUtil.isStrEmpty(item.getTitle())?"科室名称未知":item.getTitle());
    }
}
