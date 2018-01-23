package com.learn.playground.concatfirst;

import io.reactivex.Observable;

public class LearnUsingCache {

    public static void main(String[] args) {
        Observable.just("a");
        Observable
                .concat(Observable.just(getText()), Observable.just("a"))
                .first("default")
                .subscribe(result -> System.out.println(result));
    }

    private static String getText(){
        return null;
    }
}
