package com.lenovohit.hospitalgroup.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lenovohit.hospitalgroup.R;
import com.lenovohit.lartemis_api.annotation.ContentView;
import com.lenovohit.lartemis_api.base.BaseController;
import com.lenovohit.lartemis_api.base.CoreActivity;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.ui.controller.MainController;

import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.activity_main)
public class MainActivity extends CoreActivity<MainController.MainUiCallbacks> implements MainController.MainUi{

    @BindView(R.id.btnNetWork)
    Button btnNetWork;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public void initEvent() {
        btnNetWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCallbacks().getWeatherInfo();
            }
        });
    }

    @Override
    protected BaseController getController() {
        return  LArtemis.getInstance().getMainController();
    }

    @Override
    public void showToast() {
        Toast.makeText(this, "返回网络成功", Toast.LENGTH_SHORT).show();
    }
}
