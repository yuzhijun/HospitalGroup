package com.lenovohit.lartemis_api.data;


import com.lenovohit.lartemis_api.model.Doctor;

import java.util.List;

/**
 * Created by yuzhijun on 2016/7/21.
 */
public class DoctorData {

    private static Doctor doctor;
    private static List<Doctor> doctors;

    public static List<Doctor> getDoctors() {
        return doctors;
    }

    public static void setDoctors(List<Doctor> doctors) {
        DoctorData.doctors = doctors;
    }

    public static Doctor getDoctor() {
        return doctor;
    }

    public static void setDoctor(Doctor doctor) {
        DoctorData.doctor = doctor;
    }
}
