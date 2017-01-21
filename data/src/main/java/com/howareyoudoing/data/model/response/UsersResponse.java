package com.howareyoudoing.data.model.response;

import com.google.gson.annotations.SerializedName;
import com.howareyoudoing.data.model.content.User;

import java.util.ArrayList;
import java.util.List;


public class UsersResponse {

	@SerializedName("results")
	private List<User> mUsers;

	public List<User> getUsers() {
		if (mUsers == null) {
			return new ArrayList<>();
		}
		return mUsers;
	}

}
