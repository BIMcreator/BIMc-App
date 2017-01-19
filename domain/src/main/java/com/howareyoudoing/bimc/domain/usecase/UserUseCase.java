package com.howareyoudoing.bimc.domain.usecase;


import com.howareyoudoing.bimc.domain.UsersRepository;
import com.howareyoudoing.bimc.domain.model.User;

import java.util.List;

import rx.Observable;

public class UserUseCase {

	private final UsersRepository mRepository;
	private final Observable.Transformer<List<User>, List<User>> mAsyncTransformer;

	public UserUseCase(UsersRepository repository,
	                     Observable.Transformer<List<User>, List<User>> asyncTransformer) {
		mRepository = repository;
		mAsyncTransformer = asyncTransformer;
	}

	public Observable<List<User>> popularUsers() {
		return mRepository.popularUsers()
				.compose(mAsyncTransformer);
	}


}
