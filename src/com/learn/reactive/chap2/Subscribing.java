package com.learn.reactive.chap2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class Subscribing {

    private void subscribe(){
        Observable<Tweet> tweetObservable = Observable.create(new ObservableOnSubscribe<Tweet>() {
            @Override
            public void subscribe(ObservableEmitter<Tweet> e) throws Exception {

            }
        });

        tweetObservable.subscribe(new Consumer<Tweet>() {
            @Override
            public void accept(Tweet tweet) throws Exception {
                System.out.println(tweet);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
    }

    private void subscribeWrapUp(){
        Observable<Tweet> tweetObservable = Observable.create(new ObservableOnSubscribe<Tweet>() {
            @Override
            public void subscribe(ObservableEmitter<Tweet> e) throws Exception {

            }
        });

         tweetObservable.subscribe(new Observer<Tweet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Tweet tweet) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void unSubscribe(){
        Observable<Tweet> tweetObservable = Observable.create(new ObservableOnSubscribe<Tweet>() {
            @Override
            public void subscribe(ObservableEmitter<Tweet> e) throws Exception {

            }
        });
        Disposable disposable = tweetObservable.subscribe();
        disposable.dispose();
    }

    private void unSubscribeWithSubscriber(){
        Observable<Tweet> tweetObservable = Observable.create(new ObservableOnSubscribe<Tweet>() {
            @Override
            public void subscribe(ObservableEmitter<Tweet> e) throws Exception {

            }
        });

    }
}
