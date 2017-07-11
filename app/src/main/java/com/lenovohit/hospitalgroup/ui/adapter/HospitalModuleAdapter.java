package com.lenovohit.hospitalgroup.ui.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.model.HospitalMainPage;

/**
 * Created by yuzhijun on 2017/7/11.
 */
public class HospitalModuleAdapter extends BaseQuickAdapter<HospitalMainPage.IHospitalModule,BaseViewHolder> {

    public HospitalModuleAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalMainPage.IHospitalModule item) {
        ((SimpleDraweeView)helper.getView(R.id.sdvModuleLogo)).setImageURI(item.getIconURL());
        helper.setText(R.id.tvModuleName,item.getModuleName());
        helper.setText(R.id.tvModuleDes,item.getDes());
    }
}
