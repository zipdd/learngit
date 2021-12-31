package com.chinasofti.postbar.dto;

import java.io.Serializable;

public class AppDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String appID;
	private String apiKey;
	private String secretKey;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
