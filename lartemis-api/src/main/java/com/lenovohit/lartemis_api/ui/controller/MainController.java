package com.lenovohit.lartemis_api.ui.controller;

import com.google.common.base.Preconditions;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.model.Appoint;
import com.lenovohit.lartemis_api.model.CommonUser;
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.HttpResult;
import com.lenovohit.lartemis_api.model.MyAdvice;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.model.User;
import com.lenovohit.lartemis_api.network.ApiService;
import com.lenovohit.lartemis_api.network.HttpResultFunc;
import com.lenovohit.lartemis_api.network.RequestCallBack;

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
            public void getLoginData(String phoneNumber, String code) {
                getIndexLoginUserData(ui,phoneNumber,code);
            }

            @Override
            public void getLoginCode(String phoneNumber, String mode) {
                getIndexLoginCodeData(ui,phoneNumber,mode);
            }

            @Override
            public void editUserInfo(String uid, String name, String sex, String IDCard) {
                editUserInfoData(ui,uid,name,sex,IDCard);
            }

            @Override
            public void getCollectHospital(String uID) {
                getCollectHospitalData(ui,uID);
            }

            @Override
            public void focusHospital(String UID, String HID, String DoctorCode, String DepCode, String Type) {
                FocusDoctorOrHospital(ui,UID,HID,DoctorCode,DepCode,Type);
            }

            @Override
            public void getCollectDoctor(String uID) {
                getCollectDoctorData(ui,uID);
            }

            @Override
            public void focusDoctor(String UID, String HID, String DoctorCode, String DepCode, String Type) {
                FocusDoctorOrHospital(ui,UID,HID,DoctorCode,DepCode,Type);
            }

            @Override
            public void getSwitchPatientList(String UID) {
                getSwitchPatientListData(ui,UID);
            }

            @Override
            public void getValiteCode(String phoneNumber, String type) {
                   getCodeData(ui,phoneNumber,type);
            }

            @Override
            public void verifyCodeIsTrue(String phoneNumber, String code, String type) {
                 verifyCodeData(ui,phoneNumber,code,type);
            }

            @Override
            public void getPatientList(String hid, String phoneNumber) {
                    getPatientListData(ui,hid,phoneNumber);
            }

            @Override
            public void addCommonUser(List<CommonUser> user) {
                addCommonUserData(ui,user);
            }

            @Override
            public void deleteCommonUser(String pid) {
                deleteCommonUserData(ui,pid);
            }

            @Override
            public void getAppointmentHistory(String uid, String pid,String hid) {
                getAppointmentHistoryData(ui,uid,pid,hid);
            }

            @Override
            public void getAppointDetail(String AID) {
                getAppointHistoryDetailData(ui,AID);
            }

            @Override
            public void unAppoint(String aID, String appointmentCode, String hid) {
                unAppointmentData(ui,aID,appointmentCode,hid);
            }

            @Override
            public void getMyAdvice(String phoneNumber) {
                getMyAdviceListData(ui,phoneNumber);
            }

            @Override
            public void sendMyAdvice( String contact, String content) {
                sendMyAdviceData(ui,contact,content);
            }
        };
    }
    //获取预约历史详情页
    private void getAppointHistoryDetailData(final MainUi ui,String aid){
        if (ui  instanceof AppointmentHistoryDetailUi){
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.getAppointDetail(aid)
                    .map(new HttpResultFunc<Appoint>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<Appoint>() {
                        @Override
                        public void onResponse(Appoint response) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ((AppointmentHistoryDetailUi) ui).getAppointDetailCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    private void unAppointmentData(final MainUi ui,String aID,String appointmentCode,String HID){
        if (ui instanceof AppointmentHistoryDetailUi){
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.unAppoint(aID,appointmentCode,HID)
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ((AppointmentHistoryDetailUi) ui).unAppointCallback(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //通过手机号，添加就诊者
    private void addCommonUserData(final MainUi ui,List<CommonUser> user){
        if (ui instanceof SwitchPatientMangerUi){
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.addCommonUser(user)
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ((SwitchPatientMangerUi) ui).addCommonUserCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //添加患者时，获取验证码
    private void getCodeData(final MainUi ui,String phoneNumber,String type){
        if (ui instanceof SwitchPatientMangerUi){
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.getLoginCode(phoneNumber,type)
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new RequestCallBack<Result>() {
                       @Override
                       public void onResponse(Result response) {
                           CoreActivity.currentActivity.hideProgressDialog();
                           ((SwitchPatientMangerUi) ui).getVerifyCodeCallBack(response);
                       }

                       @Override
                       public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                           ui.onResponseError(error);
                       }
                   });
        }
    }
    //添加患者时，验证验证码是否正确
    private void verifyCodeData(final MainUi ui,String phoneNumber,String code,String type){
        if (ui instanceof SwitchPatientMangerUi){
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.VelifyCdoe(phoneNumber,code,type)
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ((SwitchPatientMangerUi) ui).verifyCodeCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //添加患者时，获得此手机号绑定的就诊卡
    private void getPatientListData(final MainUi ui,String hid,String phoneNumber){
        if (ui instanceof SwitchPatientMangerUi){
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.getPatientList(hid,phoneNumber)
                    .map(new HttpResultFunc<List<CommonUser>>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<List<CommonUser>>() {
                        @Override
                        public void onResponse(List<CommonUser> response) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ((SwitchPatientMangerUi) ui).getPatientListCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //获取首页信息
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
                        ui.onResponseError(error);
                    }
                });
    }
    //登录时 获取登录信息
    private void getIndexLoginUserData(final MainUi ui,String phoneNumber,String code){
        if (ui instanceof LoginUi) {
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.getLoginData(phoneNumber,code)
                    .map(new HttpResultFunc<User>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new RequestCallBack<User>() {
                       @Override
                       public void onResponse(User response) {
                           ((LoginUi) ui).getLoginDataCallBack(response);
                            if (response!=null){
                                getIndexLoginValidateData(ui,response);
                            }
                           CoreActivity.currentActivity.hideProgressDialog();
                       }

                       @Override
                       public void onFailure(ResponseError error) {
                           CoreActivity.currentActivity.hideProgressDialog();
    //                       Log.e("tag",error.getMessage());
                           ui.onResponseError(error);
                       }
                   });
        }
    }
    //登录时 获取验证码
    private void getIndexLoginCodeData(final MainUi ui,String phoneNumber,String mode){
        if (ui instanceof LoginUi) {
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.getLoginCode(phoneNumber, mode)
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
                    ui.onResponseError(error);
                }
            });
        }

    }
    //登录时 验证手机
    private void getIndexLoginValidateData(final MainUi ui,User user){
        if (ui instanceof LoginUi) {
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.getLoginValidate(user.getBaseInfo().getUID(),"1",user.getBaseInfo().getPhoneNumber(),"android",user.getBaseInfo().getPhoneNumber())
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            ((LoginUi) ui).getLoginValidateCallBack(response);

                            CoreActivity.currentActivity.hideProgressDialog();
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            //                       Log.e("tag",error.getMessage());
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //编辑用户信息
    public void editUserInfoData(final MainUi ui,String uid,String name,String sex,String IDCard){
        if (ui instanceof UserInfoEditUi){
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.editUserInfo(uid,name,sex,IDCard)
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            ((UserInfoEditUi) ui).editUserInfoCallBack(response);
                            CoreActivity.currentActivity.hideProgressDialog();
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ui.onResponseError(error);
                        }
                    });
        }

    }
    //获取收藏的医院
    public void getCollectHospitalData(final  MainUi ui,String uID){
        if (ui instanceof CollectHospital){
            mApiService.getCollectHospitalList(uID)
                    .map(new HttpResultFunc<List<Hospitals>>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<List<Hospitals>>() {
                        @Override
                        public void onResponse(List<Hospitals> response) {
                            ((CollectHospital) ui).getCollectHospitalCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //获取收藏的医生
    public void getCollectDoctorData(final  MainUi ui,String uID){
        if (ui instanceof CollectDoctor){
            mApiService.getCollectDoctorList(uID)
                    .map(new HttpResultFunc<List<Doctor>>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<List<Doctor>>() {
                        @Override
                        public void onResponse(List<Doctor> response) {
                            ((CollectDoctor) ui).getCollectDoctorCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //关注或取消关注 医生或医院
    public void FocusDoctorOrHospital(final MainUi ui,String UID,String HID,String DoctorCode,String DepCode,String Type){
                CoreActivity.currentActivity.showProgressDialog();
                mApiService.FocusHospOrDoctor(UID,HID,DoctorCode,DepCode,Type)
                        .map(new HttpResultFunc<Result>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new RequestCallBack<Result>() {
                            @Override
                            public void onResponse(Result response) {
                                CoreActivity.currentActivity.hideProgressDialog();
                                if (ui instanceof CollectHospital){
                                    ((CollectHospital) ui).FocusHospitalCallBack(response);
                                }else if (ui instanceof CollectDoctor){
                                    ((CollectDoctor) ui).FocusDoctorCallBack(response);
                                }
                            }

                            @Override
                            public void onFailure(ResponseError error) {
                                ui.onResponseError(error);
                                CoreActivity.currentActivity.hideProgressDialog();
                            }
                        });
    }
    //获取选择患者时的患者列表
    public void getSwitchPatientListData(final  MainUi ui,String uid){
        if (ui instanceof SwitchPatientUi){
            mApiService.getSwitchPatientList(uid)
                    .map(new HttpResultFunc<List<CommonUser>>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<List<CommonUser>>() {
                        @Override
                        public void onResponse(List<CommonUser> response) {
                            ((SwitchPatientUi) ui).getSwitchPatientListCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                           ui.onResponseError(error);
                        }
                    });
        }
    }
    public void deleteCommonUserData(final MainUi ui,String pid){
        if (ui instanceof SwitchPatientUi){
            mApiService.deleteCommonUser(pid)
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            ((SwitchPatientUi) ui).deleteCommonUserCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //获得预约历史记录
    public void getAppointmentHistoryData(final MainUi ui,String uid,String pid,String hid){
        if (ui instanceof AppointmentHistoryUi){
            mApiService.getAppointmentHistory(uid,pid,hid)
                    .map(new HttpResultFunc<List<Appoint>>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<List<Appoint>>() {
                        @Override
                        public void onResponse(List<Appoint> response) {
                            ((AppointmentHistoryUi) ui).getAppointmentHistoryCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            ui.onResponseError(error);
                        }
                    });
        }

    }
    //获取当前手机号下的意见反馈
    public void getMyAdviceListData(final MainUi ui,String phoneNumber){
        if (ui instanceof MyAdviceListUi){
            mApiService.getMyAdviceList(phoneNumber)
                    .map(new HttpResultFunc<List<MyAdvice>>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<List<MyAdvice>>() {
                        @Override
                        public void onResponse(List<MyAdvice> response) {
                            ((MyAdviceListUi) ui).getMyAdviceListCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    //添加意见反馈
    public void sendMyAdviceData(final MainUi ui,String contact,String content){
        if (ui instanceof MyAdviceAddUi){
            CoreActivity.currentActivity.showProgressDialog();
            mApiService.sendMyAdvice(contact,content)
                    .map(new HttpResultFunc<Result>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RequestCallBack<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ((MyAdviceAddUi) ui).getMyAdviceSendCallBack(response);
                        }

                        @Override
                        public void onFailure(ResponseError error) {
                            CoreActivity.currentActivity.hideProgressDialog();
                            ui.onResponseError(error);
                        }
                    });
        }
    }
    public interface MainUiCallbacks{//给UI界面调用
        void getIndexRecommendInfo();
        void getHospitalsList();
        void getLoginData(String phoneNumber,String code);
        void getLoginCode(String phoneNumber,String mode);
        void editUserInfo(String uid,String name,String sex,String IDCard);
        void getCollectHospital(String uID);
        void focusHospital(String UID,String HID,String DoctorCode,String DepCode,String Type);
        void getCollectDoctor(String uID);
        void focusDoctor(String UID,String HID,String DoctorCode,String DepCode,String Type);
        //获取当前用户的患者列表
        void getSwitchPatientList(String UID);
        void getValiteCode(String phoneNumber,String type);
        void verifyCodeIsTrue(String phoneNumber,String code,String type);
        //通过手机号获取就诊卡号
        void getPatientList(String hid,String phoneNumber);
        //添加就诊者
        void addCommonUser(List<CommonUser> user);
        void deleteCommonUser(String pid);
        void getAppointmentHistory(String uid,String pid,String hid);
        void  getAppointDetail(String AID);
        void  unAppoint(String aID,String appointmentCode,String hid);
        void getMyAdvice(String phoneNumber);
        void  sendMyAdvice(String contact,String content);
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
        void getLoginValidateCallBack(Result result);
    }
    public interface  UserInfoEditUi extends MainUi{
       //修改个人信息接口
        void editUserInfoCallBack(Result result);
    }
    public interface  CollectHospital extends MainUi{
        //获取收藏的医院
        void getCollectHospitalCallBack(List<Hospitals>list);
        void FocusHospitalCallBack(Result result);
    }
    public interface  CollectDoctor extends MainUi{
        //获取收藏的医生
        void getCollectDoctorCallBack(List<Doctor>list);
        void FocusDoctorCallBack(Result result);
    }
    public interface SwitchPatientUi extends MainUi{
        void getSwitchPatientListCallBack(List<CommonUser>list);
        void deleteCommonUserCallBack(Result result);
    }
    public interface  SwitchPatientMangerUi extends  MainUi{
        //获取验证码
        void getVerifyCodeCallBack(Result result);
        //验证验证码
        void verifyCodeCallBack(Result result);
        //获取此手机号的就诊卡
        void getPatientListCallBack(List<CommonUser>list);
        //添加就诊者
        void addCommonUserCallBack(Result result);
    }
    public interface  AppointmentHistoryUi extends MainUi{
        void getAppointmentHistoryCallBack(List<Appoint>list);
    }
    public interface AppointmentHistoryDetailUi extends MainUi{
        void getAppointDetailCallBack(Appoint appoint);
        void unAppointCallback(Result result);
    }
    public interface  MyAdviceListUi extends MainUi{
        void getMyAdviceListCallBack(List<MyAdvice>advice);
    }
    public interface  MyAdviceAddUi extends  MainUi{
        void getMyAdviceSendCallBack(Result result);
    }
    public AppointmentController getAppointmentController() {
        return mAppointmentController;
    }

}
