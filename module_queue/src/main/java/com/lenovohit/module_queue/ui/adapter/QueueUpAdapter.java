package com.lenovohit.module_queue.ui.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lenovohit.lartemis_api.model.QueueUp;
import com.lenovohit.module_queue.R;

/**
 * Created by SharkChao on 2017-07-13.
 */

public class QueueUpAdapter extends BaseQuickAdapter<QueueUp,BaseViewHolder>{


    public QueueUpAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueueUp item) {
     /*  SimpleDraweeView img =  helper.getView(R.id.ivHospitalImg);
        helper.setText(R.id.tvDoctorName,CommonUtil.isStrEmpty(item.getDoctorName())?"未知医生":item.getDoctorName());
        helper.setText(R.id.tvDoctorOffice,item.getDepName()+"-"+item.getDoctorJobName()+"-"+item.getAppTypeName());
        helper.setText(R.id.tvTime,CommonUtil.isStrEmpty(item.getExecuteTime())?"":item.getExecuteTime());
        helper.setText(R.id.tvLastQueueNo,CommonUtil.isStrEmpty(item.getQueueUpNum())?"":item.getQueueUpNum());
        helper.setText(R.id.tvQueueUpDes,"你的排班号为"+item.getPatientQueueUpNum()+"号,前面还有"+(Integer.parseInt(item.getPatientQueueUpNum())-Integer.parseInt(item.getQueueUpNum()))+"位患者");
        helper.setText(R.id.tvMyQueueNo,CommonUtil.isStrEmpty(item.getPatientQueueUpNum())?"":item.getPatientQueueUpNum());
        helper.setText(R.id.tvMyQueueName,CommonUtil.isStrEmpty(item.getPatientName())?"":item.getPatientName());
        if (!CommonUtil.isStrEmpty(item.getDoctorOutPhotoUrl())){
            img.setImageURI(Uri.parse(item.getDoctorOutPhotoUrl()));
        }*/
        helper.setText(R.id.tvDoctorName, "未知");
        helper.setText(R.id.tvDoctorOffice,"未知");
        helper.setText(R.id.tvTime,"未知");
        helper.setText(R.id.tvLastQueueNo,"未知");
        helper.setText(R.id.tvQueueUpDes,"你的排班号为"+"100"+"号,前面还有"+"20"+"位患者");
        helper.setText(R.id.tvMyQueueNo,"未知");
        helper.setText(R.id.tvMyQueueName,"未知");

    }
}
