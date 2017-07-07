package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.HomeMultipleRecycleAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.DeepCopyUtil;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.LXHeaderView;
import com.lenovohit.lartemis_api.views.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 首页
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_mainhome_fragment)
public class MainHomeFragment extends CoreFragment<MainController.MainUiCallbacks>  implements MainController.MainHomeUi,LXHeaderView.RefreshDistanceListener{
    /**
     * 改变titlebar中icon颜色时的距离
     */
    private static int DISTANCE_WHEN_TO_SELECTED = 40;
    @BindView(R.id.lx_header_view_rotate)
    LXHeaderView lx_header_view_rotate;
    @BindView(R.id.rvMainHome)
    RecyclerView rvMainHome;
    @BindView(R.id.home_title_bar_bg_view)
    View home_title_bar_bg_view;
    @BindView(R.id.home_title_bar_layout)
    FrameLayout homeTitleBarLayout;
    @BindView(R.id.scanning_layout)
    LinearLayout scanning_layout;
    @BindView(R.id.advisory_layout)
    LinearLayout advisory_layout;
    private int distanceY;
    Unbinder unbinder;
    private HomeMultipleRecycleAdapter adapter;
    private List<HomePage> mHomePages = new ArrayList<>();
    private EmptyView emptyView;

    public static MainHomeFragment newInstance() {
        return new MainHomeFragment();
    }

    @Override
    protected void initViews(View view,Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
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
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshMainHomeData();
            }
        });
    }

    private void initRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rvMainHome.getContext(),4 ,GridLayoutManager.VERTICAL, false);
        rvMainHome.setLayoutManager(gridLayoutManager);
        rvMainHome.addItemDecoration(new SpaceItemDecoration(CommonUtil.dip2px(getContext(),3)));
        rvMainHome.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                distanceY += dy;
                if (distanceY > CommonUtil.dip2px(getContext(), 20)) {
                    home_title_bar_bg_view.setBackgroundColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT > 10) {
                        home_title_bar_bg_view.setAlpha(distanceY * 1.0f / CommonUtil.dip2px(getContext(), 160));
                    }
                    else {
                        DISTANCE_WHEN_TO_SELECTED = 20;
                    }
                }
                else {
                    home_title_bar_bg_view.setBackgroundColor(0);
                }

                if (distanceY > CommonUtil.dip2px(getContext(), DISTANCE_WHEN_TO_SELECTED) && !scanning_layout.isSelected()) {
                    scanning_layout.setSelected(true);
                    advisory_layout.setSelected(true);
                }
                else if (distanceY <= CommonUtil.dip2px(getContext(), DISTANCE_WHEN_TO_SELECTED) && scanning_layout.isSelected()) {
                    scanning_layout.setSelected(false);
                    advisory_layout.setSelected(false);
                }
            }
        });
        adapter = new HomeMultipleRecycleAdapter(mHomePages);
        rvMainHome.setAdapter(adapter);
        emptyView = new EmptyView(rvMainHome.getContext(), (ViewGroup) rvMainHome.getParent());
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setType(EmptyView.TYPE_LOADING);
                getCallbacks().getIndexRecommendInfo();
            }
        });
        adapter.setEmptyView(emptyView.getView());

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
    public void getIndexRecommendInfoCallBack(HomePage homePage) {
        if(null == homePage || null == homePage.getTopNews()){
            lx_header_view_rotate.refreshComplete();
            emptyView.setType(EmptyView.TYPE_NO_DATA);
            return;
        }
        adapter.getData().clear();
        mHomePages.clear();
        try {
            homePage.setItemType(HomePage.TOP_BANNER);
            mHomePages.add(homePage);
            HomePage topModule = (HomePage) DeepCopyUtil.copyBySerialize(homePage);
            topModule.setItemType(HomePage.TOP_MODULE);
            mHomePages.add(topModule);
            HomePage recommendHosModule = (HomePage) DeepCopyUtil.copyBySerialize(homePage);
            recommendHosModule.setItemType(HomePage.RECOMMOND_HOS);
            mHomePages.add(recommendHosModule);
            adapter.setNewData(mHomePages);
            lx_header_view_rotate.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        lx_header_view_rotate.refreshComplete();
        emptyView.setType(EmptyView.TYPE_ERROR);
        emptyView.setMessage(error.getMessage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
