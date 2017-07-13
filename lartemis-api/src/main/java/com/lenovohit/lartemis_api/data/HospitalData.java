package com.lenovohit.lartemis_api.data;

import com.google.gson.Gson;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.utils.DiskLruCacheHelper;

import java.io.IOException;

/**
 * Created by yuzhijun on 2017/7/6.
 */

public class HospitalData {
    public static final String DEFAULT_HOSPITAL = "DefaultHospital"; // 默认选中医院
    private static Hospitals currentHospital;//当前操作的医院
    private static Hospitals sHospitals;

    public static void setCurrentHospital(Hospitals hospital){
        HospitalData.currentHospital = hospital;
        if (hospital !=null){
            try {
                DiskLruCacheHelper helper=new DiskLruCacheHelper(LArtemis.getInstance().getApplication());
                helper.put("HospitalData",new Gson().toJson(currentHospital));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Hospitals getCurrentHospital(){
        if (null == currentHospital){
            DiskLruCacheHelper helper= null;
            try {
                helper = new DiskLruCacheHelper(LArtemis.getInstance().getApplication());
                String json = helper.getAsString("HospitalData");
                sHospitals = new Gson().fromJson(json, Hospitals.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (sHospitals !=null){
                HospitalData.currentHospital=sHospitals;
                HospitalData.setCurrentHospital(currentHospital);
                return HospitalData.currentHospital;
            }
        }
        return currentHospital;
    }
}
