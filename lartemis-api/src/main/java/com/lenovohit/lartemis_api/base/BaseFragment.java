package com.lenovohit.lartemis_api.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.lenovohit.lartemis_api.model.ResponseError;

/**
 * fragment基类
 * Created by yuzhijun on 2017/6/27.
 */

public abstract class BaseFragment<UC> extends Fragment implements BaseController.Ui<UC> {
    private UC mCallbacks;

    @Override
    public void setCallbacks(UC callbacks) {
        this.mCallbacks = callbacks;
    }

    @Override
    public UC getCallbacks() {
        return mCallbacks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getController().attachUi(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getController().startUi(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getController().detachUi(this);
    }

    @Override
    public void onResponseError(ResponseError error) {}

    protected abstract BaseController getController();
}
