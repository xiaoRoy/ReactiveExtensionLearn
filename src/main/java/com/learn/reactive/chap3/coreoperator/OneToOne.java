package com.learn.reactive.chap3.coreoperator;

import io.reactivex.Observable;

public class OneToOne {

    public static void main(String[] args) {
        new OneToOne().doOnNext();
    }

    private void doOnNext() {
        Observable.just(8, 9, 10)
                .doOnNext(integer -> System.out.println("firstDoOnNext:" + integer))
                .filter(integer -> integer % 3 > 0)
                .doOnNext(integer -> System.out.println("secondDoOnNext:" + integer))
                .map(integer -> "number" + (integer * 10))
                .doOnNext(string -> System.out.println("thirdDoOnNext:" + string))
                .subscribe(result -> System.out.println("result:" + result));
    }

}
