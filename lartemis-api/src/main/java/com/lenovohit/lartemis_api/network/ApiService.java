package com.lenovohit.lartemis_api.network;

import com.lenovohit.lartemis_api.model.CommonObj;
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
import retrofit2.http.Headers;
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
    @FormUrlEncoded
    @POST("NeweHealthServices/api/User/SetAppPushInfo")
    Observable<HttpResult<Result>>getLoginValidate(@Field("UID")String UID,@Field("PlatformType") String PlatformType,@Field("OpenID")String OpenID,@Field("AliasType")String AliasType,@Field("Alias")String Alias);
   @FormUrlEncoded
    @POST("NeweHealthServices/api/User/EditUserInfo")
    Observable<HttpResult<Result>>editUserInfo(@Field("uid")String uid,@Field("name")String name,@Field("sex")String sex,@Field("IDCard")String IDCard);
    Observable<HttpResult<Result>> getLoginCode(@Field("PhoneNumber")String PhoneNumber, @Field("TempCode")String TempCode);
    @GET("NeweHealthServices/api/Hospital/SearchHospitals")
    Observable<HttpResult<List<Hospitals>>> getSearchHospitalList(@Query("key") String key);
    @GET("eHealthPlatformService/api/Hospital/AllDep/{hID}")
    Observable<HttpResult<List<CommonObj>>> getAllDep(@Path("hID") String hID);
}
