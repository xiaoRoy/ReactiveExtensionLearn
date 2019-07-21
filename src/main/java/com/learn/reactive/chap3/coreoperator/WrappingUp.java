package com.learn.reactive.chap3.coreoperator;

import io.reactivex.*;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.SingleScheduler;

public class WrappingUp {

    private Observable<Integer> observable = Observable.just(1, 3, 5, 7);

    private void fistFlatMap(){
        Observable.just(1, 3, 5).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {

                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                    }
                });
            }
        });
    }

    public static void main(String[] args) {
        WrappingUp wrappingUp = new WrappingUp();
        wrappingUp.diffMap();
        System.out.println("=======");
        wrappingUp.diffFlatMap();
    }

    private void diffMap(){
        observable.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                System.out.println("MapOperator");
                return String.format("#%1$d", integer);
            }
        }).subscribe(string -> System.out.println("result:" + string));
    }

    private void diffFlatMap(){
        observable.flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                System.out.println("FlatMapOperator");
                return Observable.just(String.format("#%1$d", integer));
            }
        }).subscribe(string -> System.out.println("result:" + string));
    }
}
