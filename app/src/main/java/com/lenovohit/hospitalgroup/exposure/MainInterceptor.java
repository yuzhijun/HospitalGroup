package com.lenovohit.hospitalgroup.exposure;

import com.lenovohit.annotation.Interceptor;
import com.lenovohit.lrouter_api.intercept.AopInterceptor;

/**
 * 请求拦截器
 * Created by yuzhijun on 2017/6/28.
 */
@Interceptor
public class MainInterceptor extends AopInterceptor {
    @Override
    public void enterRequestIntercept(String methodName, String[] paramNames, Object[] paramValues) {

    }

    @Override
    public void exitRequestIntercept(String methodName, String[] paramNames, Object[] paramValues, long lengthMillis) {

    }
}
