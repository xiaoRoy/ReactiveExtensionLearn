package com.learn.reactive.basic;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;

public class HelloWorld {
    public static void main(String[] args) {
//        Flowable.just("Hello World").subscribe(string -> System.out.println(string));


        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Jack");
                emitter.onNext("Will");
                emitter.onNext("Hector");
                emitter.onComplete();
            }
        });
        observable.subscribe(observer);
//        Observable.just("Captain").subscribe(observer);

    }
}
