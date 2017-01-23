package com.howareyoudoing.bimc.domain.model;


import java.io.Serializable;

public class User implements Serializable{

	private int mUserId;
	private String mUserType;
	private String mUsername;

	public User() {
	}

	public User(int userId, String userType, String username) {
		mUserId = userId;
		mUserType = userType;
		mUsername = username;
	}

	public int getUserId() {
		return mUserId;
	}

	public String getUserType() {
		return mUserType;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUserId(int userId) {
		mUserId = userId;
	}

	public void setUserType(String userType) {
		mUserType = userType;
	}

	public void setUsername(String username) {
		mUsername = username;
	}
}
