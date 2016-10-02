package com.howareyoudoing.bimc.screen.details;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.howareyoudoing.bimc.R;

import butterknife.BindView;

/**
 * Created by davey on 29.05.2017.
 */

public class BimcDetailsActivity extends AppCompatActivity {


	private static final String MAXIMUM_RATING = "10";

	public static final String IMAGE = "image";
	public static final String EXTRA_MOVIE = "extraMovie";

	@BindView(R.id.toolbar)
	Toolbar mToolbar;

	@BindView(R.id.toolbar_layout)
	CollapsingToolbarLayout mCollapsingToolbar;

	@BindView(R.id.image)
	ImageView mImage;

	@BindView(R.id.title)
	TextView mTitleTextView;

	@BindView(R.id.overview)
	TextView mOverviewTextView;

	@BindView(R.id.rating)
	TextView mRatingTextView;

	public static void navigate(@NonNull AppCompatActivity activity, @NonNull View transitionImage,
	                            @NonNull Movie movie) {
		Intent intent = new Intent(activity, MovieDetailsActivity.class);
		intent.putExtra(EXTRA_MOVIE, movie);

		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, IMAGE);
		ActivityCompat.startActivity(activity, intent, options.toBundle());
	}








}
