package com.chinasofti.postbar.dto;

import java.io.Serializable;

public class CommentDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cmUUID;
	private String postUUID;
	private String userUUID;
	private String cmText;
	private String cmAudio;
	private String cmTime;
	private String cmPrNum;
	private String regTime;
	private String userName;
	private String regPhoto;
	private String postTitle;
	public String getCmUUID() {
		return cmUUID;
	}
	public void setCmUUID(String cmUUID) {
		this.cmUUID = cmUUID;
	}
	public String getPostUUID() {
		return postUUID;
	}
	public void setPostUUID(String postUUID) {
		this.postUUID = postUUID;
	}
	public String getUserUUID() {
		return userUUID;
	}
	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	public String getCmText() {
		return cmText;
	}
	public void setCmText(String cmText) {
		this.cmText = cmText;
	}
	public String getCmAudio() {
		return cmAudio;
	}
	public void setCmAudio(String cmAudio) {
		this.cmAudio = cmAudio;
	}
	public String getCmTime() {
		return cmTime;
	}
	public void setCmTime(String cmTime) {
		this.cmTime = cmTime;
	}
	public String getCmPrNum() {
		return cmPrNum;
	}
	public void setCmPrNum(String cmPrNum) {
		this.cmPrNum = cmPrNum;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRegPhoto() {
		return regPhoto;
	}
	public void setRegPhoto(String regPhoto) {
		this.regPhoto = regPhoto;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
}
