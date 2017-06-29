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
import com.lenovohit.lrouter_api.base.LRouterAppcation;
import com.lenovohit.lrouter_api.core.LRouterRequest;
import com.lenovohit.lrouter_api.core.LocalRouter;
import com.lenovohit.lrouter_api.core.callback.IRequestCallBack;

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
//                getCallbacks().getWeatherInfo();
                try{
                    LocalRouter.ListenerFutureTask response = LocalRouter.getInstance(LRouterAppcation.getInstance())
                            .navigation(MainActivity.this, LRouterRequest.getInstance(MainActivity.this)
                                    .processName("com.lenovohit.hospitalgroup:module_appointment")
                                    .provider("AppoinmentProvider")
                                    .action("TestAction")
                                    .param("1", "Hello")
                                    .param("2", "Thread"))
                            .setCallBack(new IRequestCallBack() {
                                @Override
                                public void onSuccess(final String result) {
                                    Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Exception e) {

                                }
                            });
                }catch (Exception e){
                    e.printStackTrace();
                }
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
