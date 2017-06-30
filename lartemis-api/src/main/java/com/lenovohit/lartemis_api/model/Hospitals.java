package com.lenovohit.lartemis_api.model;

/**
 * 医院列表model
 * 
 * @author yuzhijun
 * @version 创建时间 2015.04.17
 * */
public class Hospitals {
	/**
	 * HID : 4
	 * HospitalName : 温附一新院
	 * FullName :
	 * HospitalLevel : 9
	 * HospitalLevelName :
	 * HospitalType : 综合医院
	 * HospitalInfo :
	 * LogoUrl : http://localhost:6906/api/Hospital/LogoPhoto/4
	 * IsCollection : 0
	 * PositionE : 0
	 * PositionN : 0
	 * ProvinceID : 381
	 * ProvinceName :
	 * CityID : 391
	 * CityName :
	 * Focus : 0
	 */
	private String HID;
	private String HospitalName;
	private String FullName;
	private String HospitalLevel;
	private String HospitalLevelName;
	private String HospitalType;
	private String HospitalInfo;
	private String LogoUrl;
	private int IsCollection;
	private String PositionE;
	private String PositionN;
	private String ProvinceID;
	private String ProvinceName;
	private String CityID;
	private String CityName;
	private String Focus;
	private String Distance;
	private String HGID;
	private String AppointmentNum;
	private String Nature;
	private String PublicTransport;

	public String getPublicTransport() {
		return PublicTransport;
	}

	public void setPublicTransport(String publicTransport) {
		PublicTransport = publicTransport;
	}

	public String getNature() {
		return Nature;
	}

	public void setNature(String nature) {
		Nature = nature;
	}

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

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String HospitalName) {
		this.HospitalName = HospitalName;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String FullName) {
		this.FullName = FullName;
	}

	public String getHospitalLevel() {
		return HospitalLevel;
	}

	public void setHospitalLevel(String HospitalLevel) {
		this.HospitalLevel = HospitalLevel;
	}

	public String getHospitalLevelName() {
		return HospitalLevelName;
	}

	public void setHospitalLevelName(String HospitalLevelName) {
		this.HospitalLevelName = HospitalLevelName;
	}

	public String getHospitalType() {
		return HospitalType;
	}

	public void setHospitalType(String HospitalType) {
		this.HospitalType = HospitalType;
	}

	public String getHospitalInfo() {
		return HospitalInfo;
	}

	public void setHospitalInfo(String HospitalInfo) {
		this.HospitalInfo = HospitalInfo;
	}

	public String getLogoUrl() {
		return LogoUrl;
	}

	public void setLogoUrl(String LogoUrl) {
		this.LogoUrl = LogoUrl;
	}

	public int getIsCollection() {
		return IsCollection;
	}

	public void setIsCollection(int IsCollection) {
		this.IsCollection = IsCollection;
	}

	public String getProvinceName() {
		return ProvinceName;
	}

	public void setProvinceName(String ProvinceName) {
		this.ProvinceName = ProvinceName;
	}

	public String getPositionE() {
		return PositionE;
	}

	public void setPositionE(String positionE) {
		PositionE = positionE;
	}

	public String getPositionN() {
		return PositionN;
	}

	public void setPositionN(String positionN) {
		PositionN = positionN;
	}

	public String getProvinceID() {
		return ProvinceID;
	}

	public void setProvinceID(String provinceID) {
		ProvinceID = provinceID;
	}

	public String getCityID() {
		return CityID;
	}

	public void setCityID(String cityID) {
		CityID = cityID;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String CityName) {
		this.CityName = CityName;
	}

	public String getFocus() {
		return Focus;
	}

	public void setFocus(String Focus) {
		this.Focus = Focus;
	}

	public String getDistance() {
		return Distance;
	}

	public void setDistance(String distance) {
		Distance = distance;
	}
}
