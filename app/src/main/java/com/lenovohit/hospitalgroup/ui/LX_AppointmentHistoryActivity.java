package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.AppointmentHistoryAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.Appoint;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SharkChao on 2017-07-10.
 * 预约历史  查看
 */
@ContentView(R.layout.lx_mine_appoint_history_activity)
public class LX_AppointmentHistoryActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.AppointmentHistoryUi {
    @BindView(R.id.lvAppointmentHistory)
    RecyclerView recyclerView;
    private Unbinder bind;
    private AppointmentHistoryAdapter adapter;
    private EmptyView emptyView;
    private List<Appoint>appointmentList=new ArrayList<>();
    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        isShowToolBar(true);
        setCenterTitle("预约历史");
        bind = ButterKnife.bind(this);
        adapter = new AppointmentHistoryAdapter(R.layout.lx_mine_appoint_history_row,appointmentList);
        emptyView = new EmptyView(recyclerView.getContext(), (ViewGroup) recyclerView.getParent());
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setType(EmptyView.TYPE_LOADING);
                getAppointmentHistoryList();
            }
        });
        adapter.setEmptyView(emptyView.getView());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new RecycleViewDivider(recyclerView.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        getAppointmentHistoryList();
    }

    @Override
    public void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("tag",appointmentList.get(position).toString());
                LX_AppointDetailActivity.startAppointDetailActivity(LX_AppointmentHistoryActivity.this,appointmentList.get(position));
            }
        });
    }

    @Override
    public void getAppointmentHistoryCallBack(List<Appoint> list) {
        if (list!= null && list.size()>= 0){
            appointmentList.clear();
            adapter.getData().clear();
            appointmentList.addAll(list);
            adapter.setNewData(appointmentList);
        }
        emptyView.setType(EmptyView.TYPE_NO_DATA);
        emptyView.setMessage("暂时还没有预约历史，赶紧预约吧!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
    public void getAppointmentHistoryList(){
        User tempUser = UserData.getTempUser();
        if (tempUser != null && tempUser.getBaseInfo()!= null && !CommonUtil.isStrEmpty(tempUser.getBaseInfo().getUID())){
            getCallbacks().getAppointmentHistory(tempUser.getBaseInfo().getUID(),"0", "0");
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        emptyView.setMessage(error.getMessage());
        emptyView.setType(EmptyView.TYPE_ERROR);
    }
    public static void startAppointmentHistoryActivity(Context context){
        context.startActivity(new Intent(context,LX_AppointmentHistoryActivity.class));
    }
}
