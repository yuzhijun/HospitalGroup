package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.gson.Gson;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.SwitchPatientAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.CommonUser;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.lrouter_api.base.LRouterAppcation;
import com.lenovohit.lrouter_api.core.LRouterRequest;
import com.lenovohit.lrouter_api.core.LocalRouter;
import com.lenovohit.lrouter_api.core.callback.IRequestCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-07-06.
 */
@ContentView(R.layout.lx_mine_switchpatient)
public class LX_SwitchPatientActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.SwitchPatientUi {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private Unbinder bind;
    private List<CommonUser>commonUsers=new ArrayList<>();
    private SwitchPatientAdapter adapter;
    private int selectedIndex;//设置默认的联系人选中项
    private EmptyView emptyView;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("切换患者");
        setRightTitleAndIcon("", R.mipmap.lx_iv_top_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    LocalRouter.getInstance(LRouterAppcation.getInstance())
                            .navigation(CoreActivity.currentActivity, LRouterRequest.getInstance(CoreActivity.currentActivity)
                                    .processName("com.lenovohit.hospitalgroup:module_appointment")
                                    .provider("AppoinmentProvider")
                                    .action("EntranceAction")
                                    .param(Constants.PUT_TYPE, Constants.PUT_TYPE_SWITCH_PATIENT))
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
        });
        bind = ButterKnife.bind(this);
        adapter = new SwitchPatientAdapter(R.layout.lx_mine_switchpaint_row,commonUsers);

        emptyView = new EmptyView(recycleView.getContext(), (ViewGroup) recycleView.getParent());
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setType(EmptyView.TYPE_LOADING);
                getPatientListData();
            }
        });
        adapter.setEmptyView(emptyView.getView());

        recycleView.setLayoutManager(new LinearLayoutManager(recycleView.getContext(),LinearLayoutManager.VERTICAL,false));
        recycleView.addItemDecoration(new RecycleViewDivider(recycleView.getContext(), LinearLayoutManager.VERTICAL));
        recycleView.setAdapter(adapter);

    }

    @Override
    public void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                commonUsers.get(selectedIndex).setSelected(false);
                commonUsers.get(position).setSelected(true);
                selectedIndex=position;
                adapter.notifyDataSetChanged();
                CommonUtil.setShardPString(Constants.COMM_USER_JSON,new Gson().toJson(commonUsers.get(position)));
                finish();
            }
        });
        ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        mItemDragAndSwipeCallback.setSwipeMoveFlags( ItemTouchHelper.START);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback );
        mItemTouchHelper.attachToRecyclerView(recycleView);

        adapter.enableSwipeItem();
//        adapter.enableDragItem(mItemTouchHelper);

        adapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            }
            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }
            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {

            }
            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
    private void getPatientListData(){
        if (UserData.getTempUser()!=null && UserData.getTempUser().getBaseInfo()!=null && !CommonUtil.isStrEmpty(UserData.getTempUser().getBaseInfo().getUID())){
            getCallbacks().getSwitchPatientList(UserData.getTempUser().getBaseInfo().getUID());
        }
    }

    @Override
    public void getSwitchPatientListCallBack(List<CommonUser> list) {
        if (list!=null || list.size()>0){
            adapter.getData().clear();
            commonUsers.clear();
            commonUsers.addAll(list);
            adapter.setNewData(commonUsers);
            setSelectIndexItem(commonUsers);
            UserData.getTempUser().setCommonUsers(commonUsers);
        }
            emptyView.setType(EmptyView.TYPE_NO_DATA);
            emptyView.setMessage("暂无患者列表，请点击右上角添加");

    }

    @Override
    public void deleteCommonUserCallBack(Result result) {
        if (result!=null && result.getState()>0){
            CommonUtil.showSnackBar(emptyView,"删除就诊者成功");
        }
    }

    public static void startSwitchPatientActivity(Context context){
        context.startActivity(new Intent(context,LX_SwitchPatientActivity.class));
    }
    /**
     * 设置recycleview的选中项
     * */
    private void setSelectIndexItem(List<CommonUser> commonUsers) {
        // 查找看本地是否有选中的常用联系人，如果没有则默认选中第一个
        String commonUserJson = CommonUtil.getShardPStringByKey(Constants.COMM_USER_JSON);
        if (CommonUtil.isStrEmpty(commonUserJson)) {
            if(commonUsers != null && commonUsers.size() > 0){
                selectedIndex = 0;
                commonUsers.get(selectedIndex).setSelected(true);
                CommonUtil.setShardPString(Constants.COMM_USER_JSON, new Gson().toJson(commonUsers.get(0)));
            }
        } else {
            CommonUser selectedUser = new Gson().fromJson(commonUserJson, CommonUser.class);
            for (int i = 0; i < commonUsers.size(); i++) {
                if (commonUsers.get(i).getPID().equalsIgnoreCase(selectedUser.getPID())) {
                    commonUsers.get(i).setSelected(true);
                    selectedIndex = i;
                }else{
                    commonUsers.get(i).setSelected(false);
                }
            }
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(recycleView,error.getMessage());
        emptyView.setType(EmptyView.TYPE_ERROR);
        emptyView.setMessage(error.getMessage());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPatientListData();
    }
}
