package com.lenovohit.lartemis_api.utils;


import com.lenovohit.lartemis_api.model.DoctorAppoint;
import com.lenovohit.lartemis_api.model.DoctorArrangeShow;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数据转化为排班对应的数据结构
 * Created by yuzhijun on 2016/10/20.
 */
public class SuplusUtil {
    private static Integer headCount = 0;

    //获取上午的排班
    private static List<DoctorAppoint> getAmArrangement(List<DoctorAppoint> doctorAppoints) throws Exception {
        List<DoctorAppoint> doctorArrangeShows = new ArrayList<>();
        if (doctorAppoints != null && doctorAppoints.size() >= 0){
            for (int i= 0;i < doctorAppoints.size();i++){
                if (CommonUtil.isMorning(doctorAppoints.get(i).getAppStart())){
                    doctorArrangeShows.add(doctorAppoints.get(i));
                }
            }
        }
        return doctorArrangeShows;
    }

    //获取下午的排班
    private static List<DoctorAppoint> getPmArrangement(List<DoctorAppoint> doctorAppoints) throws Exception {
        List<DoctorAppoint> doctorArrangeShows = new ArrayList<>();
        if (doctorAppoints != null && doctorAppoints.size() >= 0){
            for (int i= 0;i < doctorAppoints.size();i++){
                if (!CommonUtil.isMorning(doctorAppoints.get(i).getAppStart())||(CommonUtil.isMorning(doctorAppoints.get(i).getAppStart())&&!CommonUtil.isMorning(doctorAppoints.get(i).getAppEnd()))){
                    doctorArrangeShows.add(doctorAppoints.get(i));
                }
            }
        }
        return doctorArrangeShows;
    }

    //获取头部展示的时间段
    private static List<DoctorArrangeShow> getHeaderView(List<DoctorAppoint> doctorAppoints) throws Exception {
        List<DoctorArrangeShow> doctorArrangeShows = new ArrayList<>();
        if (doctorAppoints != null && doctorAppoints.size() > 0){
            String startDate = doctorAppoints.get(0).getAppDate();
            String endDate = doctorAppoints.get(doctorAppoints.size() - 1).getAppDate();
            List<String> datePeriods = CommonUtil.getDatePeriod(startDate,endDate);
            if (datePeriods != null && datePeriods.size() >= 0){
                for (int i = 0;i < datePeriods.size();i++){
                    DoctorArrangeShow doctorArrangeShow = new DoctorArrangeShow();
                    doctorArrangeShow.setAID("");
                    doctorArrangeShow.setTopLabel(datePeriods.get(i));
                    doctorArrangeShow.setBottomLabel(CommonUtil.getWeekDay(datePeriods.get(i)));
                    doctorArrangeShow.setSuplusNum("0");
                    doctorArrangeShow.setState("");
                    doctorArrangeShow.setSuplus(null);
                    doctorArrangeShow.setShow(false);
                    doctorArrangeShow.setAppStart("");
                    doctorArrangeShow.setAppDate("");
                    doctorArrangeShow.setMoney(0);
                    doctorArrangeShows.add(doctorArrangeShow);
                }
            }
        }else{
            String startDate = CommonUtil.getDateTime();
            String endDate = CommonUtil.getDateTime();
            List<String> datePeriods = CommonUtil.getDatePeriod(startDate,endDate);
            if (datePeriods != null && datePeriods.size() >= 0){
                for (int i = 0;i < datePeriods.size();i++){
                    DoctorArrangeShow doctorArrangeShow = new DoctorArrangeShow("",datePeriods.get(i), CommonUtil.getWeekDay(datePeriods.get(i)),"0","",null,"","",0,false);
                    doctorArrangeShows.add(doctorArrangeShow);
                }
            }
        }
        return doctorArrangeShows;
    }

    //获取用于展示的排班
    public static List<DoctorArrangeShow> getDoctorArrangeShows(List<DoctorAppoint> doctorAppoints) throws Exception {
        List<DoctorArrangeShow> doctorArrangeAmShow = new ArrayList<>();
        List<DoctorArrangeShow> doctorArrangePmShow = new ArrayList<>();
        List<DoctorArrangeShow> headShows = getHeaderView(doctorAppoints);
        headCount = headShows == null ? 0 : headShows.size();
        List<DoctorAppoint> amDoctorAppoints = getAmArrangement(doctorAppoints);
        List<DoctorAppoint> pmDoctorAppoints = getPmArrangement(doctorAppoints);

        //添加上午的排班展示结构
        if (headShows != null && headShows.size() >= 0) {
            for (int i = 0; i < headShows.size(); i++) {
                if (amDoctorAppoints.size() <= 0) {
                    DoctorArrangeShow doctorArrangeShow = new DoctorArrangeShow("", "", "", "","",null,"","",0, false);
                    doctorArrangeAmShow.add(doctorArrangeShow);
                } else {
                    //用头部日期一个一个询问，存在我这个日期的排班吗？存在则添加排班，不存在则添加空排班实体
                    int index = -1;
                    for (int j = 0; j < amDoctorAppoints.size(); j++) {
                        DoctorArrangeShow headShow = headShows.get(i);
                        DoctorAppoint doctorAppoint = amDoctorAppoints.get(j);
                        if (CommonUtil.isSameDay(headShow.getTopLabel(), doctorAppoint.getAppDate())) {
                            index = j;
                            break;
                        } else {
                            index = -1;
                        }
                    }
                    DoctorArrangeShow doctorArrangeShow = null;
                    if (index != -1) {
                        DoctorAppoint doctorAppoint = amDoctorAppoints.get(index);
                        doctorArrangeShow = new DoctorArrangeShow(doctorAppoint.getAID(), doctorAppoint.getAppType(), doctorAppoint.getMoney() + "元", doctorAppoint.getSuplusNum(),doctorAppoint.getState(),doctorAppoint.getSuplus()
                                ,doctorAppoint.getAppStart(),doctorAppoint.getAppDate(),doctorAppoint.getMoney(), false);
                    } else {
                        doctorArrangeShow = new DoctorArrangeShow("", "", "", "","",null,"","",0, false);
                    }
                    doctorArrangeAmShow.add(doctorArrangeShow);
                }
            }
        }

        //添加下午的排班结构
        if (headShows != null && headShows.size() >= 0) {
            for (int i = 0; i < headShows.size(); i++) {
                if (pmDoctorAppoints.size() <= 0) {
                    DoctorArrangeShow doctorArrangeShow = new DoctorArrangeShow("", "", "", "","",null,"","",0, false);
                    doctorArrangePmShow.add(doctorArrangeShow);
                } else {
                    //用头部日期一个一个询问，存在我这个日期的排班吗？存在则添加排班，不存在则添加空排班实体
                    int index = -1;
                    for (int j = 0; j < pmDoctorAppoints.size(); j++) {
                        DoctorArrangeShow headShow = headShows.get(i);
                        DoctorAppoint doctorAppoint = pmDoctorAppoints.get(j);
                        if (CommonUtil.isSameDay(headShow.getTopLabel(), doctorAppoint.getAppDate())) {
                            index = j;
                            break;
                        } else {
                            index = -1;
                        }
                    }
                    DoctorArrangeShow doctorArrangeShow = null;
                    if (index != -1) {
                        DoctorAppoint doctorAppoint = pmDoctorAppoints.get(index);
                        doctorArrangeShow = new DoctorArrangeShow(doctorAppoint.getAID(), doctorAppoint.getAppType(), doctorAppoint.getMoney() + "元", doctorAppoint.getSuplusNum(),doctorAppoint.getState(),doctorAppoint.getSuplus()
                                ,doctorAppoint.getAppStart(),doctorAppoint.getAppDate(),doctorAppoint.getMoney(), false);
                    } else {
                        doctorArrangeShow = new DoctorArrangeShow("", "", "", "","",null,"","",0, false);
                    }
                    doctorArrangePmShow.add(doctorArrangeShow);
                }
            }
        }

        List<DoctorArrangeShow> tempDoctorArrangeShow = new ArrayList<>();
        if (headShows != null && headShows.size() >= 0){
            for (int i = 0; i < headShows.size(); i++) {
                tempDoctorArrangeShow.add(headShows.get(i));
                tempDoctorArrangeShow.add(doctorArrangeAmShow.get(i));
                tempDoctorArrangeShow.add(doctorArrangePmShow.get(i));
            }
        }
        return tempDoctorArrangeShow;
    }

    //获取显示全部排班
    public static List<DoctorArrangeShow> getAllDoctorArrangeShows(List<DoctorArrangeShow> doctorArrangeShows){
        if (doctorArrangeShows != null && doctorArrangeShows.size() > 0){
            for (int i =0;i< doctorArrangeShows.size();i++){
                DoctorArrangeShow doctorArrangeShow = doctorArrangeShows.get(i);
                if (!CommonUtil.isStrEmpty(doctorArrangeShow.getAID())){
                    doctorArrangeShows.get(i).setShow(true);
                }
            }
        }
        return doctorArrangeShows;
    }

    //隐藏约满的排班
    public static List<DoctorArrangeShow> getHideDoctorArrangeShows(List<DoctorArrangeShow> doctorArrangeShows){
        if (doctorArrangeShows != null && doctorArrangeShows.size() > 0){
            for (int i =0;i< doctorArrangeShows.size();i++){
                DoctorArrangeShow doctorArrangeShow = doctorArrangeShows.get(i);
                if (!CommonUtil.isStrEmpty(doctorArrangeShow.getAID()) && "2".equalsIgnoreCase(doctorArrangeShow.getState())){
                    doctorArrangeShows.get(i).setShow(false);
                }
            }
        }
        return doctorArrangeShows;
    }

    public static Integer getHeadCount() {
        return headCount;
    }

    public static void setHeadCount(Integer headCount) {
        SuplusUtil.headCount = headCount;
    }
}
