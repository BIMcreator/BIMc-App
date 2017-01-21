package com.howareyoudoing.data.model.response;


import com.google.gson.annotations.SerializedName;
import com.howareyoudoing.data.model.content.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsResponse  {

	@SerializedName("results")
	private List<Review> mReviews;

	public List<Review> getReviews() {
		if (mReviews == null) {
			mReviews = new ArrayList<>();
		}
		return mReviews;
	}
}
