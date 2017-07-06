package com.lenovohit.lartemis_api.data;

import com.google.gson.Gson;
import com.lenovohit.lartemis_api.model.Hospitals;
import com.lenovohit.lartemis_api.utils.CommonUtil;

/**
 * Created by yuzhijun on 2017/7/6.
 */

public class HospitalData {
    public static final String DEFAULT_HOSPITAL = "DefaultHospital"; // 默认选中医院
    private static Hospitals currentHospital;//当前操作的医院

    public static void setCurrentHospital(Hospitals hospital){
        HospitalData.currentHospital = hospital;
        CommonUtil.setShardPString(DEFAULT_HOSPITAL,new Gson().toJson(hospital));
    }

    public static Hospitals getCurrentHospital(){
        if (null == currentHospital){
            String defaultHospital = CommonUtil.getShardPStringByKey(DEFAULT_HOSPITAL);
            if (!CommonUtil.isStrEmpty(defaultHospital)){
                setCurrentHospital(new Gson().fromJson(defaultHospital,Hospitals.class));
            }
        }
        return currentHospital;
    }
}
