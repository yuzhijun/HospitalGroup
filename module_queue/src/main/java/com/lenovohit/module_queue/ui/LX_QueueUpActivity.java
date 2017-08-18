package com.lenovohit.module_queue.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.HospitalData;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.CommonObj;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.QueueUp;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.ui.controller.QueueUpController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.lrouter_api.base.LRouterAppcation;
import com.lenovohit.lrouter_api.core.LRouterRequest;
import com.lenovohit.lrouter_api.core.LocalRouter;
import com.lenovohit.lrouter_api.core.callback.IRequestCallBack;
import com.lenovohit.module_queue.R;
import com.lenovohit.module_queue.ui.adapter.QueueUpAdapter;
import com.lenovohit.module_queue.ui.adapter.QueueUpParentAdapter;
import com.lenovohit.module_queue.ui.adapter.QueueUpSonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SharkChao on 2017-07-13.
 */

public class LX_QueueUpActivity extends CoreActivity<QueueUpController.QueueUpUiCallBack> implements QueueUpController.UserQueueUpUi{

    private RecyclerView mRvQueueUp;
    private RecyclerView mParentRecycleView;
    private RecyclerView mSonRecycleView;
    private QueueUpAdapter mQueueAdapter;
    private QueueUpParentAdapter mParentAdapter;
    private QueueUpSonAdapter mSonAdapter;
    private EmptyView mEmptyView;
    private int recycleViewCanShowHeight;
    private int index=0;
    @Override
    protected int getLayoutId() {
        return R.layout.lx_queueup_activity;
    }

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController().getQueueUpController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("排队叫号");
        setRightTitleAndIcon("切换医院", R.mipmap.lx_iv_switch_hospital, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchHospital(Constants.PUT_TYPE_QUEUEUP_DETAIL);
            }
        });
        //查看当前我的排队叫号的位置
        mRvQueueUp = (RecyclerView) findViewById(R.id.rvQueueUp);
        mQueueAdapter = new QueueUpAdapter(R.layout.lx_queue_up_row);
        mRvQueueUp.setLayoutManager(new LinearLayoutManager(mRvQueueUp.getContext(),LinearLayoutManager.VERTICAL,false));
        mRvQueueUp.addItemDecoration(new RecycleViewDivider(mRvQueueUp.getContext(),LinearLayoutManager.VERTICAL));
        mRvQueueUp.setAdapter(mQueueAdapter);

        //查看大科室
        mParentRecycleView= (RecyclerView) findViewById(R.id.parentRecycle);
        mParentAdapter = new QueueUpParentAdapter(R.layout.lx_parent_dept_item);
        mParentRecycleView.setLayoutManager(new LinearLayoutManager(mRvQueueUp.getContext(),LinearLayoutManager.VERTICAL,false));
        mParentRecycleView.addItemDecoration(new RecycleViewDivider(mRvQueueUp.getContext(),LinearLayoutManager.VERTICAL));
        mParentRecycleView.setAdapter(mParentAdapter);

        //查看小科室
        mSonRecycleView = (RecyclerView) findViewById(R.id.sonRecycle);
        mSonAdapter = new QueueUpSonAdapter(R.layout.lx_son_dept_item);
        mSonRecycleView.setLayoutManager(new LinearLayoutManager(mRvQueueUp.getContext(),LinearLayoutManager.VERTICAL,false));
        mSonRecycleView.addItemDecoration(new RecycleViewDivider(mRvQueueUp.getContext(),LinearLayoutManager.VERTICAL));
        mSonRecycleView.setAdapter(mSonAdapter);

        //没有数据时显示的布局
        mParentRecycleView.setVisibility(View.GONE);
        mSonRecycleView.setVisibility(View.VISIBLE);
        mEmptyView = new EmptyView(mSonRecycleView.getContext(), (ViewGroup) mSonRecycleView.getParent());
        mEmptyView.setType(EmptyView.TYPE_LOADING);
        mEmptyView.setMessage("暂无科室信息");
        mSonAdapter.setEmptyView(mEmptyView.getView());

        mRvQueueUp.setNestedScrollingEnabled(false);
    }

    @Override
    public void initEvent() {
        //大科室点击事件
        mParentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (recycleViewCanShowHeight == 0){
                    recycleViewCanShowHeight = mParentRecycleView.getHeight();
                }
                if (recycleViewCanShowHeight > 0 && Build.VERSION.SDK_INT > 10){
                    mParentRecycleView.smoothScrollBy(0, (int) (view.getY() - recycleViewCanShowHeight / 2 + view.getPivotY()));
                }
                mParentAdapter.setSelectedPosition(position);
                mParentAdapter.notifyDataSetChanged();
                index=position;
                showSonDept(mParentAdapter.getData().get(position));
            }
        });

        //小科室点击事件
        mSonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LX_QueueUpDetailActivity.startQueueDetailActivity(LX_QueueUpActivity.this,mSonAdapter.getData().get(position).getTitle(),HospitalData.getCurrentHospital().getHID(),mSonAdapter.getData().get(position).getID());
            }
        });
    }
    public void getMyQueueUpData(){
        Hospitals currentHospital = HospitalData.getCurrentHospital();
        User tempUser = UserData.getTempUser();
        if (tempUser.getBaseInfo()!=null && !CommonUtil.isStrEmpty(tempUser.getBaseInfo().getUID())){
            getCallbacks().getUserQueueUp(currentHospital.getHID(),tempUser.getBaseInfo().getUID());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyQueueUpData();
        getDepData();
    }

    @Override
    public void getUserQueueUpCallBack(List<QueueUp> list) {
        if (list!= null && list.size()>=0){
//            CommonUtil.showSnackBar(mRvQueueUp,"获取数据成功!");
            mQueueAdapter.getData().clear();
            mQueueAdapter.setNewData(list);
        }
        List<QueueUp>list1=new ArrayList<>();
        for (int i=0;i<3;i++){
            QueueUp queueUp=new QueueUp();
            queueUp.setDepCode("100");
            list1.add(queueUp);
        }
        mQueueAdapter.getData().clear();
        mQueueAdapter.setNewData(list1);
    }

    @Override
    public void getAllDepCallBack(List<CommonObj> list) {
        if (list!= null && list.size()>=0){
            if (list.size() == 1){
                mParentRecycleView.setVisibility(View.GONE);
                mSonRecycleView.setVisibility(View.VISIBLE);
                mSonAdapter.getData().clear();
                mSonAdapter.setNewData(list);
            }else {
                mParentRecycleView.setVisibility(View.VISIBLE);
                mSonRecycleView.setVisibility(View.VISIBLE);
                mParentAdapter.getData().clear();
                mParentAdapter.setNewData(list);
                showSonDept(list.get(index));
            }
        }else {
            mParentRecycleView.setVisibility(View.GONE);
            mSonRecycleView.setVisibility(View.VISIBLE);
        }
        mEmptyView.setType(EmptyView.TYPE_NO_DATA);
    }
    //显示小科室,需要传递当前的大科室进去
    private void showSonDept(CommonObj commonObj){
        List<CommonObj> sonDepts = commonObj.getChildrens();
        if(null == sonDepts || sonDepts.size() <= 0){
            mSonAdapter.getData().clear();
            List<CommonObj> commonObjs = new ArrayList<>();
            commonObjs.add(commonObj);
            mSonAdapter.setNewData(commonObjs);
            return;
        }
        mSonAdapter.getData().clear();
        mSonAdapter.getData().addAll(sonDepts);
        mSonAdapter.notifyDataSetChanged();
    }
    private void getDepData(){
        if (HospitalData.getCurrentHospital() != null){
            getCallbacks().getAllDep(HospitalData.getCurrentHospital().getHID());
        }
    }
    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(mRvQueueUp,error.getMessage());
        mEmptyView.setType(EmptyView.TYPE_ERROR);
        mEmptyView.setMessage(error.getMessage());
        mParentRecycleView.setVisibility(View.GONE);
        mSonRecycleView.setVisibility(View.VISIBLE);
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
