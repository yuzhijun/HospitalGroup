package com.lenovohit.lartemis_api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户类
 * */
public class User {
	private UserBaseInfo UserBaseInfo;
	private List<Hospitals> CollectHospitals = new ArrayList<>();
	private List<Doctor> CollectDoctors = new ArrayList<>();
	private List<CommonUser> CommonUsers;


	public UserBaseInfo getBaseInfo() {
		return UserBaseInfo;
	}

	public void setBaseInfo(UserBaseInfo baseInfo) {
		UserBaseInfo = baseInfo;
	}

	public List<Hospitals> getCollectHospitals() {
		return CollectHospitals;
	}

	public void setCollectHospitals(List<Hospitals> collectHospitals) {
		CollectHospitals = collectHospitals;
	}

	public List<Doctor> getCollectDoctors() {
		return CollectDoctors;
	}

	public void setCollectDoctors(List<Doctor> collectDoctors) {
		CollectDoctors = collectDoctors;
	}

	public List<CommonUser> getCommonUsers() {
		return CommonUsers;
	}

	public void setCommonUsers(List<CommonUser> commonUsers) {
		CommonUsers = commonUsers;
	}

	public class UserBaseInfo {
		private String UID;
		private String NickName;
		private String Email;
		private String Address;
		private String PhotoUrl;
		private String LastLoginTime;
		private String Name;
		private String IDCard;
		private String PhoneNumber;
		private String Sex;
		private String CommonUserID;

		public String getUID() {
			return UID;
		}

		public void setUID(String uID) {
			UID = uID;
		}

		public String getNickName() {
			return NickName;
		}

		public void setNickName(String nickName) {
			NickName = nickName;
		}

		public String getEmail() {
			return Email;
		}

		public void setEmail(String email) {
			Email = email;
		}

		public String getAddress() {
			return Address;
		}

		public void setAddress(String address) {
			Address = address;
		}

		public String getLastLoginTime() {
			return LastLoginTime;
		}

		public void setLastLoginTime(String lastLoginTime) {
			LastLoginTime = lastLoginTime;
		}

		public String getCommonUserID() {
			return CommonUserID;
		}

		public void setCommonUserID(String commonUserID) {
			CommonUserID = commonUserID;
		}

		public String getPhotoUrl() {
			return PhotoUrl;
		}

		public void setPhotoUrl(String photoUrl) {
			PhotoUrl = photoUrl;
		}

		public String getName() {
//			if (ViewUtil.isStrEmpty(Name)) {
//				if (!(BaseActivity.currentActivity instanceof LX_UserInfoActivity)) {
//					ViewUtil.showToast("请先设置用户[姓名]", false);
//					BaseActivity.currentActivity
//							.startCOActivity(LX_UserInfoActivity.class);
//				}
//			}
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getIDCard() {
//			if (ViewUtil.isStrEmpty(IDCard)) {
//				if (!(BaseActivity.currentActivity instanceof LX_UserInfoActivity)) {
//					ViewUtil.showToast("请先设置用户[身份证]", false);
//					BaseActivity.currentActivity
//							.startCOActivity(LX_UserInfoActivity.class);
//				}
//			}
			return IDCard;
		}

		public void setIDCard(String iDCard) {
			IDCard = iDCard;
		}

		public String getPhoneNumber() {
			return PhoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			PhoneNumber = phoneNumber;
		}

		public String getSex() {
			return Sex;
		}

		public void setSex(String sex) {
			Sex = sex;
		}
	}
}
