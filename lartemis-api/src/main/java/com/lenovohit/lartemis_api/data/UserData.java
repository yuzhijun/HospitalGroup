package com.lenovohit.lartemis_api.data;

import com.lenovohit.lartemis_api.model.User;

import java.util.Date;

/**
 * Created by Administrator on 2017-07-04.
 */

public class UserData {
    private static User tempUser; // 当前用户
    private static Date getYZMDateTime;//验证码的时间
    public static User getTempUser() {
        return tempUser;
    }

    public static void setTempUser(User tempUser) {
        UserData.tempUser = tempUser;
    }

    public static Date getGetYZMDateTime() {
        return getYZMDateTime;
    }

    public static void setGetYZMDateTime(Date getYZMDateTime) {
        UserData.getYZMDateTime = getYZMDateTime;
    }
}
