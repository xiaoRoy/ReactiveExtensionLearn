package com.learn.playground.reactiveapproach;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

public class UserManagerRx {

    private User user = new User();

    public Observable<User> getUser(){
        return Observable.just(user);
    }

    public Completable setName(String name){
        return Completable.complete();
    }
}
