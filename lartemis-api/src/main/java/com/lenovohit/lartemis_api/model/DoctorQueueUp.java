package com.lenovohit.lartemis_api.model;

/**
 * 科室下医生排队信息
 * Created by yuzhijun on 2016/11/17.
 */
public class DoctorQueueUp {
    private String ScheduleCode;
    private String DepCode;
    private String DepName;
    private String WorkTime;
    private String DoctorCode;
    private String DoctorName;
    private String DoctorJobName;
    private String DoctorOutPhotoUrl;
    private String AppTypeName;
    private String QueueUpNum;
    private QueueUpModel QueueUpModel;
    private boolean isExpand = false;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public QueueUpModel getQueueUpModel() {
        return QueueUpModel;
    }

    public void setQueueUpModel(QueueUpModel queueUpModel) {
        QueueUpModel = queueUpModel;
    }

    public String getScheduleCode() {
        return ScheduleCode;
    }

    public void setScheduleCode(String ScheduleCode) {
        this.ScheduleCode = ScheduleCode;
    }

    public String getDepCode() {
        return DepCode;
    }

    public void setDepCode(String DepCode) {
        this.DepCode = DepCode;
    }

    public String getDepName() {
        return DepName;
    }

    public void setDepName(String DepName) {
        this.DepName = DepName;
    }

    public String getWorkTime() {
        return WorkTime;
    }

    public void setWorkTime(String WorkTime) {
        this.WorkTime = WorkTime;
    }

    public String getDoctorCode() {
        return DoctorCode;
    }

    public void setDoctorCode(String DoctorCode) {
        this.DoctorCode = DoctorCode;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    public String getDoctorJobName() {
        return DoctorJobName;
    }

    public void setDoctorJobName(String DoctorJobName) {
        this.DoctorJobName = DoctorJobName;
    }

    public String getDoctorOutPhotoUrl() {
        return DoctorOutPhotoUrl;
    }

    public void setDoctorOutPhotoUrl(String DoctorOutPhotoUrl) {
        this.DoctorOutPhotoUrl = DoctorOutPhotoUrl;
    }

    public String getAppTypeName() {
        return AppTypeName;
    }

    public void setAppTypeName(String AppTypeName) {
        this.AppTypeName = AppTypeName;
    }

    public String getQueueUpNum() {
        return QueueUpNum;
    }

    public void setQueueUpNum(String queueUpNum) {
        QueueUpNum = queueUpNum;
    }
}
