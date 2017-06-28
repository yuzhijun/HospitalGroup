package com.lenovohit.lartemis_api.network;

import com.google.gson.JsonParseException;
import com.lenovohit.lartemis_api.R;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.utils.GsonHelper;
import com.lenovohit.lartemis_api.utils.StringFetcher;

import org.apache.http.conn.ConnectTimeoutException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Func1;

import static com.lenovohit.lartemis_api.utils.Constants.HttpCode.HTTP_NETWORK_ERROR;
import static com.lenovohit.lartemis_api.utils.Constants.HttpCode.HTTP_SERVER_ERROR;
import static com.lenovohit.lartemis_api.utils.Constants.HttpCode.HTTP_UNAUTHORIZED;
import static com.lenovohit.lartemis_api.utils.Constants.HttpCode.HTTP_UNKNOWN_ERROR;


public class ResponseErrorProxy implements InvocationHandler {

    public static final String TAG = ResponseErrorProxy.class.getSimpleName();

    private Object mProxyObject;

    public ResponseErrorProxy(Object proxyObject) {
        mProxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, final Method method, final Object[] args) {
        return Observable.just(null)
                .flatMap(new Func1<Object, Observable<?>>() {
                    @Override
                    public Observable<?> call(Object o) {
                        try {
                            return (Observable<?>) method.invoke(mProxyObject, args);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                ResponseError error = null;
                                if (throwable instanceof ConnectTimeoutException
                                        || throwable instanceof SocketTimeoutException
                                        || throwable instanceof UnknownHostException
                                        || throwable instanceof ConnectException) {
                                    error = new ResponseError(HTTP_NETWORK_ERROR,
                                            StringFetcher.getString(R.string.toast_error_network));
                                } else if (throwable instanceof HttpException) {
                                    HttpException exception = (HttpException) throwable;
                                    try {
                                        error = GsonHelper.builderGson().fromJson(
                                                exception.response().errorBody().string(), ResponseError.class);
                                    } catch (Exception e) {
                                        if (e instanceof JsonParseException) {
                                            error = new ResponseError(HTTP_SERVER_ERROR,
                                                    StringFetcher.getString(R.string.toast_error_server));
                                        } else {
                                            error = new ResponseError(HTTP_UNKNOWN_ERROR,
                                                    StringFetcher.getString(R.string.toast_error_unknown));
                                        }
                                    }
                                } else if (throwable instanceof JsonParseException) {
                                    error = new ResponseError(HTTP_SERVER_ERROR,
                                            StringFetcher.getString(R.string.toast_error_server));
                                } else {
                                    error = new ResponseError(HTTP_UNKNOWN_ERROR,
                                            StringFetcher.getString(R.string.toast_error_unknown));
                                }

                                if (error.getStatus() == HTTP_UNAUTHORIZED) {
                                    return refreshTokenWhenTokenInvalid();
                                } else {
                                    return Observable.error(error);
                                }
                            }
                        });
                    }
                });
    }

    private Observable<?> refreshTokenWhenTokenInvalid() {
        synchronized (ResponseErrorProxy.class) {
            //TODO
            return null;
        }
    }
}
