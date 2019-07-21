package com.learn.reactive.chap3.playground;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;

public class LearnZip {

    Observable<Integer> odds = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            emitter.onNext(1);
            emitter.onNext(3);
//            emitter.onNext(5);
            emitter.onError(new NullPointerException());
        }
    }).onErrorReturnItem(5);
    Observable<Integer> evens = Observable.just(2, 4, 6);

    Observable<String> texts = Observable.just("A", "B", "C");

    private void zip(){
        Observable.zip(odds, evens, texts, new Function3<Integer, Integer, String, Object>() {
            @Override
            public Object apply(Integer integer, Integer integer2, String s) throws Exception {
                System.out.println("apply");
                System.out.println(integer);
                System.out.println(integer2);
                System.out.println(s);
                return new Object();
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext");
                System.out.println(o.toString());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
