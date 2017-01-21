package com.howareyoudoing.data.repository;

/**
 * Created by davey on 31.05.2017.
 */

import com.howareyoudoing.bimc.domain.UsersRepository;
import com.howareyoudoing.bimc.domain.model.User;
import com.howareyoudoing.data.cache.UsersCacheTransformer;
import com.howareyoudoing.data.mapper.UsersMapper;
import com.howareyoudoing.data.model.response.UsersResponse;
import com.howareyoudoing.data.network.ApiFactory;

import java.util.List;

import rx.Observable;

/**
 * мы можем асинхронно выполнять запросы, обрабатывать и получать данные, а также обрабатывать ошибки
 * Создаем интерфейс для репозитория в слое бизнес-логики (для взаимодействия через интерфейсы) и реализуем его в слое данных:
 */
public class UsersDataRepository implements UsersRepository {

	@Override
	public Observable<List<User>> popularUsers() {

		/**
		 * Добавим также показ прогресс бара при старте загрузки и его скрытие после окончания запроса:
		 */
        /*ApiFactory.getMoviesService()
                .popularUsers()
                .map(MoviesResponse::getUsers)
                .doOnSubscribe(mLoadingView::showLoadingIndicator)
                .doAfterTerminate(mLoadingView::hideLoadingIndicator)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showMovies, throwable -> showError());*/

		/**
		 * кэширование данных. Это очень легко исправить – преобразуем Observable так, чтобы он сохранял список элементов:
		 */
/*
        ApiFactory.getMoviesService()
                .popularUsers()
                .map(MoviesResponse::getUsers)
                .flatMap(movies -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Movie.class);
                        realm.insert(movies);
                    });
                    return Observable.just(movies);
                })
*/

		/**
		 * Есть и еще возможность улучшить код с использованием RxJava и показать способ обработки ошибок.
		 * Предположим, что мы не смогли получить данные с сервера, но у нас есть закэшированные данные.
		 * Тогда в случае ошибки мы можем отобразить их. Это сделать с помощью метода onErrorResumeNext.
		 * Этот метод в случае возникновения ошибки в исходном потоке данных меняет его на другой поток данных,
		 * который передается в параметре. В нашем случае это будет поток данных, основанный на закэшированных элементах:
		 */

       /* .onErrorResumeNext(throwable -> {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<Movie> results = realm.where(Movie.class).findAll();
            return Observable.just(realm.copyFromRealm(results));
        })*/


		return ApiFactory.getUsersService()
				.popularUsers()
				.map(UsersResponse::getUsers)
				.compose(new UsersCacheTransformer())
				.flatMap(Observable::from)
				.map(new UsersMapper())
				.toList();
	}
}