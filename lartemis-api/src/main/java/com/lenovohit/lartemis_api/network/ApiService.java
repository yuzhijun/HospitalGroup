package com.lenovohit.lartemis_api.network;

import com.lenovohit.lartemis_api.model.CommonObj;
import com.lenovohit.lartemis_api.model.CommonUser;
import com.lenovohit.lartemis_api.model.Doctor;
import com.lenovohit.lartemis_api.model.DoctorAppoint;
import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.HttpResult;
import com.lenovohit.lartemis_api.model.Result;
import com.lenovohit.lartemis_api.model.User;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络接口类
 * Created by yuzhijun on 2016/3/29.
 */
@Singleton
public interface ApiService {
    @GET("NeweHealthServices/api/User/GetIndexRecommendInfo/{cityID}/{uID}")
    Observable<HttpResult<HomePage>> getIndexRecommendInfo(@Path("cityID") int cityID,@Path("uID") int uID);
    @GET("NeweHealthServices/api/Hospital/UserLocationHospitals")
    Observable<HttpResult<List<Hospitals>>>getIndexHospitalList();
    @GET("NeweHealthServices/api/User/LoginInfo/{phoneNumber}/{smsCode}")
    Observable<HttpResult<User>>getLoginData(@Path("phoneNumber")String phoneNumber,@Path("smsCode")String smsCode);
   @FormUrlEncoded
    @POST("NeweHealthServices/api/User/SendSMSCode")
    Observable<HttpResult<Result>>getLoginCode(@Field("PhoneNumber")String PhoneNumber, @Field("TempCode")String TempCode);
   //登录验证
    @FormUrlEncoded
    @POST("NeweHealthServices/api/User/SetAppPushInfo")
    Observable<HttpResult<Result>>getLoginValidate(@Field("UID")String UID,@Field("PlatformType") String PlatformType,@Field("OpenID")String OpenID,@Field("AliasType")String AliasType,@Field("Alias")String Alias);
    @FormUrlEncoded
    @POST("NeweHealthServices/api/User/EditUserInfo")
    Observable<HttpResult<Result>>editUserInfo(@Field("uid")String uid,@Field("name")String name,@Field("sex")String sex,@Field("IDCard")String IDCard);
    @GET("NeweHealthServices/api/Hospital/SearchHospitals")
    Observable<HttpResult<List<Hospitals>>> getSearchHospitalList(@Query("key") String key);
    @GET("eHealthPlatformService/api/Hospital/AllDep/{hID}")
    Observable<HttpResult<List<CommonObj>>> getAllDep(@Path("hID") String hID);
    @GET("NeweHealthServices/api/User/CollectHospitals/{uID}")
    Observable<HttpResult<List<Hospitals>>> getCollectHospitalList(@Path("uID") String uID);
    @GET("NeweHealthServices/api/User/CollectDoctors/{uID}")
    Observable<HttpResult<List<Doctor>>> getCollectDoctorList(@Path("uID") String uID);
    @FormUrlEncoded
    @POST("NeweHealthServices/api/Hospital/FocusOrNotHospitalOrDoctor")
    Observable<HttpResult<Result>>FocusHospOrDoctor(@Field("UID")String UID,@Field("HID")String HID,@Field("DoctorCode") String DoctorCode,@Field("DepCode")String DepCode,@Field("Type")String Type);
    @GET("eHealthPlatformService/api/Hospital/DepDoctors/{hID}/{depCode}/{tag}")
    Observable<HttpResult<List<Doctor>>> getDepDoctors(@Path("hID") String hID,@Path("depCode") String depCode,@Path("tag") String tag);
    @GET("eHealthPlatformService/api/Appointment/DoctorAppoint")
    Observable<HttpResult<List<DoctorAppoint>>> getDoctorAppoint(@Query("hID")String hID,@Query("dCode")String dCode,@Query("depCode")String depCode,@Query("tag")String tag);
    @GET("NeweHealthServices/api/User/GetAllCommonUser/{uID}")
    Observable<HttpResult<List<CommonUser>>>getSwitchPatientList(@Path("uID")String uid);
    @FormUrlEncoded
    @POST("NeweHealthServices/api/User/VerifyPhoneCode")
    Observable<HttpResult<Result>>VelifyCdoe(@Field("PhoneNumber")String PhoneNumber,@Field("SMSCode")String SMSCode,@Field("TempCode")String TempCode);
}
