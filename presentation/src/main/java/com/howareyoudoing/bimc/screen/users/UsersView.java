package com.howareyoudoing.bimc.screen.users;

import android.support.annotation.NonNull;

import com.howareyoudoing.bimc.domain.model.User;
import com.howareyoudoing.bimc.screen.general.LoadingView;

import java.util.List;


public interface UsersView extends LoadingView{


	void showUsers(@NonNull List<User> users);

	void showError();

}
