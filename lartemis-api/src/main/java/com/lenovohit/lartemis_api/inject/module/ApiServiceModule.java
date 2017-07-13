package com.lenovohit.lartemis_api.inject.module;

import com.lenovohit.lartemis_api.inject.StringConverterFactory;
import com.lenovohit.lartemis_api.network.ApiService;
import com.lenovohit.lartemis_api.network.ResponseErrorProxy;

import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络相关module
 * Created by yuzhijun on 2016/4/28.
 */
@Module
public class ApiServiceModule {
    private static final String BASE_URL =  "http://www.lenovohit.com.cn:10445/";
//    private static final String BASE_URL =  "http://10.63.201.105:8003/";
    private static final int DEFAULT_TIMEOUT = 5;
    private static final int READ_TIMEOUT = 3;

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClientBuilder(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClientBuilder = new OkHttpClient();
        httpClientBuilder.newBuilder()
                .addInterceptor(interceptor)
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return httpClientBuilder;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient OkHttpClientBuilder){
        return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpClientBuilder)
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return getByProxy(ApiService.class,retrofit);
    }

    ApiService getByProxy(Class<? extends ApiService> apiService, Retrofit retrofit){
        ApiService api = retrofit.create(apiService);
        return (ApiService) Proxy.newProxyInstance(apiService.getClassLoader(),new Class<?>[] { apiService },new ResponseErrorProxy(api));
    }
}
