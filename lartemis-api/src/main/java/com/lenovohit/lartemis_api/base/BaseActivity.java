package com.lenovohit.lartemis_api.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.ResponseError;
import com.lenovohit.lartemis_api.ui.controller.MainController;
import com.lenovohit.lartemis_api.utils.ActivityStack;

/**
 *  activity基类
 * Created by yuzhijun on 2017/6/15.
 */
public abstract class BaseActivity<UC> extends AppCompatActivity implements BaseController.Ui<UC>{
    private MainController mMainController;
    private UC mCallbacks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mMainController = LArtemis.getInstance().getMainController();
        getController().attachUi(this);
        ActivityStack.create().add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainController.init();
        getController().startUi(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainController.suspend();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getController().detachUi(this);
        ActivityStack.create().remove(this);
    }

    protected abstract int getLayoutId();
    protected abstract BaseController getController();

    @Override
    public void setCallbacks(UC callbacks) {
        mCallbacks = callbacks;
    }

    @Override
    public UC getCallbacks() {
        return mCallbacks;
    }

    @Override
    public void onResponseError(ResponseError error) {}

    protected final MainController getMainController() {
        return mMainController;
    }

}
