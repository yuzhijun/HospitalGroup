package com.lenovohit.lartemis_api.model;

/**
 * 获取用户在某个医院下面绑定的所有就诊卡的排队
 * Created by yuzhijun on 2016/11/15.
 */
public class QueueUp {
    private String ScheduleCode;
    private String DepCode;
    private String DepName;
    private String DoctorCode;
    private String DoctorName;
    private String DoctorJobName;
    private String AppTypeName;
    private String QueueUpNum;
    private String PatientName;
    private String HospitalCard;
    private String PatientQueueUpNum;
    private String ExecuteTime;
    private String HospitalName;
    private String HID;
    private String WorkTime;
    private String DoctorOutPhotoUrl;


    public String getWorkTime() {
        return WorkTime;
    }

    public void setWorkTime(String workTime) {
        WorkTime = workTime;
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

    public String getAppTypeName() {
        return AppTypeName;
    }

    public void setAppTypeName(String AppTypeName) {
        this.AppTypeName = AppTypeName;
    }


    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getHospitalCard() {
        return HospitalCard;
    }

    public void setHospitalCard(String HospitalCard) {
        this.HospitalCard = HospitalCard;
    }

    public String getExecuteTime() {
        return ExecuteTime;
    }

    public void setExecuteTime(String ExecuteTime) {
        this.ExecuteTime = ExecuteTime;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

    public String getQueueUpNum() {
        return QueueUpNum;
    }

    public void setQueueUpNum(String queueUpNum) {
        QueueUpNum = queueUpNum;
    }

    public String getPatientQueueUpNum() {
        return PatientQueueUpNum;
    }

    public void setPatientQueueUpNum(String patientQueueUpNum) {
        PatientQueueUpNum = patientQueueUpNum;
    }

    public String getHID() {
        return HID;
    }

    public void setHID(String HID) {
        this.HID = HID;
    }

    public String getDoctorOutPhotoUrl() {
        return DoctorOutPhotoUrl;
    }

    public void setDoctorOutPhotoUrl(String doctorOutPhotoUrl) {
        DoctorOutPhotoUrl = doctorOutPhotoUrl;
    }

    @Override
    public String toString() {
        return "QueueUp{" +
                "ScheduleCode='" + ScheduleCode + '\'' +
                ", DepCode='" + DepCode + '\'' +
                ", DepName='" + DepName + '\'' +
                ", DoctorCode='" + DoctorCode + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                ", DoctorJobName='" + DoctorJobName + '\'' +
                ", AppTypeName='" + AppTypeName + '\'' +
                ", QueueUpNum='" + QueueUpNum + '\'' +
                ", PatientName='" + PatientName + '\'' +
                ", HospitalCard='" + HospitalCard + '\'' +
                ", PatientQueueUpNum='" + PatientQueueUpNum + '\'' +
                ", ExecuteTime='" + ExecuteTime + '\'' +
                ", HospitalName='" + HospitalName + '\'' +
                ", HID='" + HID + '\'' +
                ", WorkTime='" + WorkTime + '\'' +
                ", DoctorOutPhotoUrl='" + DoctorOutPhotoUrl + '\'' +
                '}';
    }
}
