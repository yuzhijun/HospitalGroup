package com.lenovohit.lartemis_api.network;

import com.lenovohit.lartemis_api.model.HttpResult;
import com.lenovohit.lartemis_api.model.Weather;

import javax.inject.Singleton;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络接口类
 * Created by yuzhijun on 2016/3/29.
 */
@Singleton
public interface ApiService {
    @GET("service/getIpInfo.php")
    Observable<HttpResult<Weather>> getWeatherResult(@Query("ip") String ip);
}
