package com.lenovohit.module_queue.ui.adapter;

import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.lartemis_api.model.DoctorQueueUp;
import com.lenovohit.lartemis_api.model.QueueUpModel;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.module_queue.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SharkChao on 2017-07-14.
 */

public class QueueUpDetailAdapter extends BaseQuickAdapter<DoctorQueueUp,BaseViewHolder>{
    private List<QueueUpModel.PatientQueueUp> mPatientQueueUps = new ArrayList<>();
    public QueueUpDetailAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, DoctorQueueUp item) {
        SimpleDraweeView image = helper.getView(R.id.ivDoctorAvatar);
        ImageView ivExpandArrow = helper.getView(R.id.ivExpandArrow);
        LinearLayout llDoctorQueue = helper.getView(R.id.llDoctorQueueUp);
        final TagFlowLayout tfQueueUpPatient = helper.getView(R.id.tfQueueUpPatient);

        if (!CommonUtil.isStrEmpty(item.getDoctorOutPhotoUrl())){
            image.setImageURI(Uri.parse(item.getDoctorOutPhotoUrl()));
        }
        helper.setText(R.id.tvDoctorName,CommonUtil.isStrEmpty(item.getDoctorName())?"未知医生姓名":item.getDoctorName());
        helper.setText(R.id.tvDoctorOffice,item.getDepName()+"-"+item.getDoctorJobName()+" "+item.getAppTypeName());
        helper.setText(R.id.tvTime,CommonUtil.isStrEmpty(item.getWorkTime())?"":item.getWorkTime());
        //判断是不是有排队叫号的信息，有的话则展开
        ivExpandArrow.setBackgroundResource(item.isExpand()?R.mipmap.lx_iv_arrow_up:R.mipmap.lx_iv_arrow_down);
        if (item.getQueueUpModel()!= null && item.isExpand()){
            llDoctorQueue.setVisibility(View.VISIBLE);
            TagAdapter tagAdapter = new TagAdapter<QueueUpModel.PatientQueueUp>(mPatientQueueUps) {
                @Override
                public View getView(FlowLayout parent, int position, QueueUpModel.PatientQueueUp patientQueueUp) {
                    View queueUpTagView = LayoutInflater.from(helper.itemView.getContext()).inflate(R.layout.queue_up_tag_row,tfQueueUpPatient,false);
                    TextView tvMyQueueNo = (TextView) queueUpTagView.findViewById(R.id.tvMyQueueNo);
                    TextView tvPatientName = (TextView) queueUpTagView.findViewById(R.id.tvPatientName);
                    tvMyQueueNo.setText(CommonUtil.isStrEmpty(patientQueueUp.getPatientQueueUpNum())?"":patientQueueUp.getPatientQueueUpNum());
                    tvPatientName.setText(CommonUtil.isStrEmpty(patientQueueUp.getPatientName())?"":patientQueueUp.getPatientName());
                    return queueUpTagView;
                }
            };
            tfQueueUpPatient.setAdapter(tagAdapter);

            helper.setText(R.id.tvLastQueueNo,CommonUtil.isNotEmpty(item.getQueueUpModel().getCurrentPatient())?item.getQueueUpModel().getCurrentPatient().getPatientQueueUpNum():"");
            helper.setText(R.id.tvLastQueueName,CommonUtil.isNotEmpty(item.getQueueUpModel().getCurrentPatient())?item.getQueueUpModel().getCurrentPatient().getPatientName():"");
            if (item.getQueueUpModel().getQueueUpData() != null && item.getQueueUpModel().getQueueUpData().size() > 0){
                helper.setText(R.id.tvPatientQueueDes,"候诊患者 共" + item.getQueueUpModel().getQueueUpData().size() + "位");
                mPatientQueueUps.clear();
                mPatientQueueUps.addAll(item.getQueueUpModel().getQueueUpData());
                tagAdapter.notifyDataChanged();
            }else{
                helper.setText(R.id.tvPatientQueueDes,"候诊患者 共0位");
                mPatientQueueUps.clear();
                tagAdapter.notifyDataChanged();
            }
        }else {
            llDoctorQueue.setVisibility(View.GONE);
        }
    }
}
