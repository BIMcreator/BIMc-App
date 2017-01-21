package com.howareyoudoing.data.model.response;

import com.google.gson.annotations.SerializedName;
import com.howareyoudoing.data.model.content.Video;

import java.util.ArrayList;
import java.util.List;

public class VideosResponse {

	@SerializedName("results")
	private List<Video> mVideos;

	public List<Video> getVideos() {
		if (mVideos == null) {
			return new ArrayList<>();
		}
		return mVideos;
	}
}
