package com.lenovohit.hospitalgroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.CollectHosAdapter;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.data.UserData;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.CommonUtil;
import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.lartemis_api.views.EmptyView;
import com.lenovohit.lartemis_api.views.LXHeaderView;
import com.lenovohit.lartemis_api.views.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2017-07-05.
 */
@ContentView(R.layout.lx_mine_hospital_collect)
public class LX_HospitalsActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.CollectHospital, BaseQuickAdapter.RequestLoadMoreListener, LXHeaderView.RefreshDistanceListener {
    @BindView(R.id.tvPreText)
    TextView tvPreText;
    @BindView(R.id.tvHosPitalCount)
    TextView tvHosPitalCount;
    @BindView(R.id.tvPostText)
    TextView tvPostText;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    List<Hospitals>collectHosList=new ArrayList<>();
    private Unbinder bind;
    private View notDataView;
    @BindView(R.id.lx_header_view_rotate)
     LXHeaderView lx_header_view_rotate;
    private CollectHosAdapter adapter;
    private EmptyView emptyView;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        bind = ButterKnife.bind(this);
        isShowToolBar(true);
        setCenterTitle("关注的医院");
        adapter = new CollectHosAdapter(R.layout.lx_mine_collect_hospital_row,collectHosList);
        emptyView = new EmptyView(recycleView.getContext(), (ViewGroup) recycleView.getParent());
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyView.setType(EmptyView.TYPE_LOADING);
                adapter.setEmptyView(emptyView.getView());
                getCollectHospitalData();
            }
        });
        adapter.setEmptyView(emptyView.getView());
        recycleView.setLayoutManager(new LinearLayoutManager(recycleView.getContext(),LinearLayoutManager.VERTICAL,false));
        recycleView.addItemDecoration(new RecycleViewDivider(recycleView.getContext(), LinearLayoutManager.VERTICAL));
        recycleView.setAdapter(adapter);
        //获取数据
        getCollectHospitalData();
        //设置顶部一共收藏了多少家医院
        User tempUser = UserData.getTempUser();
        if (tempUser!=null&&tempUser.getCollectHospitals()!=null){
            tvHosPitalCount.setText(tempUser.getCollectHospitals().size()+"");
        }
        //下拉刷新
        lx_header_view_rotate.setOnRefreshDistanceListener(this);
        lx_header_view_rotate.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
              getCollectHospitalData();
            }
        });
        //没有数据时的布局,以及点击重试
        notDataView = LayoutInflater.from(recycleView.getContext()).inflate(R.layout.lx_no_data_view, (ViewGroup) recycleView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCollectHospitalData();
            }
        });
    }

    @Override
    public void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                TextView btnUnFocus = (TextView)view.findViewById(R.id.btnCancel);
                btnUnFocus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User tempUser = UserData.getTempUser();
                        if (tempUser!=null&&tempUser.getBaseInfo()!=null){
                            tvHosPitalCount.setText(tempUser.getCollectHospitals().size()+"");
                            getCallbacks().focusHospital(tempUser.getBaseInfo().getUID(),tempUser.getCollectHospitals().get(position).getHID(),"","", Constants.FOCUS_HOSPITAL_NO);
                        }
                    }
                });
            }
        });
    }
    public void getCollectHospitalData(){
        User tempUser = UserData.getTempUser();
        if (tempUser!=null&&tempUser.getBaseInfo()!=null&&!CommonUtil.isStrEmpty(tempUser.getBaseInfo().getUID())){
            getCallbacks().getCollectHospital(tempUser.getBaseInfo().getUID());
        }
    }
    @Override
    public void getCollectHospitalCallBack(List<Hospitals> list) {
        if (list!=null&&list.size()>=0){
            adapter.getData().clear();
            adapter.setNewData(list);
            UserData.getTempUser().setCollectHospitals(list);
            CommonUtil.showSnackBar(tvPostText,"获取数据成功!");
            tvHosPitalCount.setText(list.size() + "");
        }
        emptyView.setType(EmptyView.TYPE_NO_DATA);
        emptyView.setMessage("您还没有关注医院，赶紧关注吧");
        adapter.setEmptyView(emptyView.getView());
        lx_header_view_rotate.refreshComplete();
    }

    @Override
    public void FocusHospitalCallBack(Result result) {
        if (result==null || result.getState() <= 0) {
          CommonUtil.showSnackBar(tvPostText,"取消失败");
            return;
        }
            for (int i = collectHosList.size() - 1; i >= 0; i--) {
                if (collectHosList.get(i).getHID().equals(UserData.getTempUser().getCollectHospitals().get(i).getHID()))
                    collectHosList.remove(i);
            }
            UserData.getTempUser().setCollectHospitals(collectHosList);
            adapter.setNewData(collectHosList);
            tvHosPitalCount.setText(collectHosList.size() + "");
            CommonUtil.showSnackBar(tvHosPitalCount,"取消关注成功!");
        }


    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(tvPostText,error.getMessage());
        lx_header_view_rotate.refreshComplete();
        emptyView.setType(EmptyView.TYPE_ERROR);
        adapter.setEmptyView(emptyView.getView());
    }


    @Override
    public void onLoadMoreRequested() {

    }
    public static void startHospitalActivity(Context context){
        context.startActivity(new Intent(context,LX_HospitalsActivity.class));
    }

    @Override
    public void onPositionChange(int currentPosY) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
