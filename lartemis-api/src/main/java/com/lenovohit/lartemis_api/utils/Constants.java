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
    public static final String SP_USER_INFO="user"; //用来保存用户信息
    public static final String COMM_USER_JSON="comm_user";  //用来保存常用联系人

    //跳转到登录页面时，需要判断从哪个界面跳转过来,按界面顺序从上到下
    public static final  String LOGIN_TOP="000";
    public static final String LOGIN_COLLECT_HOSPITAL = "001";
    public static final String LOGIN_COLLECT_DOCTOR = "002";
    public static final String LOGIN_SWITCH_PATIENT = "003";
    public static final String LOGIN_APPOINTMENT_HISTORY = "004";
    public static final String LOGIN_MINE_ADVICE = "005";

    //关注，取消关注   医生或者医院
    //11关注医院；10取消关注医院 ；21关注医生；20取消关注医生
    public static final String FOCUS_DOCTOR_YES="21";
    public static final String FOCUS_DOCTOR_NO="20";
    public static final String FOCUS_HOSPITAL_YES="11";
    public static final String FOCUS_HOSPITAL_NO="10";

    //进程间通讯 传递参数
    public static final String PUT_TYPE="PUT_TYPE";
    public static final String PUT_TYPE_SWITCH_PATIENT="PUT_TYPE_SWITCH_PATIENT";
    public static final String PUT_TYPE_APPOINTMENT="PUT_TYPE_APPOINTMENT";

    //是否关注
    public static final  String IS_COLLECTION = "1";

    //预约，挂号，等待叫号，正在诊疗，已经诊疗，已取消
    public static final String APPOINTMENT = "1";
    public static final String REGISTER = "2";
    public static final String WAITE_CALL = "3";
    public static final String VISITING = "4";
    public static final String VISITEND = "5";
    public static final String CANCEL_APPOINTMENT = "9";
    public static final String EXPIRED  = "10";
}
