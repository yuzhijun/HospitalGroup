package com.lenovohit.lartemis_api.network;

import com.lenovohit.lartemis_api.model.HttpResult;

import rx.functions.Func1;

/**
 * 处理服务器返回结果
 * Created by yuzhijun on 2017/6/27.
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> tHttpResult) {
        return null;
    }
}
