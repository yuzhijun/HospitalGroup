package com.lenovohit.lartemis_api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 我的反馈model
 * 
 * @author yuzhijun
 * @version 创建时间 2015.04.23
 * */
public class MyAdvice implements Parcelable{
	private String ID;
	private String Content;
	private String Contact;
	private String Email;
	private String CreateTime;
	private String ReplyContent;
	private String ReplyName;
	private String ReplyTime;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getReplyContent() {
		return ReplyContent;
	}

	public void setReplyContent(String replyContent) {
		ReplyContent = replyContent;
	}

	public String getReplyName() {
		return ReplyName;
	}

	public void setReplyName(String replyName) {
		ReplyName = replyName;
	}

	public String getReplyTime() {
		return ReplyTime;
	}

	public void setReplyTime(String replyTime) {
		ReplyTime = replyTime;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.ID);
		dest.writeString(this.Content);
		dest.writeString(this.Contact);
		dest.writeString(this.Email);
		dest.writeString(this.CreateTime);
		dest.writeString(this.ReplyContent);
		dest.writeString(this.ReplyName);
		dest.writeString(this.ReplyTime);
	}

	public MyAdvice() {
	}

	protected MyAdvice(Parcel in) {
		this.ID = in.readString();
		this.Content = in.readString();
		this.Contact = in.readString();
		this.Email = in.readString();
		this.CreateTime = in.readString();
		this.ReplyContent = in.readString();
		this.ReplyName = in.readString();
		this.ReplyTime = in.readString();
	}

	public static final Creator<MyAdvice> CREATOR = new Creator<MyAdvice>() {
		@Override
		public MyAdvice createFromParcel(Parcel source) {
			return new MyAdvice(source);
		}

		@Override
		public MyAdvice[] newArray(int size) {
			return new MyAdvice[size];
		}
	};
}
