package com.lenovohit.module_queue.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.HospitalData;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.QueueUp;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.ui.controller.QueueUpController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;
import com.lenovohit.module_queue.R;
import com.lenovohit.module_queue.ui.adapter.QueueUpAdapter;

import java.util.List;

/**
 * Created by SharkChao on 2017-07-13.
 */

public class LX_QueueUpActivity extends CoreActivity<QueueUpController.QueueUpUiCallBack> implements QueueUpController.UserQueueUpUi{

    private RecyclerView mRvQueueUp;
    private QueueUpAdapter mQueueAdapter;

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
        mRvQueueUp = (RecyclerView) findViewById(R.id.rvQueueUp);
        mQueueAdapter = new QueueUpAdapter(R.layout.lx_queue_up_row);
        mRvQueueUp.setLayoutManager(new LinearLayoutManager(mRvQueueUp.getContext(),LinearLayoutManager.VERTICAL,false));
        mRvQueueUp.addItemDecoration(new RecycleViewDivider(mRvQueueUp.getContext(),LinearLayoutManager.VERTICAL));
        mRvQueueUp.setAdapter(mQueueAdapter);
    }

    @Override
    public void initEvent() {

    }
    public void getMyQueueUpData(){
        Hospitals currentHospital = HospitalData.getCurrentHospital();
        User tempUser = UserData.getTempUser();
        if (tempUser.getBaseInfo()!=null && !CommonUtil.isStrEmpty(tempUser.getBaseInfo().getUID()))
        getCallbacks().getUserQueueUp(currentHospital.getHID(),tempUser.getBaseInfo().getUID());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyQueueUpData();
    }

    @Override
    public void getUserQueueUpCallBack(List<QueueUp> list) {
        if (list!= null && list.size()>=0){
            CommonUtil.showSnackBar(mRvQueueUp,"获取数据成功!");
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(mRvQueueUp,error.getMessage());
    }
}
