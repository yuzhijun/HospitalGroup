package com.lenovohit.appointentranceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lenovohit.lartemis_api.utils.Constants;
import com.lenovohit.module_appointment.ui.LX_AppointmentHosActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnStartup)
    Button btnStartup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnStartup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LX_AppointmentHosActivity.class);
                intent.putExtra(Constants.PUT_TYPE,Constants.PUT_TYPE_APPOINTMENT);
                startActivity(intent);
            }
        });
    }
}
