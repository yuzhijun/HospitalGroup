package com.lenovohit.hospitalgroup.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreFragment;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;

/**
 * Created by yuzhijun on 2017/6/29.
 */
@ContentView(R.layout.lx_app_mainhome_fragment)
public class MainHomeFragment extends CoreFragment<MainController.MainUiCallbacks>  implements MainController.MainHomeUi,LXHeaderView.RefreshDistanceListener,BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.lx_header_view_rotate)
    LXHeaderView lx_header_view_rotate;
    @BindView(R.id.rvMainHome)
    RecyclerView rvMainHome;
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
