package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.CollectDoctorAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.views.AlertDialog;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.LXHeaderView;
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
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static com.lenovohit.hospitalgroup.R.id.lx_header_view_rotate;

/**
 * Created by Administrator on 2017-07-05.
 * 关注的医院
 */
@ContentView(R.layout.lx_mine_collect_doctor)
public class LX_DoctorActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.CollectDoctor, LXHeaderView.RefreshDistanceListener {
    @BindView(R.id.tvPreText)
    TextView tvPreText;
    @BindView(R.id.tvDoctorCount)
    TextView tvDoctorCount;
    @BindView(R.id.tvPostText)
    TextView tvPostText;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(lx_header_view_rotate)
    LXHeaderView lxHeaderViewRotate;
    private Unbinder bind;
    private List<Doctor>collectDoctorList=new ArrayList<>();
    private CollectDoctorAdapter adapter;
    private EmptyView emptyView;
    private User tempUser;
    private Doctor doctor;
    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        bind = ButterKnife.bind(this);
        isShowToolBar(true);
        setCenterTitle("关注的医生");
        tempUser = UserData.getTempUser();
        if (tempUser !=null && tempUser.getCollectDoctors()!=null && tempUser.getCollectDoctors().size()>=0){
            tvDoctorCount.setText(tempUser.getCollectDoctors().size()+"");
        }
        adapter = new CollectDoctorAdapter(R.layout.lx_mine_doctor_info_row,collectDoctorList);
        emptyView = new EmptyView(recycleView.getContext(), (ViewGroup) recycleView.getParent());
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setType(EmptyView.TYPE_LOADING);
                getCollectDoctorData();
            }
        });
        adapter.setEmptyView(emptyView.getView());
        recycleView.setLayoutManager(new LinearLayoutManager(recycleView.getContext(),LinearLayoutManager.VERTICAL,false));
        recycleView.addItemDecoration(new RecycleViewDivider(recycleView.getContext(), LinearLayoutManager.VERTICAL));
        recycleView.setAdapter(adapter);

        //下拉刷新
        lxHeaderViewRotate.setOnRefreshDistanceListener(this);
        lxHeaderViewRotate.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getCollectDoctorData();
            }
        });

        //获取数据
        getCollectDoctorData();
    }

    @Override
    public void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                TextView tvCancle = (TextView) view.findViewById(R.id.tvStatus);
                doctor = UserData.getTempUser().getCollectDoctors().get(position);
                tvCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog(CoreActivity.currentActivity).builder()
                                .setTitle("提示")
                                .setMsg("确定要取消关注[" + doctor.getDoctorName() + "]吗?")
                                .setCancelable(false)
                                .setPositiveButton("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        CoreActivity.currentActivity.showProgressDialog();
                                        getCallbacks().focusDoctor(UserData.getTempUser().getBaseInfo().getUID(),collectDoctorList.get(position).getHID(),collectDoctorList.get(position).getDoctorCode(),collectDoctorList.get(position).getDepCode(), Constants.FOCUS_DOCTOR_NO);
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
                    }
                });
                try{
                    LocalRouter.getInstance(LRouterAppcation.getInstance())
                            .navigation(CoreActivity.currentActivity, LRouterRequest.getInstance(CoreActivity.currentActivity)
                                    .processName("com.lenovohit.hospitalgroup:module_appointment")
                                    .provider("AppoinmentProvider")
                                    .action("DoctorAction")
                                    .param("TAG","zxyy")
                                    .requestObject(doctor))

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
    }

    @Override
    public void getCollectDoctorCallBack(List<Doctor> list) {
        if (list!=null && list.size()>=0){
            adapter.getData().clear();
            collectDoctorList.clear();
            collectDoctorList.addAll(list);
            adapter.setNewData(collectDoctorList);
            UserData.getTempUser().setCollectDoctors(list);
            CommonUtil.showSnackBar(tvPostText,"获取数据成功!");
            tvDoctorCount.setText(list.size() + "");

        }
        //不管有没有数据，都要设置以下两项
        emptyView.setType(EmptyView.TYPE_NO_DATA);
        emptyView.setMessage("您还没有关注医生,赶紧关注吧!");
        lxHeaderViewRotate.refreshComplete();
    }

    @Override
    public void FocusDoctorCallBack(Result result) {
        if (result==null || result.getState() <= 0) {
            CommonUtil.showSnackBar(tvPostText,"取消失败");
            return;
        }
        for (int i = collectDoctorList.size() - 1; i >= 0; i--) {
            if (collectDoctorList.get(i).getDoctorCode().equals(doctor.getDoctorCode()) && collectDoctorList.get(i).getHID().equals(doctor.getHID())){
                collectDoctorList.remove(i);
            }

        }
        UserData.getTempUser().setCollectDoctors(collectDoctorList);
        adapter.setNewData(collectDoctorList);
        tvDoctorCount.setText(collectDoctorList.size() + "");
        CommonUtil.showSnackBar(tvDoctorCount,"取消关注成功!");
    }
    public void getCollectDoctorData(){
        User tempUser = UserData.getTempUser();
        if (tempUser!=null&&tempUser.getBaseInfo()!=null&&!CommonUtil.isStrEmpty(tempUser.getBaseInfo().getUID())){
            getCallbacks().getCollectDoctor(tempUser.getBaseInfo().getUID());
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(tvDoctorCount,error.getMessage());
        lxHeaderViewRotate.refreshComplete();
        emptyView.setType(EmptyView.TYPE_ERROR);
        emptyView.setMessage(error.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onPositionChange(int currentPosY) {

    }
    public static void startDoctorActivity(Context context){
        context.startActivity(new Intent(context,LX_DoctorActivity.class));
    }
}
