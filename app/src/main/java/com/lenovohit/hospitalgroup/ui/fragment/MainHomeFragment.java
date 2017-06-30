package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.HomeMultipleRecycleAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.views.LXHeaderView;
import com.lenovohit.lartemis_api.views.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_mainhome_fragment)
public class MainHomeFragment extends CoreFragment<MainController.MainUiCallbacks>  implements MainController.MainHomeUi,LXHeaderView.RefreshDistanceListener,BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.lx_header_view_rotate)
    LXHeaderView lx_header_view_rotate;
    @BindView(R.id.rvMainHome)
    RecyclerView rvMainHome;
    @BindView(R.id.home_title_bar_layout)
    FrameLayout homeTitleBarLayout;
    private int distanceY;
    private HomeMultipleRecycleAdapter adapter;
    private List<HomePage> mHomePages = new ArrayList<>();

    public static MainHomeFragment newInstance() {
        return new MainHomeFragment();
    }

    @Override
    protected void initViews(View view,Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        isShowToolBar(false);
        initPtrFrame();
        initRecyclerView();

        getCallbacks().getIndexRecommendInfo();
    }

    private void initPtrFrame(){
        lx_header_view_rotate.setOnRefreshDistanceListener(this);
        lx_header_view_rotate.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshMainHomeData();
            }
        });
    }

    private void initRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rvMainHome.getContext(),4, GridLayoutManager.VERTICAL, false);
        rvMainHome.setLayoutManager(gridLayoutManager);
        rvMainHome.addItemDecoration(new SpaceItemDecoration(CommonUtil.dip2px(getContext(),3)));
        rvMainHome.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        adapter = new HomeMultipleRecycleAdapter(mHomePages);
        adapter.setOnLoadMoreListener(this,rvMainHome);
        adapter.setEnableLoadMore(true);
        rvMainHome.setAdapter(adapter);
        isShowToolBar(false);
    }

    @Override
    protected void handleArguments(Bundle arguments) {

    }

    @Override
    protected BaseController getController() {
        return  LArtemis.getInstance().getMainController();
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
    public void onPositionChange(int currentPosY) {
        if (currentPosY > 0) {
            if (homeTitleBarLayout.getVisibility() == View.VISIBLE) {
                homeTitleBarLayout.setVisibility(View.GONE);
            }
        } else {
            if (homeTitleBarLayout.getVisibility() == View.GONE) {
                homeTitleBarLayout.setVisibility(View.VISIBLE);
                distanceY = 0;
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void getIndexRecommendInfoCallBack(HomePage homePage) {
        if(null == homePage || null == homePage.getTopNews()){
            lx_header_view_rotate.refreshComplete();
            return;
        }

        adapter.getData().clear();
        mHomePages.clear();
        mHomePages.add(homePage);
        adapter.setNewData(mHomePages);
        lx_header_view_rotate.refreshComplete();
    }
}
