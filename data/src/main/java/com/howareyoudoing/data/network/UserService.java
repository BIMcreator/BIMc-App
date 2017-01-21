package com.howareyoudoing.data.network;

import com.howareyoudoing.bimc.domain.UsersRepository;
import com.howareyoudoing.data.model.response.UsersResponse;

import retrofit2.http.GET;
import rx.Observable;


public interface UserService {

	@GET("popular/")
	Observable<UsersResponse> popularUsers();

}
