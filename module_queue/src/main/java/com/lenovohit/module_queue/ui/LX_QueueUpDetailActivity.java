package com.lenovohit.module_queue.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.DoctorQueueUp;
import com.lenovohit.lartemis_api.model.QueueUpModel;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.QueueUpController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.module_queue.R;
import com.lenovohit.module_queue.ui.adapter.QueueUpDetailAdapter;

import java.util.List;

/**
 * Created by SharkChao on 2017-07-14.
 */

public class LX_QueueUpDetailActivity extends CoreActivity<QueueUpController.QueueUpUiCallBack> implements QueueUpController.QueueUpDetailUi{
    private static final String TITLE="title";
    private static final String HID = "hid";
    private static final String DEP_ID = "dep_id";
    private String mTitle;
    private String mHid;
    private String mDep_id;
    private RecyclerView mRecyclerView;
    private QueueUpDetailAdapter mQueueAdapter;
    private EmptyView mEmptyView;
    private DoctorQueueUp mDoctorQueueUp;

    @Override
    protected int getLayoutId() {
        return R.layout.lx_queueup_detail_activity;
    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController().getQueueUpController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.queueRecycle);
        mTitle = getIntent().getStringExtra(TITLE);
        mHid = getIntent().getStringExtra(HID);
        mDep_id = getIntent().getStringExtra(DEP_ID);
        if (!CommonUtil.isStrEmpty(mTitle)){
            setCenterTitle(mTitle);
        }
        mQueueAdapter = new QueueUpDetailAdapter(R.layout.lx_queueup_detail_row);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext(), LinearLayout.VERTICAL,false));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(mRecyclerView.getContext(),LinearLayout.VERTICAL));
        mEmptyView = new EmptyView(mRecyclerView.getContext(), (ViewGroup) mRecyclerView.getParent());
        mEmptyView.setType(EmptyView.TYPE_LOADING);
        mQueueAdapter.setEmptyView(mEmptyView.getView());
        mRecyclerView.setAdapter(mQueueAdapter);
        mQueueAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDoctorQueueUp = mQueueAdapter.getData().get(position);
                boolean isExpand = mDoctorQueueUp.isExpand();
                if (isExpand){
                    mDoctorQueueUp.setExpand(false);
                    adapter.notifyDataSetChanged();
                }else{
                    mDoctorQueueUp.setExpand(true);
                    getGetQueueUp(mDoctorQueueUp.getScheduleCode());
                }
            }
        });
    }

    @Override
    public void initEvent() {

    }
    public static void startQueueDetailActivity(Context context,String title,String hid,String dep_id){
        Intent intent=new Intent(context,LX_QueueUpDetailActivity.class);
        intent.putExtra(TITLE,title);
        intent.putExtra(HID,hid);
        intent.putExtra(DEP_ID,dep_id);
        context.startActivity(intent);
    }
    public void getDoctorQueue(){
        getCallbacks().getDoctorQueue(mHid,mDep_id);
    }

    @Override
    public void getDoctorQueueCallBack(List<DoctorQueueUp> list) {
        if (list!=null && list.size()>=0){
            mQueueAdapter.getData().clear();
            mQueueAdapter.setNewData(list);
        }
        mEmptyView.setType(EmptyView.TYPE_NO_DATA);
        mEmptyView.setMessage("暂时没有排队叫号信息");
    }

    @Override
    public void getOneQueueCallBack(QueueUpModel queueUpModel) {
        if (queueUpModel!=null){
          if (mDoctorQueueUp != null){
              mDoctorQueueUp.setQueueUpModel(queueUpModel);
              mQueueAdapter.notifyDataSetChanged();
          }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDoctorQueue();
    }
    private void getGetQueueUp(String scheduleCode){
        getCallbacks().getOneQueue(mHid,scheduleCode);
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        mEmptyView.setType(EmptyView.TYPE_ERROR);
        mEmptyView.setMessage(error.getMessage());
    }
}
