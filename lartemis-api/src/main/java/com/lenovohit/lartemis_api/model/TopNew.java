package com.lenovohit.lartemis_api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TopNew implements Serializable {
	private String ID;
	private String Title;
	private String Auther;
	private String CreateTime;
	private String ImgURL;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getAuther() {
		return Auther;
	}

	public void setAuther(String auther) {
		Auther = auther;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getImgURL() {
		return ImgURL;
	}

	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}

}
