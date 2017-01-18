package com.howareyoudoing.bimc.screen.details;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.howareyoudoing.bimc.R;
import com.howareyoudoing.bimc.domain.model.User;
import com.howareyoudoing.bimc.utils.Images;

import butterknife.BindView;
import butterknife.ButterKnife;


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
	                            @NonNull User user) {
		Intent intent = new Intent(activity, BimcDetailsActivity.class);
		intent.putExtra(EXTRA_MOVIE, user);

		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, IMAGE);
		ActivityCompat.startActivity(activity, intent, options.toBundle());
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prepareWindowForAnimation();
		setContentView(R.layout.activity_user_details);
		ButterKnife.bind(this);
		setSupportActionBar(mToolbar);

		ViewCompat.setTransitionName(findViewById(R.id.app_bar), IMAGE);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		User user = (User) getIntent().getSerializableExtra(EXTRA_MOVIE);
		showMovie(user);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void prepareWindowForAnimation() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Slide transition = new Slide();
			transition.excludeTarget(android.R.id.statusBarBackground, true);
			getWindow().setStatusBarColor(Color.TRANSPARENT);
			getWindow().setEnterTransition(transition);
			getWindow().setReturnTransition(transition);
		}
	}

	private void showMovie(@NonNull User user) {
		String title = getString(R.string.user_details);
		mCollapsingToolbar.setTitle(title);
		mCollapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));

		Images.loadMovie(mImage, user, Images.WIDTH_780);

		String year = user.getReleasedDate().substring(0, 4);
		mTitleTextView.setText(getString(R.string.user_title, user.getTitle(), year));
		mOverviewTextView.setText(user.getOverview());

		String average = String.valueOf(user.getVoteAverage());
		average = average.length() > 3 ? average.substring(0, 3) : average;
		average = average.length() == 3 && average.charAt(2) == '0' ? average.substring(0, 1) : average;
		mRatingTextView.setText(getString(R.string.rating, average, MAXIMUM_RATING));
	}



}
