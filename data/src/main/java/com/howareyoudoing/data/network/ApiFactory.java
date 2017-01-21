package com.howareyoudoing.data.network;


import com.howareyoudoing.data.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiFactory {


	private static OkHttpClient sClient;

	private static UserService sService;

	public static UserService getUsersService() {
		//I know that double checked locking is not a good pattern, but it's enough here
		UserService service = sService;
		if (service == null) {
			synchronized (ApiFactory.class) {
				service = sService;
				if (service == null) {
					service = sService = createService();
				}
			}
		}
		return service;
	}

	//создаем сервис и добавляем адаптер для ретрофита
	private static UserService createService() {
		return new Retrofit.Builder()
				.baseUrl(BuildConfig.API_ENDPOINT)
				.client(getClient())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build()
				.create(UserService.class);
	}

	private static OkHttpClient getClient() {
		OkHttpClient client = sClient;
		if (client == null) {
			synchronized (ApiFactory.class) {
				client = sClient;
				if (client == null) {
					client = sClient = buildClient();
				}
			}
		}
		return client;
	}

	private static OkHttpClient buildClient() {
		return new OkHttpClient.Builder()
				.addInterceptor(new ApiKeyInterceptor())
				.build();
	}
}
