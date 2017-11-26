package com.learn.playground.multithreading;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MultiThreading {

    public static void main(String[] args) {
        new MultiThreading().subscribeOnDifferentThread();
    }

    private void defaultThread(){
        Observable<String> observable = getObservable();
        observable.subscribe(result());
    }

    private Observable<String> getObservable() {
        return Observable
                    .fromCallable(numberOne())
                    .doOnNext(doOnNext())
                    .map(numberToString());
    }

    private void subscribeOnThread(){
        ExecutorService executorService =
                Executors.newSingleThreadExecutor();
        executorService.execute(() -> getObservable().subscribe(result()));
        executorService.shutdown();
    }

    private void subscribeOnDifferentThread(){
        Observable<String> observable = getObservable();
        observable
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
                .subscribe(result())
        ;
    }

    private void observerOnDifferentThread(){
        Observable
                .fromCallable(numberOne())
                .doOnNext(doOnNext())
                .subscribeOn(Schedulers.from(Executors.newSingleThreadExecutor()))
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
            System.out.println("Result Thread:" + Thread.currentThread().getName());
        };
    }

    private static class ThreadNameThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            return null;
        }
    }
}
