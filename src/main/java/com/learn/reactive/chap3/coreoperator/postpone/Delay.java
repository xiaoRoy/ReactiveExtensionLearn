package com.learn.reactive.chap3.coreoperator.postpone;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import org.omg.CORBA.TIMEOUT;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static io.reactivex.Observable.timer;

public class Delay {

    private static final String[] WORD_ARRAY = {"apple", "bear", "cat", "docker", "english"};

    public static void main(String[] args) {
        new Delay().timerFlatMap();
    }

    private void delay() {
        Observable
                .just("apple", "bear", "cat", "docker", "english")
                .delay(word -> timer(word.length(), TimeUnit.SECONDS))
                .subscribe(word -> System.out.println(word));
        sleep();
    }

    private void delayFlatMap() {
        Observable
                .just("apple", "bear", "cat", "docker", "english")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String word) throws Exception {
                        return timer(word.length(), TimeUnit.SECONDS).map(zero -> word);
                    }
                })
//                .flatMap(word -> timer(word.length(), TimeUnit.SECONDS).map(zero -> word))
                .subscribe(System.out::println);
        sleep();
    }
    

    private void normalDelay() {
        Observable
                .just("a", "b", "c")
                .delay(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);
    }

    private void timerFlatMap() {
        Observable
                .timer(1, TimeUnit.SECONDS)
                .flatMap(zero -> Observable.just("a", "b", "c"))
                .subscribe(System.out::println);
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(20L);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private Observable<Long> timerCustom(String word) {
        long length = word.length();
        return Observable.just(length);
    }
}
