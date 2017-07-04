package com.lenovohit.lartemis_api.model;

public class Result {
	private String PhotoUrl;
	private int State;
	private String Msg;

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}
}
