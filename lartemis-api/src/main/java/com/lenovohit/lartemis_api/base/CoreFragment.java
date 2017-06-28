package com.lenovohit.lartemis_api.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovohit.lartemis_api.annotation.ContentView;

/**
 *
 * Created by yuzhijun on 2017/6/27.
 */
public abstract class CoreFragment<UC> extends BaseFragment<UC> {

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handleArguments(getArguments());
        initViews(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void handleArguments(Bundle arguments);

    protected int getLayoutResId() {
        for (Class c = getClass(); c != Fragment.class; c = c.getSuperclass()) {
            ContentView annotation = (ContentView) c.getAnnotation(ContentView.class);
            if (annotation != null) {
                return annotation.value();
            }
        }
        return 0;
    }
}
