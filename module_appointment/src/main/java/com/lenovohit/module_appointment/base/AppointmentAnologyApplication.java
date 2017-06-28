package com.lenovohit.module_appointment.base;

import com.lenovohit.annotation.Application;
import com.lenovohit.lrouter_api.base.AnologyApplication;

/**
 * Created by yuzhijun on 2017/6/28.
 */
@Application(name = "com.lenovohit.hospitalgroup:module_appointment",priority = 998)
public class AppointmentAnologyApplication extends AnologyApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
