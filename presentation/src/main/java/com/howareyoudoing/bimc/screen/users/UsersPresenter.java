package com.howareyoudoing.bimc.screen.users;


import android.support.annotation.NonNull;

import com.howareyoudoing.bimc.R;
import com.howareyoudoing.bimc.domain.usecase.UserUseCase;

import ru.arturvasilov.rxloader.LifecycleHandler;

/**
 * Когда мы видим код, написанный в рамках одного экрана (Activity / Fragment), мы можем легко
 * сказать, какой код отвечает за непосредственное отображение данных пользователю, а какой код
 * управляет логикой этого отображения. В этом и заключается суть паттерна MVP. Основным объектом
 * является Presenter, который управляет View. Например, вот код Presenter-а, который хорошо
 * иллюстрирует разделение функций:
 */

public class UsersPresenter {

	private final UsersView mUsersView;
	private final UserUseCase mUserUseCase;
	private final LifecycleHandler mLifecycleHandler;

	/**
	 * Еще один важный вопрос заключается в обработке жизненного цикла экрана. Это относится к
	 * логике экрана, поэтому такой обработкой должен заниматься Presenter. Есть несколько вариантов
	 * того, как он может это делать. Первый – определить в Presenter методы жизненного цикла
	 * Activity / Fragment и вызывать их. Второй – передать специальный делегат, который будет
	 * заниматься обработкой жизненного цикла, как и сделано в примере:
	 *
	 */

	public UsersPresenter(@NonNull UsersView usersView, @NonNull UserUseCase userUseCase,
	                       @NonNull LifecycleHandler lifecycleHandler) {
		mUsersView = usersView;
		mUserUseCase = userUseCase;
		/**
		 * Экземпляр LifecycleHandler, который передается в Presenter, является интерфейсом и позволяет
		 * обрабатывать события жизненного цикла и корректно сохранять состояния запроса.
		 * Второй вариант предпочтительнее, так как он упрощает тестирование и позволяет изменять
		 * способ обработки жизненного цикла без изменения самих классов.
		 */
		mLifecycleHandler = lifecycleHandler;
	}

	/**
	 * Когда экран запускается, у Presenter-а вызывается метод init. В этом методе Presenter
	 * инициирует загрузку данных и во время всего этого процесса управляет экземпляром View:
	 * показывает и скрывает прогресс, отображает полученные данные и ошибки. При этом View не
	 * знает, когда и в зависимости от чего ей нужно выполнить ту или иную операцию с UI. Вместо
	 * этого она предоставляет интерфейс Presenter-у, который с помощью него и управляет UI-частью
	 * приложения.
	 */

	public void init() {
		mUserUseCase.popularUsers()
				.doOnSubscribe(mUsersView::showLoadingIndicator)
				.doAfterTerminate(mUsersView::hideLoadingIndicator)
				//
				/**
				 * если метод cache() - то запрос выполнится только один раз. При повторной
				 * подписке будет возвращен старый результат. Но при этом нужно каким-то образом
				 * сохранять Observable (к примеру, в лоадере)
				 */
				//.cache()
				.compose(mLifecycleHandler.load(R.id.users_request_id))
				.subscribe(mUsersView::showUsers, throwable -> mUsersView.showError());
	}





}
