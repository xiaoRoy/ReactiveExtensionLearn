package com.learn.reactive.chap2.connectable;

import io.reactivex.Observable;

public class Apple {
    public Apple(Observable<String> observable) {
        observable.subscribe(string -> {
            System.out.println("Apple");
        });
        System.out.println("Apple subscribed");
    }
}
