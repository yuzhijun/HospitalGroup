package com.lenovohit.lartemis_api.network;

import com.lenovohit.lartemis_api.model.HttpResult;
import com.lenovohit.lartemis_api.model.ResponseError;

import rx.functions.Func1;

import static com.lenovohit.lartemis_api.utils.Constants.HttpCode.HTTP_SERVER_ERROR;

/**
 * 处理服务器返回结果
 * Created by yuzhijun on 2017/6/27.
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> tHttpResult) {
        if (tHttpResult.getHasError() > 0){
            throw new ResponseError(HTTP_SERVER_ERROR, tHttpResult.getErrorMessage());
        }
        return tHttpResult.getData();
    }
}
