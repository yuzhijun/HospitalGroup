package com.lenovohit.hospitalgroup.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.model.TopNew;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 主页Adapter
 * Created by yuzhijun on 2017/6/30.
 */
public class HomeMultipleRecycleAdapter extends BaseMultiItemQuickAdapter<HomePage,BaseViewHolder> implements BaseQuickAdapter.SpanSizeLookup, BaseQuickAdapter.OnItemChildClickListener{


    public HomeMultipleRecycleAdapter(List<HomePage> data) {
        super(data);
        setSpanSizeLookup(this);
        addItemType(HomePage.TOP_BANNER, R.layout.lx_top_banner_layout);
        addItemType(HomePage.TOP_MODULE, R.layout.lx_top_module_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePage item) {
       switch (helper.getItemViewType()){
           case HomePage.TOP_BANNER:
                bindToTopBannerData(helper,item);
               break;
           case HomePage.TOP_MODULE:
                bindToModuleData(helper,item);
               break;
       }
    }

    //数据绑定到顶部
    private void bindToTopBannerData(BaseViewHolder helper, final HomePage item){
        BGABanner bgaBanner = helper.getView(R.id.banner);
        bgaBanner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                //点击图标
            }
        });
        bgaBanner.setAdapter(new BGABanner.Adapter<View,TopNew>() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, TopNew topNew, int position) {
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdv_item_fresco_content);
                simpleDraweeView.setImageURI(Uri.parse(topNew.getImgURL()));
            }
        });

        bgaBanner.setData(R.layout.lx_top_banner_content_layout, item.getTopNews(), null);
    }

    //绑定模块
    private void bindToModuleData(BaseViewHolder helper, final HomePage item){
        ((SimpleDraweeView)helper.getView(R.id.ivAppointment)).setImageURI(item.getIndexPageModels().get(0).getIconURL());
        ((SimpleDraweeView)helper.getView(R.id.ivTodayAppointment)).setImageURI(item.getIndexPageModels().get(1).getIconURL());
        ((SimpleDraweeView)helper.getView(R.id.ivMobileTreatment)).setImageURI(item.getIndexPageModels().get(2).getIconURL());
        ((SimpleDraweeView)helper.getView(R.id.ivReportSearch)).setImageURI(item.getIndexPageModels().get(3).getIconURL());

        helper.setText(R.id.tvAppointment,item.getIndexPageModels().get(0).getModuleName());
        helper.setText(R.id.tvTodayAppointment,item.getIndexPageModels().get(1).getModuleName());
        helper.setText(R.id.tvMobileTreatment,item.getIndexPageModels().get(2).getModuleName());
        helper.setText(R.id.tvReportSearch,item.getIndexPageModels().get(3).getModuleName());

        helper.setText(R.id.tvAppointmentDes,item.getIndexPageModels().get(0).getDes());
        helper.setText(R.id.tvTodayAppointmentDes,item.getIndexPageModels().get(1).getDes());
        helper.setText(R.id.tvMobileTreatmentDes,item.getIndexPageModels().get(2).getDes());
        helper.setText(R.id.tvReportSearchDes,item.getIndexPageModels().get(3).getDes());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return mData.get(position).getSpanSize();
    }
}
