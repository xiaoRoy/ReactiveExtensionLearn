package com.learn.playground.observableshowcase;


import io.reactivex.*;
import io.reactivex.disposables.Disposable;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;

public class LearnObservable {

    public static void main(String[] args) {
        new LearnObservable().maybe();
    }

    private void observable(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("first");
                emitter.onNext("second");
                if(randomResult()){
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception());
                }
            }
        })
        .subscribe(observe());
    }

    private void flowable(){
    }

    private void single(){
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
               if(randomResult()){
                   emitter.onSuccess("only one");
               } else {
                   emitter.onError(new Exception());
               }
            }
        })
        .subscribe(observeSingle());
    }

    private void completable(){
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                if(randomResult()){
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception());
                }
            }
        })
        .subscribe(observeCompletable());
    }

    private void maybe(){
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onSuccess("only one");
                if(randomResult()){
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception());
                }
            }
        }).subscribe(observeMaybe());
    }

    private boolean randomResult() {
        return new Random(new Date().getTime()).nextBoolean();
    }

    private MaybeObserver<String> observeMaybe(){
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("trail.onSubscribe");
            }

            @Override
            public void onSuccess(String text) {
                System.out.println("trail.onSuccess");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("trail.onError");
            }

            @Override
            public void onComplete() {
                System.out.println("trail.onComplete");
            }
        };
    }

    private CompletableObserver observeCompletable(){
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("trail.onSubscribe");
            }

            @Override
            public void onComplete() {
                System.out.println("trail.onComplete");
            }

            @Override
            public void onError(Throwable throwable) {

            }
        };
    }

    private SingleObserver<String> observeSingle(){
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("trail.onSubscribe");
            }

            @Override
            public void onSuccess(String text) {
                System.out.println("trail.onSuccess");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("trail.onError");
            }
        };
    }

    private Observer<String> observe(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("trail.onSubscribe");
            }

            @Override
            public void onNext(String text) {
                System.out.println("trail.onNext");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("trail.onError");
            }

            @Override
            public void onComplete() {
                System.out.println("trail.onComplete");
            }
        };
    }

}
