package com.howareyoudoing.bimc.screen.users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.howareyoudoing.bimc.R;
import com.howareyoudoing.bimc.domain.model.User;
import com.howareyoudoing.bimc.domain.usecase.UserUseCase;
import com.howareyoudoing.bimc.screen.details.BimcDetailsActivity;
import com.howareyoudoing.bimc.screen.general.LoadingDialog;
import com.howareyoudoing.bimc.screen.general.LoadingView;
import com.howareyoudoing.data.repository.RepositoryProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.arturvasilov.rxloader.RxUtils;


public class UsersActivity extends AppCompatActivity implements UsersView, UsersAdapter.OnItemClickListener {

	@BindView(R.id.toolbar)
	Toolbar mToolbar;

	@BindView(R.id.recyclerView)
	RecyclerView mUsersRecycler;

	@BindView(R.id.empty)
	View mEmptyView;

	private UsersAdapter mAdapter;

	private LoadingView mLoadingView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);
		ButterKnife.bind(this);
		setSupportActionBar(mToolbar);

		int columns = getResources().getInteger(R.integer.columns_count);
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
		mUsersRecycler.setLayoutManager(layoutManager);
		mAdapter = createAdapter();
		mUsersRecycler.setAdapter(mAdapter);

		mLoadingView = LoadingDialog.view(getSupportFragmentManager());

		UserUseCase userUseCase = new UserUseCase(RepositoryProvider.getUsersRepository(), RxUtils.async());
		LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
		UsersPresenter presenter = new UsersPresenter(this, userUseCase, lifecycleHandler);
		presenter.init();
	}


	@Override
	public void showLoadingIndicator() {
		mLoadingView.showLoadingIndicator();
	}

	@Override
	public void hideLoadingIndicator() {
		mLoadingView.hideLoadingIndicator();
	}

	@Override
	public void showUsers(@NonNull List<User> users) {
		mAdapter.changeDataSet(users);
		mUsersRecycler.setVisibility(View.VISIBLE);
		mEmptyView.setVisibility(View.GONE);
	}

	@Override
	public void showError() {
		mUsersRecycler.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onItemClick(@NonNull View view, @NonNull User user) {
		ImageView imageView = ButterKnife.findById(view, R.id.image);
		BimcDetailsActivity.navigate(this, imageView, user);
	}

	@NonNull
	private UsersAdapter createAdapter() {
		TypedValue typedValue = new TypedValue();
		getResources().getValue(R.dimen.rows_count, typedValue, true);
		float rowsCount = typedValue.getFloat();
		int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
				? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
				: 0;
		int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

		int columns = getResources().getInteger(R.integer.columns_count);
		int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

		return new UsersAdapter(imageHeight, imageWidth, this);
	}
}
