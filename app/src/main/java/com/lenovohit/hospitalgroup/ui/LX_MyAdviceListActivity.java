package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.MyAdviceAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.MyAdvice;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.lenovohit.hospitalgroup.R.id.recycleView;

/**
 * Created by SharkChao on 2017-07-11.
 */
@ContentView(R.layout.lx_mine_advice_list)
public class LX_MyAdviceListActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MyAdviceListUi {
    @BindView(recycleView)
    RecyclerView mRecycleView;
    private Unbinder mBind;
    private List<MyAdvice>mMyAdviceList=new ArrayList<>();
    private MyAdviceAdapter mMyAdviceAdapter;
    private EmptyView emptyView;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("我的反馈");
        mBind = ButterKnife.bind(this);
        mMyAdviceAdapter = new MyAdviceAdapter(R.layout.lx_mine_advice_list_row,mMyAdviceList);
        emptyView = new EmptyView(mRecycleView.getContext(), (ViewGroup) mRecycleView.getParent());
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setType(EmptyView.TYPE_LOADING);
               getMyAdviceList();
            }
        });
        mMyAdviceAdapter.setEmptyView(emptyView.getView());
        mRecycleView.setLayoutManager(new LinearLayoutManager(mRecycleView.getContext(),LinearLayoutManager.VERTICAL,false));
        mRecycleView.addItemDecoration(new RecycleViewDivider(mRecycleView.getContext(), LinearLayoutManager.VERTICAL));
        mRecycleView.setAdapter(mMyAdviceAdapter);
        getMyAdviceList();
    }

    @Override
    public void initEvent() {
        mMyAdviceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void getMyAdviceListCallBack(List<MyAdvice> advice) {
        if (advice!=null && advice.size()>=0){
            mMyAdviceAdapter.getData().clear();
            mMyAdviceList.clear();
            mMyAdviceList.addAll(advice);
            mMyAdviceAdapter.setNewData(mMyAdviceList);
        }
        emptyView.setType(EmptyView.TYPE_NO_DATA);
        emptyView.setMessage("暂无反馈信息，点击右上角添加吧!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
    public void getMyAdviceList(){
        if (UserData.getTempUser()!=null && UserData.getTempUser().getBaseInfo()!=null && !CommonUtil.isStrEmpty(UserData.getTempUser().getPhoneNumber())){
            getCallbacks().getMyAdvice(UserData.getTempUser().getPhoneNumber());
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        emptyView.setType(EmptyView.TYPE_ERROR);
        emptyView.setMessage(error.getMessage());
    }
    public static void startMyAdviceListActivity(Context context){
        context.startActivity(new Intent(context,LX_MyAdviceListActivity.class));
    }
}
