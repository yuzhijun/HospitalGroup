package com.lenovohit.lartemis_api.ui.controller;

import com.google.common.base.Preconditions;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.HttpResult;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.network.ApiService;
import com.lenovohit.lartemis_api.network.HttpResultFunc;
import com.lenovohit.lartemis_api.network.RequestCallBack;
import com.lenovohit.lartemis_api.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 主页p层控制器
 * Created by yuzhijun on 2017/6/16.
 */
public class MainController extends BaseController<MainController.MainUi,MainController.MainUiCallbacks> {
    private static final String IP = "63.223.108.42";
    private ApiService mApiService;
    private AppointmentController mAppointmentController;

    @Inject
    public MainController(ApiService apiService, AppointmentController appointmentController){
        super();

        mApiService = Preconditions.checkNotNull(apiService, "ApiService cannot be null");
        mAppointmentController = Preconditions.checkNotNull(appointmentController, "secondController cannot be null");
    }

    @Override
    protected void onInited() {
        super.onInited();
        //其他controller则在这里init
        mAppointmentController.init();
    }

    @Override
    protected void onSuspended() {
        super.onSuspended();
        //其他controller则在这里suspend
        mAppointmentController.suspend();
    }

    @Override
    protected  void populateUi(MainUi ui) {
        super.populateUi(ui);
    }

    @Override
    protected MainUiCallbacks createUiCallbacks(final MainUi ui) {
        return new MainUiCallbacks() {
            @Override
            public void getIndexRecommendInfo() {
              getIndexRecommendInfoData(ui);
            }

            @Override
            public void getHospitalsList() {
                getIndexHospitalsInfoData(ui);
            }

            @Override
            public void getLoginData() {
                getIndexLoginUserData(ui);
            }

            @Override
            public void getLoginCode() {
                getIndexLoginCodeData(ui);
            }
        };
    }

    private void getIndexRecommendInfoData(final MainUi ui){
        mApiService.getIndexRecommendInfo(31,0)
                .map(new HttpResultFunc<HomePage>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestCallBack<HomePage>() {
                    @Override
                    public void onResponse(HomePage response) {
                        if (ui instanceof MainHomeUi){
                            ((MainHomeUi)ui).getIndexRecommendInfoCallBack(response);
                        }
                    }

                    @Override
                    public void onFailure(ResponseError error) {
                        ui.onResponseError(error);
                    }
                });
    }
    //获取所有医院列表
    private void getIndexHospitalsInfoData(final MainUi ui){

        mApiService.getIndexHospitalList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestCallBack<HttpResult<List<Hospitals>>>() {
                    @Override
                    public void onResponse(HttpResult<List<Hospitals>> response) {
                        if (ui instanceof HospitalUi){
                            ((HospitalUi)ui).getHospitalListCallBack((List<Hospitals>) response);
                        }
                    }

                    @Override
                    public void onFailure(ResponseError error) {
                    }
                });
    }
    //登录时 获取登录信息
    private void getIndexLoginUserData(final MainUi ui){
        if (ui instanceof LoginUi) {
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.getLoginData(((LoginUi) ui).getUserPhone(),((LoginUi) ui).getUserCode())
                    .map(new HttpResultFunc<User>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new RequestCallBack<User>() {
                       @Override
                       public void onResponse(User response) {
                           ((LoginUi) ui).getLoginDataCallBack(response);
                           CoreActivity.currentActivity.hideProgressDialog();
                       }

                       @Override
                       public void onFailure(ResponseError error) {
                           CoreActivity.currentActivity.hideProgressDialog();
    //                       Log.e("tag",error.getMessage());
                       }
                   });
        }
    }
    //登录时 获取验证码
    private void getIndexLoginCodeData(final MainUi ui){
        if (ui instanceof LoginUi) {
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.getLoginCode(((LoginUi) ui).getUserPhone(), Constants.SMS_TEMP_CODE)
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestCallBack<Result>() {
                @Override
                public void onResponse(Result response) {
                    ((LoginUi) ui).getLoginCodeCallBack(response);
                    CoreActivity.currentActivity.hideProgressDialog();
                }

                @Override
                public void onFailure(ResponseError error) {
                    CoreActivity.currentActivity.hideProgressDialog();
//                    Log.e("tag",error.getMessage());
                }
            });
        }

    }
    public interface MainUiCallbacks{//给UI界面调用
        void getIndexRecommendInfo();
        void getHospitalsList();
        void getLoginData();
        void getLoginCode();
    }

    public interface MainUi extends BaseController.Ui<MainUiCallbacks> {
        //这里面的方式是给UI界面调用的，要activity等UI层实现
    }

    public interface MainHomeUi extends MainUi{
        void getIndexRecommendInfoCallBack(HomePage response);
    }
    public interface HospitalUi extends MainUi{
        //获取所有的医院列表
        void getHospitalListCallBack(List<Hospitals>list);
    }
    public interface  LoginUi extends MainUi{
        //登录接口
        void getLoginDataCallBack(User user);
        void getLoginCodeCallBack(Result result);
        String getUserPhone();
        String getUserCode();
    }

    public AppointmentController getAppointmentController() {
        return mAppointmentController;
    }
}
