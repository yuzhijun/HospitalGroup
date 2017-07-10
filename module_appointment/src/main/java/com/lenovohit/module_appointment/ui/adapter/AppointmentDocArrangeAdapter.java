package com.lenovohit.module_appointment.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.lartemis_api.model.DoctorArrangeShow;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.module_appointment.R;

import java.util.List;

/**
 * Created by yuzhijun on 2017/7/7.
 */
public class AppointmentDocArrangeAdapter extends BaseQuickAdapter<DoctorArrangeShow,BaseViewHolder> {

    public AppointmentDocArrangeAdapter(@LayoutRes int layoutResId, @Nullable List<DoctorArrangeShow> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorArrangeShow doctorArrangeShow) {
        String topLabel = doctorArrangeShow.getTopLabel();
        if (topLabel.length() >= 10){
            //日期格式不显示年
            topLabel = topLabel.substring(5,10);
            helper.setText(R.id.tvTopLabel, CommonUtil.isStrEmpty(topLabel)?"":topLabel);
            helper.setText(R.id.tvBottomLabel,CommonUtil.isStrEmpty(doctorArrangeShow.getBottomLabel())?"":doctorArrangeShow.getBottomLabel());
        }else{
            if (!CommonUtil.isStrEmpty(topLabel)){
                if ("2".equalsIgnoreCase(doctorArrangeShow.getState())){
                    if (doctorArrangeShow.isShow()){
                        helper.getView(R.id.llArrange).setBackgroundResource(R.drawable.lx_btn_line_gray_stroke);
                        helper.getView(R.id.llHasNum).setVisibility(View.GONE);
                        helper.getView(R.id.tvNoNum).setVisibility(View.VISIBLE);
                        helper.setText(R.id.tvNoNum,"约满");
                    }else{
                        helper.getView(R.id.llArrange).setBackgroundResource(R.drawable.lx_btn_suplus_line_stroke);
                        helper.getView(R.id.llHasNum).setVisibility(View.VISIBLE);
                        helper.getView(R.id.tvNoNum).setVisibility(View.GONE);
                    }
                }else if("1".equalsIgnoreCase(doctorArrangeShow.getState())){
                    helper.getView(R.id.llArrange).setBackgroundResource(R.drawable.lx_btn_line_primary_stroke);
                    ((TextView)helper.getView(R.id.tvTopLabel)).setTextColor(CommonUtil.getResColor(R.color.white));
                    helper.setText(R.id.tvTopLabel,CommonUtil.isStrEmpty(topLabel)?"":"2".equalsIgnoreCase(topLabel)?"专家":"普通");
                    ((TextView)helper.getView(R.id.tvBottomLabel)).setTextColor(CommonUtil.getResColor(R.color.white));
                    helper.setText(R.id.tvBottomLabel,CommonUtil.isStrEmpty(doctorArrangeShow.getBottomLabel())?"":doctorArrangeShow.getBottomLabel());
                }else if("0".equalsIgnoreCase(doctorArrangeShow.getState())){
                    helper.getView(R.id.llArrange).setBackgroundResource(R.drawable.lx_btn_line_gray_stroke);
                    helper.getView(R.id.llHasNum).setVisibility(View.GONE);
                    helper.getView(R.id.tvNoNum).setVisibility(View.VISIBLE);
                    helper.setText(R.id.tvNoNum,"停诊");
                }
            }
        }
    }
}
