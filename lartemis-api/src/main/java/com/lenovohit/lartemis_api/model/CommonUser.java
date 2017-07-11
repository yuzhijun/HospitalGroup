package com.lenovohit.lartemis_api.model;

import java.io.Serializable;

/**
 * 常用联系人
 * 
 * @author yuzhijun 2015.05.21
 * */
@SuppressWarnings("serial")
public class CommonUser implements Serializable {
	private boolean selected; // 判断是否选中

	private String PID; // 就诊卡id

	private String UID; // 所属用户id

	private String HGID; // 所属医院id

	private String HospitalName; // 所属医院名称

	private String Name; // 真实姓名

	private String Sex; // 性别

	private String IDCard; // 身份证

	private String HospitalCard; // 就诊卡
	private String PhoneNumber; // 医院预留手机号
	private String Address; // 地址
	private String Tag;
	private String SexName;

	public String getSexName() {
		return SexName;
	}

	public void setSexName(String sexName) {
		SexName = sexName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public String getHGID() {
		return HGID;
	}

	public void setHGID(String hgID) {
		HGID = hgID;
	}

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getHospitalCard() {
		return HospitalCard;
	}

	public void setHospitalCard(String hospitalCard) {
		HospitalCard = hospitalCard;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	@Override
	public String toString() {
		return "CommonUser{" +
				"selected=" + selected +
				", PID='" + PID + '\'' +
				", UID='" + UID + '\'' +
				", HGID='" + HGID + '\'' +
				", HospitalName='" + HospitalName + '\'' +
				", Name='" + Name + '\'' +
				", Sex='" + Sex + '\'' +
				", IDCard='" + IDCard + '\'' +
				", HospitalCard='" + HospitalCard + '\'' +
				", PhoneNumber='" + PhoneNumber + '\'' +
				", Address='" + Address + '\'' +
				", Tag='" + Tag + '\'' +
				", SexName='" + SexName + '\'' +
				'}';
	}
}
