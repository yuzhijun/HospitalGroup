package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.Appoint;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */

public class AppointmentHistoryAdapter extends BaseQuickAdapter<Appoint,BaseViewHolder>{
    public AppointmentHistoryAdapter(@LayoutRes int layoutResId, @Nullable List<Appoint> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Appoint item) {
        helper.setText(R.id.tvHospitalName, CommonUtil.isStrEmpty(item.getHospitalName())?"未知医院名称":item.getHospitalName());
        helper.setText(R.id.tvDepName,CommonUtil.isStrEmpty(item.getDepName())?"未知部门名称":item.getDepName());
        helper.setText(R.id.tvDoctorName,CommonUtil.isStrEmpty(item.getDoctorName())?"未知医生姓名":item.getDoctorName());
        helper.setText(R.id.tvMoney,CommonUtil.isStrEmpty(item.getMoney()+"")?"":item.getMoney()+"元");
        helper.setText(R.id.tvDateTime,CommonUtil.isStrEmpty(item.getAppTime())?"":item.getAppTime());
        helper.setText(R.id.tvPatientName,CommonUtil.isStrEmpty(item.getPName())?"":item.getPName());
        TextView tvState =  helper.getView(R.id.tvState);
        String state = item.getState();
        if (Constants.CANCEL_APPOINTMENT.equals(state)){
            tvState.setBackgroundColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.white));
            tvState.setTextColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.gray_dark));
            tvState.setText("已取消");
        }else if(Constants.APPOINTMENT.equals(state)){
            tvState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"已预约");
        }else if (Constants.REGISTER.equals(state)){
            tvState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"已挂号");
        }else if(Constants.WAITE_CALL.equals(state)){
            tvState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"已呼叫");
        }else if(Constants.VISITING.equals(state)){
            tvState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"正在就诊");
        }else if(Constants.VISITEND.equals(state)){
            tvState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"已就诊");
        }else if(Constants.EXPIRED.equals(state)){
            tvState.setBackgroundColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.white));
            tvState.setTextColor(LArtemis.getInstance().getApplication().getResources().getColor(R.color.gray_dark));
            tvState.setText(CommonUtil.isStrEmpty(state)?"状态未知":"已过期");
        }
    }

}
