package com.lenovohit.hospitalgroup.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lenovohit.hospitalgroup.R;
import com.lenovohit.hospitalgroup.ui.adapter.SwitchPatientCardAdapter;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-07-07.
 */
@ContentView(R.layout.lx_mine_switch_patient_manage_activity)
public class LX_SwitchPatientManagerActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.SwitchPatientMangerUi {

    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtVerifyCode)
    EditText edtVerifyCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.btnPatientManage)
    Button btnPatientManage;
    @BindView(R.id.lvHospitalCard)
    RecyclerView recyclerView;
    @BindView(R.id.llSelectHospitalCard)
    LinearLayout llSelectHospitalCard;
    private String hid;
    private Unbinder bind;
    private TimeCount time;
    private List<CommonUser>cardList=new ArrayList<>();
    private SwitchPatientCardAdapter adapter;
    private EmptyView emptyView;
    private String phone;

    @Override
    protected BaseController getController() {
        return LArtemis.getInstance().getMainController();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        bind = ButterKnife.bind(this);
        hid = getIntent().getStringExtra(Constants.PUT_TYPE);
        isShowToolBar(true);
        setCenterTitle("新增就诊者");
        edtPhone.setText("18757574669");
        edtVerifyCode.setText("999999");
        initTime();
        adapter = new SwitchPatientCardAdapter(R.layout.lx_switch_patient_manage_row,cardList);
        emptyView = new EmptyView(recyclerView.getContext(), (ViewGroup) recyclerView.getParent());
        emptyView.setType(EmptyView.TYPE_LOADING);
        emptyView.setRefreshListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCardListData();
            }
        });
        adapter.setEmptyView(emptyView.getView());

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new RecycleViewDivider(recyclerView.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initEvent() {
        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = edtPhone.getText().toString();
                if (CommonUtil.isStrEmpty(phone)){
                    CommonUtil.showSnackBar(edtPhone,"手机号不能为空!");
                }else if (!CommonUtil.isMobileNO(phone)){
                    CommonUtil.showSnackBar(edtPhone,"请输入正确的手机号码");
                }else{
                    //在此处获取验证码
                    btnCode.setEnabled(false);
                    btnCode.setBackgroundResource(R.drawable.btn_gray_button);
                    time=new TimeCount(59000,1000);
                    time.start();
                    getCallbacks().getValiteCode(phone,Constants.SMS_ADD_PERSON);
                }
            }
        });
        btnPatientManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getCardListData();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonUser commonUser = cardList.get(position);
                commonUser.setUID(UserData.getTempUser().getBaseInfo().getUID());
                commonUser.setPhoneNumber(phone);
                List<CommonUser>list=new ArrayList<CommonUser>();
                list.add(commonUser);
                getCallbacks().addCommonUser(list);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getVerifyCodeCallBack(Result result) {
            if (result!=null && result.getState()>0){
                //获取成功
                CommonUtil.showSnackBar(edtPhone,"获取验证码成功!");
            }else {
                //获取失败，可以重新获取
                UserData.setGetYZMDateTime(null);
                btnCode.setText("重新获取验证码");
                btnCode.setEnabled(true);
                btnCode.setBackgroundResource(R.drawable.lx_btn_complete_select);
                time.cancel();
            }
    }

    @Override
    public void verifyCodeCallBack(Result result) {
        if (result!=null && result.getState()>0){
            //点击获取时，先验证验证码是否正确，正确的话就获取就诊卡
            String phone = edtPhone.getText().toString();
            if (!CommonUtil.isStrEmpty(hid)&&!CommonUtil.isStrEmpty(phone)) {
                getCallbacks().getPatientList(hid,phone);
            }
        }else {
            CommonUtil.showSnackBar(edtPhone,"验证码出错了，请重新获取!");
        }
    }

    @Override
    public void getPatientListCallBack(List<CommonUser> list) {
        if (list!=null && list.size()>=0){
            adapter.getData().clear();
            cardList.clear();
            cardList.addAll(list);
            adapter.setNewData(cardList);
        }else {
            CommonUtil.showSnackBar(edtPhone,"获取数据失败，请重新尝试!");
        }
        emptyView.setType(EmptyView.TYPE_NO_DATA);
        emptyView.setMessage("暂无该手机号对应的就诊卡，请到医院办理");
    }

    @Override
    public void addCommonUserCallBack(Result result) {
        if (result!=null && result.getState()>0){
//            CommonUtil.showSnackBar(edtPhone,"添加就诊人成功");
            finish();
//            Log.e("tag",result.toString());
        }
    }

    public void getCardListData(){
        phone = edtPhone.getText().toString();
        if (CommonUtil.isStrEmpty(phone)){
            CommonUtil.showSnackBar(edtPhone,"手机号不能为空");
        }else if(!CommonUtil.isMobileNO(phone)){
            CommonUtil.showSnackBar(edtPhone,"请输入正确的手机号码");
        }else  if (CommonUtil.isStrEmpty(edtVerifyCode.getText().toString())){
            CommonUtil.showSnackBar(edtPhone,"验证码不能为空");
        }else {
            //点击获取新增就诊者
            getCallbacks().verifyCodeIsTrue(phone,edtVerifyCode.getText().toString(),Constants.SMS_ADD_PERSON);
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        super.onResponseError(error);
        CommonUtil.showSnackBar(edtPhone,error.getMessage());
        emptyView.setType(EmptyView.TYPE_ERROR);
        emptyView.setMessage(error.getMessage());
    }
    /**
     * 初始化时时间控件
     * */
    public void initTime() {
        // 初始化时时间控件
        if (UserData.getGetYZMDateTime()!=null) {
            long a = UserData.getGetYZMDateTime().getTime();
            long b = new Date().getTime();
            if (a > b) {
                time = new TimeCount(a - b, 1000);
                time.start();// 开始计时
            }
        } else {
            time = new TimeCount(99000, 1000);
        }
    }

    /**
     * 定时器类
     * */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            btnCode.setEnabled(false);
            btnCode.setText((millisUntilFinished) / 1000 + "秒后重新获取");
            btnCode.setBackgroundResource(R.drawable.btn_gray_button);
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            UserData.setGetYZMDateTime(null);
            btnCode.setText("重新获取验证码");
            btnCode.setEnabled(true);
            btnCode.setBackgroundResource(R.drawable.lx_btn_complete_select);
        }
    }
}
