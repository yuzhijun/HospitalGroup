package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

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
         ImageView ivStatus = helper.getView(R.id.ivState);
        TextView tvTime=helper.getView(R.id.tvTime);
        String status = item.getReplyName();
        if (CommonUtil.isStrEmpty(status)) {
            ivStatus.setBackgroundResource(R.mipmap.lx_iv_unreply);
        } else {
            ivStatus.setBackgroundResource(R.mipmap.lx_iv_reply);
        }
        String replyTime = item.getReplyTime();
        if (CommonUtil.isStrEmpty(replyTime)||replyTime.equals("0001-01-01 00:00:00")){
            if (!CommonUtil.isStrEmpty(item.getCreateTime())){
                tvTime.setText(item.getCreateTime());
            }
        }else {
            tvTime.setText(replyTime);
        }
    }
}
