package com.howareyoudoing.data.mapper;

import com.howareyoudoing.bimc.domain.model.User;

import rx.functions.Func1;


public class UsersMapper implements Func1< com.howareyoudoing.data.model.content.User, User> {

	@Override
	public User call(com.howareyoudoing.data.model.content.User user) {
		return new User(user.getPosterPath(), user.getOverview(),
				user.getTitle(), user.getReleasedDate(), user.getVoteAverage());
	}
}
