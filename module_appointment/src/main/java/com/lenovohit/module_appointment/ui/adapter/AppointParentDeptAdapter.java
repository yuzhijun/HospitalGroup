package com.lenovohit.module_appointment.ui.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.lartemis_api.model.CommonObj;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.module_appointment.R;

import java.util.List;

/**
 * Created by yuzhijun on 2017/7/5.
 */
public class AppointParentDeptAdapter extends BaseQuickAdapter<CommonObj,BaseViewHolder>{
    private int selectedPosition = 0;

    public AppointParentDeptAdapter(@LayoutRes int layoutResId, @Nullable List<CommonObj> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonObj item) {
       int position = helper.getLayoutPosition() - getHeaderLayoutCount();
        if (selectedPosition == position) {
            helper.getView(R.id.tvHead).setVisibility(View.VISIBLE);
            helper.getView(R.id.llFrame).setBackgroundColor(Color.WHITE);
            ((TextView)helper.getView(R.id.tvName)).setTextColor(CommonUtil.getResColor(R.color.colorPrimary));
        }else{
            helper.getView(R.id.tvHead).setVisibility(View.INVISIBLE);
            helper.getView(R.id.llFrame).setBackgroundColor(CommonUtil.getResColor(R.color.grey_normal_bg));
            ((TextView)helper.getView(R.id.tvName)).setTextColor(Color.BLACK);
        }

        helper.setText(R.id.tvName,CommonUtil.isStrEmpty(item.getTitle())?"科室未知":item.getTitle());
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
