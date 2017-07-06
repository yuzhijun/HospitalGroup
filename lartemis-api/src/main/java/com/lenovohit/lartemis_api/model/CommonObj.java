package com.lenovohit.lartemis_api.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CommonObj implements Serializable {
	private String IsUrgent;
	private String ID;
	private String Title;
	private List<CommonObj> Childrens;

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

	public String getIsUrgent() {
		return IsUrgent;
	}

	public void setIsUrgent(String isUrgent) {
		IsUrgent = isUrgent;
	}

	public List<CommonObj> getChildrens() {
		return Childrens;
	}

	public void setChildrens(List<CommonObj> childrens) {
		Childrens = childrens;
	}

}
