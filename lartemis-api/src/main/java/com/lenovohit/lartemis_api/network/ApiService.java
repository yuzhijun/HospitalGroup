package com.lenovohit.lartemis_api.network;

import com.lenovohit.lartemis_api.model.HomePage;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.model.HttpResult;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.http.GET;
import retrofit2.http.Path;
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
}
