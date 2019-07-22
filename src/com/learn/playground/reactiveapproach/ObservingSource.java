package com.learn.playground.reactiveapproach;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ObservingSource {
    final private DisposableObserver<String> observer = new DisposableObserver<String>() {
        @Override
        public void onNext(String s) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    private void learnDisposableObservable() {
        Observable
                .just("Hello")
                .subscribe(observer);
        observer.dispose();
    }

    private void learnSubscribeWith() {
        Disposable disposable = Observable
                .just("Hello")
                .subscribeWith(observer);
        disposable.dispose();
    }

    private void learnCompositeDisposable(){
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable = Observable
                .just("Hello")
                .subscribeWith(observer);
        compositeDisposable.add(disposable);
        compositeDisposable.dispose();
    }

}
