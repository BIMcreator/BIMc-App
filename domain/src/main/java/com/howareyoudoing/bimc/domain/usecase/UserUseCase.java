package com.howareyoudoing.bimc.domain.usecase;


import com.howareyoudoing.bimc.domain.UsersRepository;
import com.howareyoudoing.bimc.domain.model.User;

import java.util.List;

import rx.Observable;

/**
 * слой бизнес-логики является очень небольшим и содержит только управление потоками:
 * Слой бизнес-логики обращается к слою данных за получением данных с помощью объекта Repository.
 * Если бы в приложении была более сложная бизнес-логика, за нее отвечал бы этот класс. Тестировать
 * его достаточно легко, нам нужно только передать ему такой экземпляр Repository, который нужен для
 * конкретного теста (подмена окружения).
 */

public class UserUseCase {

	private final UsersRepository mRepository;
	private final Observable.Transformer<List<User>, List<User>> mAsyncTransformer;

	public UserUseCase(UsersRepository repository,
	                     Observable.Transformer<List<User>, List<User>> asyncTransformer) {
		mRepository = repository;
		mAsyncTransformer = asyncTransformer;
	}

	public Observable<List<User>> popularUsers() {
		return mRepository.popularUsers()
				.compose(mAsyncTransformer);
	}
}
