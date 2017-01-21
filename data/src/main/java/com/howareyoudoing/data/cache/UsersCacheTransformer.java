package com.howareyoudoing.data.cache;



import com.howareyoudoing.data.model.content.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

public class UsersCacheTransformer implements Observable.Transformer<List<User>, List<User>> {

	/**
	 * Поля mSaveFunc и mCacheErrorHandler определяются в этом же классе, это обычные функции
	 * преобразования, которые для удобства были вынесены в отдельные поля:
	 */

	private final Func1<List<User>, Observable<List<User>>> mSaveFunc = movies -> {
		Realm.getDefaultInstance().executeTransaction(realm -> {
			realm.delete(User.class);
			realm.insert(movies);
		});
		return Observable.just(movies);
	};

	private final Func1<Throwable, Observable<List<User>>> mCacheErrorHandler = throwable -> {
		Realm realm = Realm.getDefaultInstance();
		RealmResults<User> results = realm.where(User.class).findAll();
		return Observable.just(realm.copyFromRealm(results));
	};

	/**
	 *  Здесь мы вначале получаем данные через сервис Retrofit, после добавляем обработку кэширования
	 *  (сохранения и восстановления в случае ошибки) следующим образом:
	 */
	@Override
	public Observable<List<User>> call(Observable<List<User>> moviesObservable) {
		return moviesObservable
				.flatMap(mSaveFunc)
				.onErrorResumeNext(mCacheErrorHandler);
	}
}