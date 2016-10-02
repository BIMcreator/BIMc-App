package com.howareyoudoing.bimc;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by davey on 29.05.2017.
 */

public class BimcApp extends Application{

	private static BimcApp sInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;

		Picasso picasso = new Picasso.Builder(this)
				.downloader(new OkHttp3Downloader(this))
				.build();
		Picasso.setSingletonInstance(picasso);

		RealmConfiguration configuration = new RealmConfiguration.Builder(this)
				.rxFactory(new RealmObservableFactory())
				.build();
		Realm.setDefaultConfiguration(configuration);
	}

	@NonNull
	public static BimcApp getAppContext() {
		return sInstance;
	}

}
