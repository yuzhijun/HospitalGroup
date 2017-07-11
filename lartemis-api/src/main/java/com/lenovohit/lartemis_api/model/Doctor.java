package com.lenovohit.lartemis_api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Doctor implements Parcelable{
	private String DoctorName;
	private String HospitalName;
	private String DepName;
	private String DepID;
	private String JobName;
	private String PhotoUrl;
	private String Info;
	private String Expert;
	private String DoctorID;
	private String DoctorCode;
	private String IsCollection;
	private String Focus;
	private String ClickNum;
	private String HID;
	private String HGID;
	private String Mode;
	private String DepCode;
	private String Sex;
	private String PhoneNum;
	private String IsOnline;
	private String OnlineMoney;
	private String IsAppointment;
	private String IsRegister;
	private String AppointmentNum;

	public String getAppointmentNum() {
		return AppointmentNum;
	}

	public void setAppointmentNum(String appointmentNum) {
		AppointmentNum = appointmentNum;
	}

	public String getHGID() {
		return HGID;
	}

	public void setHGID(String HGID) {
		this.HGID = HGID;
	}
	public String getHID() {
		return HID;
	}

	public void setHID(String HID) {
		this.HID = HID;
	}

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public String getDepCode() {
		return DepCode;
	}

	public void setDepCode(String depCode) {
		DepCode = depCode;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getIsOnline() {
		return IsOnline;
	}

	public void setIsOnline(String isOnline) {
		IsOnline = isOnline;
	}

	public String getOnlineMoney() {
		return OnlineMoney;
	}

	public void setOnlineMoney(String onlineMoney) {
		OnlineMoney = onlineMoney;
	}

	public String getDoctorName() {
		return DoctorName;
	}

	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

	public String getDepName() {
		return DepName;
	}

	public void setDepName(String depName) {
		DepName = depName;
	}

	public String getDepID() {
		return DepID;
	}

	public void setDepID(String depID) {
		DepID = depID;
	}

	public String getJobName() {
		return JobName;
	}

	public void setJobName(String jobName) {
		JobName = jobName;
	}

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}

	public String getInfo() {
		return Info;
	}

	public void setInfo(String info) {
		Info = info;
	}

	public String getExpert() {
		return Expert;
	}

	public void setExpert(String expert) {
		Expert = expert;
	}

	public String getDoctorID() {
		return DoctorID;
	}

	public void setDoctorID(String doctorID) {
		DoctorID = doctorID;
	}

	public String getDoctorCode() {
		return DoctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		DoctorCode = doctorCode;
	}

	public String getIsCollection() {
		return IsCollection;
	}

	public void setIsCollection(String isCollection) {
		IsCollection = isCollection;
	}

	public String getFocus() {
		return Focus;
	}

	public void setFocus(String focus) {
		Focus = focus;
	}

	public String getClickNum() {
		return ClickNum;
	}

	public void setClickNum(String clickNum) {
		ClickNum = clickNum;
	}

	public String getIsAppointment() {
		return IsAppointment;
	}

	public void setIsAppointment(String isAppointment) {
		IsAppointment = isAppointment;
	}

	public String getIsRegister() {
		return IsRegister;
	}

	public void setIsRegister(String isRegister) {
		IsRegister = isRegister;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.DoctorName);
		dest.writeString(this.HospitalName);
		dest.writeString(this.DepName);
		dest.writeString(this.DepID);
		dest.writeString(this.JobName);
		dest.writeString(this.PhotoUrl);
		dest.writeString(this.Info);
		dest.writeString(this.Expert);
		dest.writeString(this.DoctorID);
		dest.writeString(this.DoctorCode);
		dest.writeString(this.IsCollection);
		dest.writeString(this.Focus);
		dest.writeString(this.ClickNum);
		dest.writeString(this.HID);
		dest.writeString(this.HGID);
		dest.writeString(this.Mode);
		dest.writeString(this.DepCode);
		dest.writeString(this.Sex);
		dest.writeString(this.PhoneNum);
		dest.writeString(this.IsOnline);
		dest.writeString(this.OnlineMoney);
		dest.writeString(this.IsAppointment);
		dest.writeString(this.IsRegister);
		dest.writeString(this.AppointmentNum);
	}

	public Doctor() {
	}

	protected Doctor(Parcel in) {
		this.DoctorName = in.readString();
		this.HospitalName = in.readString();
		this.DepName = in.readString();
		this.DepID = in.readString();
		this.JobName = in.readString();
		this.PhotoUrl = in.readString();
		this.Info = in.readString();
		this.Expert = in.readString();
		this.DoctorID = in.readString();
		this.DoctorCode = in.readString();
		this.IsCollection = in.readString();
		this.Focus = in.readString();
		this.ClickNum = in.readString();
		this.HID = in.readString();
		this.HGID = in.readString();
		this.Mode = in.readString();
		this.DepCode = in.readString();
		this.Sex = in.readString();
		this.PhoneNum = in.readString();
		this.IsOnline = in.readString();
		this.OnlineMoney = in.readString();
		this.IsAppointment = in.readString();
		this.IsRegister = in.readString();
		this.AppointmentNum = in.readString();
	}

	public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
		@Override
		public Doctor createFromParcel(Parcel source) {
			return new Doctor(source);
		}

		@Override
		public Doctor[] newArray(int size) {
			return new Doctor[size];
		}
	};
}
