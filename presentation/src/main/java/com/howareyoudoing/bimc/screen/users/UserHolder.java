package com.howareyoudoing.bimc.screen.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.howareyoudoing.bimc.R;
import com.howareyoudoing.bimc.domain.model.User;
import com.howareyoudoing.bimc.utils.Images;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserHolder  extends RecyclerView.ViewHolder{

	@BindView(R.id.image)
	ImageView mImageView;

	public UserHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	@NonNull
	public static UserHolder create(@NonNull Context context, int imageHeight, int imageWidth) {
		View view = View.inflate(context, R.layout.image_item, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.image);
		ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
		layoutParams.height = imageHeight;
		layoutParams.width = imageWidth;
		imageView.requestLayout();
		return new UserHolder(view);
	}

	public void bind(@NonNull User user) {
		Images.loadMovie(mImageView, user, Images.WIDTH_185);
	}

}
