package com.learn.reactive.chap2.connectable;

import io.reactivex.Observable;

public class Bear {
    public Bear(Observable<String> observable) {
        observable.subscribe(string -> {
            System.out.println("Bear");
        });
        System.out.println("Bear subscribed");
    }
}
