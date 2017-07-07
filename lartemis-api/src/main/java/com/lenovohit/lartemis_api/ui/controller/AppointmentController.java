package com.lenovohit.lartemis_api.ui.controller;

import com.google.common.base.Preconditions;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.model.CommonObj;
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lartemis_api.model.DoctorAppoint;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.network.ApiService;
import com.lenovohit.lartemis_api.network.HttpResultFunc;
import com.lenovohit.lartemis_api.network.RequestCallBack;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 预约挂号控制器
 * Created by yuzhijun on 2017/6/27.
 */
public class AppointmentController extends BaseController<AppointmentController.AppointmentUi,AppointmentController.AppointmentUiCallbacks> {
    private ApiService mApiService;

    @Inject
    public AppointmentController(ApiService apiService){
        super();
        mApiService = Preconditions.checkNotNull(apiService, "ApiService cannot be null");
    }

    @Override
    protected void onInited() {
        super.onInited();
    }

    @Override
    protected void onSuspended() {
        super.onSuspended();
    }

    @Override
    protected synchronized void populateUi(AppointmentUi ui) {
        super.populateUi(ui);
    }

    @Override
    protected AppointmentUiCallbacks createUiCallbacks(final AppointmentUi ui) {
        return new AppointmentUiCallbacks() {
            @Override
            public void getSearchHospital(String key) {
                CoreActivity.currentActivity.showProgressDialog();
                mApiService.getSearchHospitalList(key)
                        .map(new HttpResultFunc<List<Hospitals>>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new RequestCallBack<List<Hospitals>>() {
                            @Override
                            public void onResponse(List<Hospitals> response) {
                                CoreActivity.currentActivity.hideProgressDialog();
                                ((AppointmentHosUi)ui).getSearchHospitalCallBack(response);
                            }
                            @Override
                            public void onFailure(ResponseError error) {
                                CoreActivity.currentActivity.hideProgressDialog();
                                ui.onResponseError(error);
                            }
                        });
            }

            @Override
            public void getAllDep(String hid) {
                CoreActivity.currentActivity.showProgressDialog();
                mApiService.getAllDep(hid)
                        .map(new HttpResultFunc<List<CommonObj>>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new RequestCallBack<List<CommonObj>>() {
                            @Override
                            public void onResponse(List<CommonObj> response) {
                                CoreActivity.currentActivity.hideProgressDialog();
                                ((AppointmentMainUi)ui).getAllDepCallBack(response);
                            }

                            @Override
                            public void onFailure(ResponseError error) {
                                CoreActivity.currentActivity.hideProgressDialog();
                                ui.onResponseError(error);
                            }
                        });
            }

            @Override
            public void getDepDoctors(String hid, String depCode, String tag) {
                CoreActivity.currentActivity.showProgressDialog();
                mApiService.getDepDoctors(hid,depCode,tag)
                        .map(new HttpResultFunc<List<Doctor>>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new RequestCallBack<List<Doctor>>() {
                           @Override
                           public void onResponse(List<Doctor> response) {
                               CoreActivity.currentActivity.hideProgressDialog();
                               ((AppointmentDocUi)ui).getDepDoctorsCallBack(response);
                           }
                           @Override
                           public void onFailure(ResponseError error) {
                               CoreActivity.currentActivity.hideProgressDialog();
                               ui.onResponseError(error);
                           }
                       });
            }

            @Override
            public void getDoctorAppoint(String hid, String dCode, String depCode, String tag) {
                CoreActivity.currentActivity.showProgressDialog();
                mApiService.getDoctorAppoint(hid,dCode,depCode,tag)
                        .map(new HttpResultFunc<List<DoctorAppoint>>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new RequestCallBack<List<DoctorAppoint>>() {
                            @Override
                            public void onResponse(List<DoctorAppoint> response) {
                                CoreActivity.currentActivity.hideProgressDialog();
                                ((AppointmentDocInfoUi)ui).getDoctorAppointCallBack(response);
                            }
                            @Override
                            public void onFailure(ResponseError error) {
                                CoreActivity.currentActivity.hideProgressDialog();
                                ui.onResponseError(error);
                            }
                        });

            }
        };
    }

    public interface AppointmentUiCallbacks{//给UI界面调用
        void getSearchHospital(String key);//根据搜索的key获取医院
        void getAllDep(String hid);
        void getDepDoctors(String hid,String depCode,String tag);
        void getDoctorAppoint(String hid,String dCode,String depCode,String tag);
    }

    public interface AppointmentUi extends BaseController.Ui<AppointmentController.AppointmentUiCallbacks> {
        //这里面的方式是给UI界面调用的，要activity等UI层实现
    }

    public interface AppointmentMainUi extends AppointmentUi{
        void getAllDepCallBack(List<CommonObj> response);
    }

    public interface AppointmentHosUi extends AppointmentUi{
        void getSearchHospitalCallBack(List<Hospitals> response);
    }

    public interface AppointmentDocUi extends AppointmentUi{
        void getDepDoctorsCallBack(List<Doctor> response);
    }

    public interface AppointmentDocInfoUi extends AppointmentUi{
        void getDoctorAppointCallBack(List<DoctorAppoint> response);
    }
}
