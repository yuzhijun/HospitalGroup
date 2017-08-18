package com.lenovohit.lartemis_api.data;

import com.google.gson.Gson;
import com.lenovohit.lartemis_api.core.LArtemis;
import com.lenovohit.lartemis_api.model.User;

import net.grandcentrix.tray.AppPreferences;

import java.util.Date;

/**
 * Created by Administrator on 2017-07-04.
 */

public class UserData {
    private static User tempUser; // 当前用户
    private static Date getYZMDateTime;//验证码的时间
    public static User getTempUser() {
        if (tempUser == null){
            AppPreferences sp=new AppPreferences(LArtemis.getInstance().getApplication());
            String json = sp.getString("UserData", "");
            User user = new Gson().fromJson(json, User.class);
            if (user!= null){
                UserData.setTempUser(user);
                UserData.tempUser=user;
            }
        }
        return tempUser;
    }

    public static void setTempUser(User tempUser) {
        UserData.tempUser = tempUser;
        if (tempUser !=null){
            AppPreferences sp=new AppPreferences(LArtemis.getInstance().getApplication());
            sp.put("UserData",new Gson().toJson(tempUser));
        }
    }

    public static Date getGetYZMDateTime() {
        return getYZMDateTime;
    }

    public static void setGetYZMDateTime(Date getYZMDateTime) {
        UserData.getYZMDateTime = getYZMDateTime;
    }
}
