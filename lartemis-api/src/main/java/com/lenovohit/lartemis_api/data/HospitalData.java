package com.lenovohit.lartemis_api.data;

import com.google.gson.Gson;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.Hospitals;

import net.grandcentrix.tray.AppPreferences;

/**
 * Created by yuzhijun on 2017/7/6.
 */

public class HospitalData {
    public static final String DEFAULT_HOSPITAL = "DefaultHospital"; // 默认选中医院
    private static Hospitals currentHospital;//当前操作的医院
    public static void setCurrentHospital(Hospitals hospital){
        HospitalData.currentHospital = hospital;
        if (hospital !=null){
            AppPreferences sp=new AppPreferences(LArtemis.getInstance().getApplication());
            sp.put("HospitalData",new Gson().toJson(hospital));
        }
    }

    public static Hospitals getCurrentHospital(){
        if (null == currentHospital){
            AppPreferences sp=new AppPreferences(LArtemis.getInstance().getApplication());
            String json = sp.getString("HospitalData", "");
            Hospitals hospitals = new Gson().fromJson(json, Hospitals.class);
            if (hospitals !=null){
                HospitalData.currentHospital=hospitals;
                HospitalData.setCurrentHospital(hospitals);
                return HospitalData.currentHospital;
            }
        }
        return currentHospital;
    }
}
