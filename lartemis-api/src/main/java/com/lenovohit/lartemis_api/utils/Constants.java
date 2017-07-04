package com.lenovohit.lartemis_api.utils;

/**
 * 公共常量类
 * Created by yuzhijun on 2017/6/27.
 */
public class Constants {

    public class HttpCode {
        public static final int HTTP_UNAUTHORIZED = 401;
        public static final int HTTP_SERVER_ERROR = 500;
        public static final int HTTP_NOT_HAVE_NETWORK = 600;
        public static final int HTTP_NETWORK_ERROR = 700;
        public static final int HTTP_UNKNOWN_ERROR = 800;
    }
    //短信模板编号默认模板001，003为验证现在的手机号码002验证原来的手机号码004新增就诊者
    public static final String SMS_TEMP_CODE = "001";
    public static final String SMS_NOW_CODE = "003";
    public static final String SMS_ADD_PERSON = "004";
    //sp文件中的key变量
    public static final String SP_USER_INFO="user";
}
