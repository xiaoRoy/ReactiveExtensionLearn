package com.learn.reactive.chap2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class MasteringObservableCreate {


    public static void main(String[] args) {
        multipleSubscription();
    }

    private static void log(Object object){
        System.out.println(Thread.currentThread().getName() + ":" + object);
    }

    private void create(){
        log("before");
        Observable.range(5,3).subscribe(integer -> {log(integer);});
        log("after");
    }

    private static void multipleSubscription(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                log("create");
                emitter.onNext(12);
                emitter.onComplete();
            }
        }).cache();
        log("Starting");
        observable.subscribe(integer -> {log("ElementA:" + integer);});
        observable.subscribe(integer -> {log("ElementB:" + integer);});
        log("exit");
    }
}
