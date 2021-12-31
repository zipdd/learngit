package com.chinasofti.postbar.dto;

import java.io.Serializable;

public class PostPraiseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String poPrUUID;
	private String postUUID;
	private String userUUID;
	
	public String getPoPrUUID() {
		return poPrUUID;
	}
	public void setPoPrUUID(String poPrUUID) {
		this.poPrUUID = poPrUUID;
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

}
