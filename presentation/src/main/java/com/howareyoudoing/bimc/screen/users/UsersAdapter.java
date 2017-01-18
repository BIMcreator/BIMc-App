package com.howareyoudoing.bimc.screen.users;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.howareyoudoing.bimc.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserHolder>{

	private final List<User> mUsers;
	private final int mImageHeight;
	private final int mImageWidth;

	private final OnItemClickListener mOnItemClickListener;

	private final View.OnClickListener mInternalListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			User user = (User) view.getTag();
			mOnItemClickListener.onItemClick(view, user);
		}
	};

	public MoviesAdapter(int imageHeight, int imageWidth, @NonNull OnItemClickListener onItemClickListener) {
		mUsers = new ArrayList<>();
		mImageHeight = imageHeight;
		mImageWidth = imageWidth;
		mOnItemClickListener = onItemClickListener;
	}

	public void changeDataSet(@NonNull List<User> movies) {
		mUsers.clear();
		mUsers.addAll(movies);
		notifyDataSetChanged();
	}


	@Override
	public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(UserHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}
}
