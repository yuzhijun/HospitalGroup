package com.lenovohit.hospitalgroup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lenovohit.hospitalgroup.R;

/**
 * 欢迎页面
 * Created by yuzhijun on 2017/7/3.
 */
public class LX_WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lx_welcome_activity);

        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent(LX_WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }.sendEmptyMessageDelayed(1, 1000);
    }
}
