package com.chinasofti.postbar.dto;

import java.io.Serializable;

public class CommentPraiseDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cmPrUUID;
	private String postUUID;
	private String userUUID;
	private String cmUUID;
	public String getCmPrUUID() {
		return cmPrUUID;
	}
	public void setCmPrUUID(String cmPrUUID) {
		this.cmPrUUID = cmPrUUID;
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
	public String getCmUUID() {
		return cmUUID;
	}
	public void setCmUUID(String cmUUID) {
		this.cmUUID = cmUUID;
	}
}
