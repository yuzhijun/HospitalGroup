package com.lenovohit.lartemis_api.ui.controller;


import com.google.common.base.Preconditions;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.model.CommonObj;
import com.lenovohit.lartemis_api.model.DoctorQueueUp;
import com.lenovohit.lartemis_api.model.QueueUp;
import com.lenovohit.lartemis_api.model.QueueUpModel;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.network.ApiService;
import com.lenovohit.lartemis_api.network.HttpResultFunc;
import com.lenovohit.lartemis_api.network.RequestCallBack;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SharkChao on 2017-07-13.
 * 排队叫号的p层处理
 */

public class QueueUpController extends BaseController<QueueUpController.QueueUpUi,QueueUpController.QueueUpUiCallBack> {

    private ApiService mApiService;

    @Inject
    public QueueUpController(ApiService apiService){
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
    protected synchronized void populateUi(QueueUpUi ui) {
        super.populateUi(ui);
    }


    @Override
    protected QueueUpUiCallBack createUiCallbacks(final QueueUpUi ui) {
        return new QueueUpUiCallBack() {

            @Override
            public void getUserQueueUp(String hid, String uid) {
                getUserQueueUpData(ui,hid,uid);
            }

            @Override
            public void getAllDep(String hid) {
                if (ui instanceof UserQueueUpUi){
                    mApiService.getAllDep(hid)
                            .map(new HttpResultFunc<List<CommonObj>>())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new RequestCallBack<List<CommonObj>>() {
                                @Override
                                public void onResponse(List<CommonObj> response) {
                                    ((UserQueueUpUi)ui).getAllDepCallBack(response);
                                }

                                @Override
                                public void onFailure(ResponseError error) {
                                    ui.onResponseError(error);
                                }
                            });
                }
            }

            @Override
            public void getDoctorQueue(String hid, String depCode) {
                if (ui  instanceof QueueUpDetailUi) {
                    mApiService.getAllDoctorQueue(hid,depCode)
                            .map(new HttpResultFunc<List<DoctorQueueUp>>())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new RequestCallBack<List<DoctorQueueUp>>() {
                                @Override
                                public void onResponse(List<DoctorQueueUp> response) {
                                    ((QueueUpDetailUi) ui).getDoctorQueueCallBack(response);
                                }

                                @Override
                                public void onFailure(ResponseError error) {
                                    ui.onResponseError(error);
                                }
                            });
                }
            }

            @Override
            public void getOneQueue(String hid, String ScheduleCode) {
                if (ui instanceof  QueueUpDetailUi){
                    CoreActivity.currentActivity.showProgressDialog();
                    mApiService.getOneQueue(hid,ScheduleCode)
                            .map(new HttpResultFunc<QueueUpModel>())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new RequestCallBack<QueueUpModel>() {
                                @Override
                                public void onResponse(QueueUpModel response) {
                                    CoreActivity.currentActivity.hideProgressDialog();
                                    ((QueueUpDetailUi) ui).getOneQueueCallBack(response);
                                }

                                @Override
                                public void onFailure(ResponseError error) {
                                    CoreActivity.currentActivity.hideProgressDialog();
                                    ui.onResponseError(error);
                                }
                            });
                }
            }
        };
    }

    //获取当前用户下的排队叫号
    public void getUserQueueUpData(final QueueUpUi ui,String hid,String uid){
        if (ui instanceof UserQueueUpUi){
            mApiService.getUserQueueUp(hid,uid)
                    .map(new HttpResultFunc<List<QueueUp>>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<List<QueueUp>>() {
                        @Override
                        public void onResponse(List<QueueUp> response) {
                            ((UserQueueUpUi) ui).getUserQueueUpCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            ui.onResponseError(error);
                        }
                    });
        }
    }


    public interface QueueUpUiCallBack{
        void getUserQueueUp(String hid,String uid);
        void getAllDep(String hid);
        void getDoctorQueue(String hid,String depCode);
        void getOneQueue(String hid,String ScheduleCode);
    }

    public interface QueueUpUi extends BaseController.Ui<QueueUpUiCallBack> {
    }
    public interface UserQueueUpUi extends QueueUpUi{
        void getUserQueueUpCallBack(List<QueueUp>list);
        void getAllDepCallBack(List<CommonObj>list);
    }
    public interface  QueueUpDetailUi extends QueueUpUi{
       void  getDoctorQueueCallBack(List<DoctorQueueUp>list);
        void getOneQueueCallBack(QueueUpModel list);
    }
}
