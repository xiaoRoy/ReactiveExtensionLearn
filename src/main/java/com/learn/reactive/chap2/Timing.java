package com.learn.reactive.chap2;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Timing {

    public static void main(String[] args) {
        timer();
//        interval();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private static void timer(){
        Observable.timer(1, TimeUnit.SECONDS).subscribe((Long zero) -> System.out.println(zero));
    }

    private static void interval(){
        Observable.interval(1, TimeUnit.SECONDS).subscribe((Long zero) -> System.out.println(zero));
    }
}
