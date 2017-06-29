package com.lenovohit.lartemis_api.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.lenovohit.lartemis_api.annotation.ContentView;

/**
 * Created by yuzhijun on 2017/6/16.
 */
public abstract class CoreActivity<UC> extends BaseActivity<UC> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initView(savedInstanceState);
        initEvent();
    }

    public abstract void initView(@Nullable Bundle savedInstanceState);
    public abstract void initEvent();

    @Override
    protected int getLayoutId() {
        for (Class c = getClass(); c != Context.class; c = c.getSuperclass()) {
            ContentView annotation = (ContentView) c.getAnnotation(ContentView.class);
            if (annotation != null) {
                return annotation.value();
            }
        }
        return 0;
    }

    protected void addFragment(int containerViewId, Fragment fragment , String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(containerViewId, fragment , tag);
        fragmentTransaction.commit();
    }
}
