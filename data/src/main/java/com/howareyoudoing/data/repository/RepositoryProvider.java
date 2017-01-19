package com.howareyoudoing.data.repository;


import com.howareyoudoing.bimc.domain.UsersRepository;

public class RepositoryProvider {

	private static UsersRepository sUsersRepository;

	public static UsersRepository getUsersRepository(){
		if (sUsersRepository == null) {
			sUsersRepository = new UsersDataRepository();
		}
		return sUsersRepository;
	}


	public static void setUsersRepository(UsersRepository usersRepository) {
		sUsersRepository = usersRepository;
	}


}
