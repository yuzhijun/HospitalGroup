package com.lenovohit.hospitals.domain;

import java.io.Serializable;

public class Appoint implements Serializable {
	/**
	 * AID : 1
	 * DoAID : sample string 2
	 * HID : 3
	 * HospitalName : sample string 4
	 * DepName : sample string 5
	 * DoctorName : sample string 6
	 * DoctorJobName : sample string 7
	 * DoctorSex : sample string 8
	 * PID : 9
	 * AppTypeName : sample string 10
	 * Money : 11.0
	 * AppTime : 2016-07-21 13:20:39
	 * Note : sample string 12
	 * DoctorPhoto : sample string 13
	 * PName : sample string 14
	 * State : sample string 15
	 */

	private String AID;
	private String DoAID;
	private String HID;
	private String HospitalName;
	private String DepName;
	private String DoctorName;
	private String DoctorJobName;
	private String DoctorSex;
	private String PID;
	private String AppTypeName;
	private double Money;
	private String AppTime;
	private String Note;
	private String DoctorPhoto;
	private String PName;
	private String State;
	private String IDCard;
	private String PCard;
	private String PhoneNumber;
	private String PPhone;
	private String OrderID;
	private String AppointmentID;
	private String HospitalCard;

	public String getPPhone() {
		return PPhone;
	}

	public void setPPhone(String PPhone) {
		this.PPhone = PPhone;
	}

	public String getHospitalCard() {
		return HospitalCard;
	}

	public void setHospitalCard(String hospitalCard) {
		HospitalCard = hospitalCard;
	}

	public String getAppointmentID() {
		return AppointmentID;
	}

	public void setAppointmentID(String appointmentID) {
		AppointmentID = appointmentID;
	}

	public String getAID() {
		return AID;
	}

	public void setAID(String AID) {
		this.AID = AID;
	}

	public String getDoAID() {
		return DoAID;
	}

	public void setDoAID(String DoAID) {
		this.DoAID = DoAID;
	}

	public String getHID() {
		return HID;
	}

	public void setHID(String HID) {
		this.HID = HID;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String HospitalName) {
		this.HospitalName = HospitalName;
	}

	public String getDepName() {
		return DepName;
	}

	public void setDepName(String DepName) {
		this.DepName = DepName;
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

	public String getDoctorSex() {
		return DoctorSex;
	}

	public void setDoctorSex(String DoctorSex) {
		this.DoctorSex = DoctorSex;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String PID) {
		this.PID = PID;
	}

	public String getAppTypeName() {
		return AppTypeName;
	}

	public void setAppTypeName(String AppTypeName) {
		this.AppTypeName = AppTypeName;
	}

	public double getMoney() {
		return Money;
	}

	public void setMoney(double Money) {
		this.Money = Money;
	}

	public String getAppTime() {
		return AppTime;
	}

	public void setAppTime(String AppTime) {
		this.AppTime = AppTime;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String Note) {
		this.Note = Note;
	}

	public String getDoctorPhoto() {
		return DoctorPhoto;
	}

	public void setDoctorPhoto(String DoctorPhoto) {
		this.DoctorPhoto = DoctorPhoto;
	}

	public String getPName() {
		return PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public String getState() {
		return State;
	}

	public void setState(String State) {
		this.State = State;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String IDCard) {
		this.IDCard = IDCard;
	}

	public String getPCard() {
		return PCard;
	}

	public void setPCard(String PCard) {
		this.PCard = PCard;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
}
