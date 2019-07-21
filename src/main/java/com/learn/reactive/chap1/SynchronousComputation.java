package com.learn.reactive.chap1;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronousComputation {

    public static void main(String[] args) {

    }

    private static void syncEmit() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
                observableEmitter.onComplete();
            }
        });

        observable.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                System.out.println("map.thread:" + Thread.currentThread());
                return "number:" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String result) throws Exception {
                System.out.println("accept:" + Thread.currentThread());
                System.out.println(result);
            }
        });
    }


    private static void asyncEmit() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                Executors.newFixedThreadPool(1).submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                            observableEmitter.onNext(1);
                            observableEmitter.onNext(2);
                            observableEmitter.onNext(3);
                            observableEmitter.onComplete();
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            }
        });

//        observable.doOnNext()
    }
}
