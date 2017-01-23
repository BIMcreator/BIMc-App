package com.howareyoudoing.data.model.content;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

	@PrimaryKey
	@SerializedName("userSettingsId")
	private int mUserId;

	@SerializedName("userType")
	private String mUserType;

	@SerializedName("username")
	private String mUsername;

	public User() {
	}

	public User(int userId, String userType, String username) {
		mUserId = userId;
		mUserType = userType;
		mUsername = username;
	}

	public User(Parcel in) {
		mUserId = in.readInt();
		mUserType = in.readString();
		mUsername = in.readString();
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