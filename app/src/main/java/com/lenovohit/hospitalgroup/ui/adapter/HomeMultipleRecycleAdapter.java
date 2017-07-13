package com.lenovohit.hospitalgroup.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.LX_LoginActivity;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.data.HospitalData;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.model.TopNew;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.lrouter_api.base.LRouterAppcation;
import com.lenovohit.lrouter_api.core.LRouterRequest;
import com.lenovohit.lrouter_api.core.LocalRouter;
import com.lenovohit.lrouter_api.core.callback.IRequestCallBack;

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
        addItemType(HomePage.RECOMMOND_HOS,R.layout.lx_main_recommond_hos_layout);
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
           case HomePage.RECOMMOND_HOS:
               bindToRecommendHosData(helper,item);
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
        if (null == item.getIndexPageModels() || item.getIndexPageModels().size() < 4)return;
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

        helper.addOnClickListener(R.id.llAppointment);
        setOnItemChildClickListener(this);
    }

    private void bindToRecommendHosData(BaseViewHolder helper, HomePage homePage){
        if (homePage.getRecommendHospitals() == null || homePage.getRecommendHospitals().size() <= 0) return;
        RecyclerView recyclerView = helper.getView(R.id.rvRecommondHospital);
        LinearLayout llMoreHospital = helper.getView(R.id.llMoreHospital);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL));
        RecommendHosAdapter recommendHosAdapter = new RecommendHosAdapter(R.layout.lx_main_recommond_hos_layout,homePage.getRecommendHospitals());
        recyclerView.setAdapter(recommendHosAdapter);
        llMoreHospital.setFocusableInTouchMode(true);
        llMoreHospital.requestFocus();
        llMoreHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return mData.get(position).getSpanSize();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.llAppointment:
                if (UserData.getTempUser() == null){
                    //首先去登录
                    LX_LoginActivity.startLoginActivity(CoreActivity.currentActivity,Constants.LOGIN_QUEUE_UP);
                }else {
                    if (HospitalData.getCurrentHospital() == null){
                        switchHospital(Constants.PUT_TYPE_APPOINTMENT);
                    }else {
                        //直接跳转到手机预约界面
                        try {
                            LocalRouter.getInstance(LRouterAppcation.getInstance())
                                    .navigation(CoreActivity.currentActivity, LRouterRequest.getInstance(CoreActivity.currentActivity)
                                            .processName("com.lenovohit.hospitalgroup:module_appointment")
                                            .provider("AppoinmentProvider")
                                            .action("AppointmentAction"))
                                    .setCallBack(new IRequestCallBack() {
                                        @Override
                                        public void onSuccess(final String result) {
                                        }
                                        @Override
                                        public void onFailure(Exception e) {
                                        }
                                    });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.llMobileTreatment:
                if (UserData.getTempUser() == null){
                    //首先去登录
                    LX_LoginActivity.startLoginActivity(CoreActivity.currentActivity,Constants.LOGIN_QUEUE_UP);
                }else {
                    if (HospitalData.getCurrentHospital() == null){
                        //如果没有当前医院的话,跳转到选择医院界面。
                        switchHospital(Constants.PUT_TYPE_QUEUEUP);
                    }else {
                        //跳转到排队叫号模块
                        try {
                            LocalRouter.getInstance(LRouterAppcation.getInstance())
                                    .navigation(CoreActivity.currentActivity, LRouterRequest.getInstance(CoreActivity.currentActivity)
                                            .processName("com.lenovohit.hospitalgroup:module_queue")
                                            .provider("QueueProvider")
                                            .action("QueueAction"))
                                    .setCallBack(new IRequestCallBack() {
                                        @Override
                                        public void onSuccess(final String result) {
                                        }
                                        @Override
                                        public void onFailure(Exception e) {
                                        }
                                    });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }
    private void switchHospital(String type){
        try{
            LocalRouter.getInstance(LRouterAppcation.getInstance())
                    .navigation(CoreActivity.currentActivity, LRouterRequest.getInstance(CoreActivity.currentActivity)
                            .processName("com.lenovohit.hospitalgroup:module_appointment")
                            .provider("AppoinmentProvider")
                            .action("EntranceAction")
                            .param(Constants.PUT_TYPE, type))
                    .setCallBack(new IRequestCallBack() {
                        @Override
                        public void onSuccess(final String result) {
                        }
                        @Override
                        public void onFailure(Exception e) {
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
