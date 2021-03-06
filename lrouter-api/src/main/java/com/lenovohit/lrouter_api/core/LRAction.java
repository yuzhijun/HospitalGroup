package com.lenovohit.lrouter_api.core;

import android.content.Context;


/**
 * 响应动作
 * Created by yuzhijun on 2017/5/27.
 */
public abstract class LRAction<T> {

    //是否需要非阻塞访问
    public abstract boolean needAsync(Context context, LRouterRequest<T> requestData);
    //调用action动作
    public abstract LRActionResult invoke(Context context, LRouterRequest<T> requestData);
}
