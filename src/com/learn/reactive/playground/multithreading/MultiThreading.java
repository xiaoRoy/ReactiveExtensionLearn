package com.learn.reactive.playground.multithreading;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.concurrent.Callable;

public class MultiThreading {

    public static void main(String[] args) {
        new MultiThreading().defaultThread();
    }

    private void defaultThread(){
        Observable
                .fromCallable(numberOne())
                .doOnNext(doOnNext())
                .map(numberToString())
                .subscribe(result());

    }

    private Callable<Integer> numberOne(){
        return () -> {
            System.out.println("Observable Thread:" + Thread.currentThread().getName());
            return 1;
        };
    }

    private Function<Integer, String> numberToString(){
        return number -> {
            System.out.println("Operator Thread:" + Thread.currentThread().getName());
            return String.valueOf(number);
        };
    }

    private Consumer<Integer> doOnNext(){
        return integer -> {
            System.out.println("DoOnNext Thread:" + Thread.currentThread().getName());};
    }

    private Consumer<String> result(){
        return result ->{
            System.out.println("Subscriber Thread:" + Thread.currentThread().getName());
        };
    }
}
