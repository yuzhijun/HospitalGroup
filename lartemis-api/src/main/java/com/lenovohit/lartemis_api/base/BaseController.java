package com.lenovohit.lartemis_api.base;

import com.google.common.base.Preconditions;
import com.lenovohit.lartemis_api.model.ResponseError;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * p层父类
 * Created by yuzhijun on 2017/6/15.
 */
public abstract class BaseController<U extends BaseController.Ui<UC>, UC> {

    private final Set<U> mUis;
    private final Set<U> mUnmodifiableUis;
    //controller是否被初始化
    private boolean mInited;

    public BaseController() {
        //初始化页面集合
        mUis = new CopyOnWriteArraySet<>();
        mUnmodifiableUis = Collections.unmodifiableSet(mUis);

    }

    public final void init() {
        Preconditions.checkState(!mInited, "Already inited");
        onInited();
        mInited = true;
    }

    public final void suspend() {
        Preconditions.checkState(mInited, "Not inited");
        onSuspended();
        mInited = false;
    }

    protected abstract UC createUiCallbacks(U ui);
    protected synchronized void populateUi(U ui) {}

    protected void onInited() {}

    protected void onSuspended() {}

    public final boolean isInited() {
        return mInited;
    }

    public synchronized final void  attachUi(U ui) {
        Preconditions.checkArgument(ui != null, "ui cannot be null");
        Preconditions.checkState(!mUis.contains(ui), "UI is already attached");
        mUis.add(ui);
        ui.setCallbacks(createUiCallbacks(ui));
    }

    public synchronized final void startUi(U ui) {
        Preconditions.checkArgument(ui != null, "ui cannot be null");
        Preconditions.checkState(mUis.contains(ui), "ui is not attached");
        populateUi(ui);
    }

    public synchronized final void detachUi(U ui) {
        Preconditions.checkArgument(ui != null, "ui cannot be null");
        Preconditions.checkState(mUis.contains(ui), "ui is not attached");
        ui.setCallbacks(null);
        mUis.remove(ui);
    }

    protected synchronized final void populateUis() {
        for (U ui : mUis) {
            populateUi(ui);
        }
    }

    protected final Set<U> getUis() {
        return mUnmodifiableUis;
    }

    protected int getId(U ui) {
        return ui.hashCode();
    }

    protected synchronized U findUi(final int id) {
        for (U ui : mUis) {
            if (getId(ui) == id) {
                return ui;
            }
        }
        return null;
    }

    public interface Ui<UC> {
        void setCallbacks(UC callbacks);

        UC getCallbacks();

        void onResponseError(ResponseError error);
    }
}
