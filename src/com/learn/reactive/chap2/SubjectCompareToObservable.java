package com.learn.reactive.chap2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.PublishSubject;

public class SubjectCompareToObservable {

    public static void main(String[] args) {
        publishSubject();
    }

    private static void normalObservable(){
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("One");
                emitter.onNext("Two");
            }
        });
        observable.subscribe(string -> System.out.println(string));
        observable.subscribe(string -> System.out.println(string));
    }

    private static void publishSubject(){
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(string -> System.out.println(string));
        publishSubject.onNext("one");
        publishSubject.onNext("two");
        publishSubject.subscribe(string -> System.out.println(string));
        publishSubject.onNext("three");
    }
}
