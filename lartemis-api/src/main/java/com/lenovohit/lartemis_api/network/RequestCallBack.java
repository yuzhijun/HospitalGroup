package com.lenovohit.lartemis_api.network;

import android.util.Log;

import com.lenovohit.lartemis_api.model.ResponseError;

import rx.Subscriber;

/**
 * 定制返回方法
 * Created by yuzhijun on 2017/6/27.
 */
public abstract class RequestCallBack<T> extends Subscriber<T> {
    private static final String TAG = "RequestCallback";

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof ResponseError) {
            onFailure((ResponseError) throwable);
        } else {
            Log.e(TAG, "throwable isn't instance of ResponseError");
        }
    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    @Override
    public void onStart() {
    }

    public abstract void onResponse(T response);

    public abstract void onFailure(ResponseError error);
}
