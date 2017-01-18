package com.howareyoudoing.bimc.domain;


import java.util.List;

import rx.Observable;

public interface UsersRepository {


	Observable<List<com.howareyoudoing.bimc.domain.model.User>> popularMovies();

}
