package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.AllHosAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.views.DropDownMenu;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.LXHeaderView;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_hospital_fragment)
public class HospitalFragment extends CoreFragment<MainController.MainUiCallbacks> implements MainController.MainHomeUi,LXHeaderView.RefreshDistanceListener, BaseQuickAdapter.RequestLoadMoreListener {
    LXHeaderView lx_header_view_rotate;
    @BindView(R.id.dropDown)
    DropDownMenu dropDownMenu;
    private List<View>popupViews;
    private List<String> titleList;
    private TagAdapter<String> propertyAdapter;
    private TagAdapter<String> typeAdapter;
    //医院等级
    private List<String> propertyTitleList = new ArrayList<>();
    //医院类型
    private List<String> typeTitleList = new ArrayList<>();
    //第二个下拉矿中的view
    private RecyclerView orderView;
    private AllHosAdapter adapter;
    Unbinder mUnbinder;
    List<Hospitals> hospitalList = new ArrayList<>();
    private EmptyView emptyView;

    public static HospitalFragment newInstance() {
        return new HospitalFragment();
    }

    @Override
    protected void initViews(final View view, Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this,view);
        setCenterTitle("医院");
        setRightTitleAndIcon("", R.mipmap.lx_iv_search_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showSnackBar(view,"您点击了搜索按钮");
            }
        });
       setLeftTitleAndIcon("全国", R.mipmap.lx_iv_location_icon, new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               CommonUtil.showSnackBar(view,"您点击了定位按钮");
           }
       });
        //添加tab标题
        titleList = new ArrayList<>();
        titleList.add("医院性质");
         titleList.add("综合排序");

        //添加具体下拉布局
        popupViews=new ArrayList<>();
        //第一项的下拉布局
        View oneTabView =  LayoutInflater.from(getActivity()).inflate(R.layout.lx_dropdown_hospital_property_view,null);
        popupViews.add(oneTabView);
        final TagFlowLayout propertyView = (TagFlowLayout) oneTabView.findViewById(R.id.tflHospitalProperty);
        final TagFlowLayout typeView= (TagFlowLayout) oneTabView.findViewById(R.id.tflHospitalType);

        propertyTitleList.add("三级甲等");
        propertyTitleList.add("二级乙等");
        propertyAdapter = new TagAdapter<String>(propertyTitleList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.lx_common_tag_textview,
                        propertyView, false);
                tv.setText(s);
                return tv;
            }
        };
        typeTitleList.add("公立医院");
        typeTitleList.add("私立医院");
        typeAdapter = new TagAdapter<String>(typeTitleList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.lx_common_tag_textview,
                        typeView, false);
                tv.setText(s);
                return tv;
            }
        };
        propertyView.setAdapter(propertyAdapter);
        typeView.setAdapter(typeAdapter);

        //第二项的下拉布局
        orderView = (RecyclerView)LayoutInflater.from(getActivity()).inflate(R.layout.lx_dropdown_hospital_order_view,null);
        popupViews.add(orderView);

        //添加dropDownView下边的布局
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.lx_dropdown_hospital_arrow_view, null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.AllHosRecyclerView);
        adapter = new AllHosAdapter(R.layout.lx_recommond_hos_view_item,hospitalList);
        adapter.setOnLoadMoreListener(this);
        adapter.setEnableLoadMore(false);
        emptyView = new EmptyView(recyclerView.getContext(), (ViewGroup) recyclerView.getParent());
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setType(EmptyView.TYPE_LOADING);
                getCallbacks().getIndexRecommendInfo();
            }
        });
        adapter.setEmptyView(emptyView.getView());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new RecycleViewDivider(recyclerView.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        getCallbacks().getIndexRecommendInfo();

        //下拉刷新
        lx_header_view_rotate= (LXHeaderView) inflate.findViewById(R.id.lx_header_view_rotate);
        lx_header_view_rotate.setOnRefreshDistanceListener(this);
        lx_header_view_rotate.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshMainHomeData();
            }
        });

        dropDownMenu.setDropDownMenu(titleList,popupViews,inflate);

    }

    @Override
    protected void handleArguments(Bundle arguments) {

    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    public void getHospitalListCallBack(List<Hospitals> list) {
        if (list==null||list.size()==0){
            emptyView.setType(EmptyView.TYPE_NO_DATA);
            adapter.setEmptyView(emptyView.getView());
            lx_header_view_rotate.refreshComplete();
            return;
        }
        emptyView.setType(EmptyView.TYPE_NO_DATA);
        hospitalList.clear();
        adapter.setNewData(hospitalList);
        lx_header_view_rotate.refreshComplete();

    }

    @Override
    public void onPositionChange(int currentPosY) {

    }
    //下拉刷新主页数据
    private void refreshMainHomeData(){
        lx_header_view_rotate.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCallbacks().getIndexRecommendInfo();
            }
        },1000);
    }

    @Override
    public void getIndexRecommendInfoCallBack(HomePage response) {
        if(null == response || null == response.getTopNews()){
            lx_header_view_rotate.refreshComplete();
            emptyView.setType(EmptyView.TYPE_NO_DATA);
            return;
        }
        adapter.getData().clear();
        hospitalList.clear();
        hospitalList.addAll(response.getRecommendHospitals());
        adapter.setNewData(hospitalList);
        lx_header_view_rotate.refreshComplete();
        adapter.loadMoreEnd();
        emptyView.setType(EmptyView.TYPE_NO_DATA);
        emptyView.setMessage("还没有医院,请重试");
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        lx_header_view_rotate.refreshComplete();
        emptyView.setType(EmptyView.TYPE_ERROR);
        emptyView.setMessage(error.getMessage());
    }

    @Override
    public void onLoadMoreRequested() {
        refreshMainHomeData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
