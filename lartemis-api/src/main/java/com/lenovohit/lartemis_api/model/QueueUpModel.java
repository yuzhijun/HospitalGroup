package com.lenovohit.lartemis_api.model;

import java.util.List;

/**
 * 某个医生下面的排队
 * Created by yuzhijun on 2016/11/17.
 */
public class QueueUpModel {
    private String ScheduleCode;
    private String DepCode;
    private String DepName;
    private String WorkTime;
    private String DoctorCode;
    private String DoctorName;
    private String DoctorOutPhotoUrl;
    private String DoctorJobName;
    private String AppTypeName;
    private String QueueUpNum;
    private PatientQueueUp CurrentPatient;
    private List<PatientQueueUp> QueueUpData;

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

    public String getDoctorOutPhotoUrl() {
        return DoctorOutPhotoUrl;
    }

    public void setDoctorOutPhotoUrl(String DoctorOutPhotoUrl) {
        this.DoctorOutPhotoUrl = DoctorOutPhotoUrl;
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

    public String getQueueUpNum() {
        return QueueUpNum;
    }

    public PatientQueueUp getCurrentPatient() {
        return CurrentPatient;
    }

    public void setCurrentPatient(PatientQueueUp currentPatient) {
        CurrentPatient = currentPatient;
    }

    public void setQueueUpNum(String queueUpNum) {
        QueueUpNum = queueUpNum;
    }

    public List<PatientQueueUp> getQueueUpData() {
        return QueueUpData;
    }

    public void setQueueUpData(List<PatientQueueUp> queueUpData) {
        QueueUpData = queueUpData;
    }

    public class PatientQueueUp{
        private String PatientName;
        private String HospitalCard;
        private String PatientQueueUpNum;

        public String getPatientName() {
            return PatientName;
        }

        public void setPatientName(String patientName) {
            PatientName = patientName;
        }

        public String getHospitalCard() {
            return HospitalCard;
        }

        public void setHospitalCard(String hospitalCard) {
            HospitalCard = hospitalCard;
        }

        public String getPatientQueueUpNum() {
            return PatientQueueUpNum;
        }

        public void setPatientQueueUpNum(String patientQueueUpNum) {
            PatientQueueUpNum = patientQueueUpNum;
        }
    }
}
