package com.lenovohit.lartemis_api.model;

/**
 * 我的反馈model
 * 
 * @author yuzhijun
 * @version 创建时间 2015.04.23
 * */
public class MyAdvice {
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

}
