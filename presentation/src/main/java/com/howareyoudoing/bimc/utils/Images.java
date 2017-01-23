package com.howareyoudoing.bimc.utils;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.howareyoudoing.bimc.BimcApp;
import com.howareyoudoing.bimc.BuildConfig;
import com.howareyoudoing.bimc.domain.model.User;
import com.squareup.picasso.Picasso;


public class Images {

	public static final String WIDTH_185 = "w185";
	public static final String WIDTH_780 = "w780";

	private Images() {
	}

	public static void loadMovie(@NonNull ImageView imageView, @NonNull User user,
	                             @NonNull String size) {
		loadMovie(imageView, "http://black-star.ru/newbrand/images/logo-star.jpg", size);
	}

	public static void loadMovie(@NonNull ImageView imageView, @NonNull String posterPath,
	                             @NonNull String size) {
		String url = BuildConfig.IMAGES_BASE_URL + size + posterPath;
		Picasso.with(imageView.getContext())
				.load(url)
				.noFade()
				.into(imageView);
	}

	public static void fetch(@NonNull String posterPath, @NonNull String size) {
		String url = BuildConfig.IMAGES_BASE_URL + size + posterPath;
		Picasso.with(BimcApp.getAppContext())
				.load(url)
				.fetch();
	}


}
