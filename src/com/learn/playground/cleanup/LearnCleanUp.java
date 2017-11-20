package com.learn.playground.cleanup;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class LearnCleanUp {

    public static void main(String[] args) {
        new LearnCleanUp().testDisposable();
    }

    private void testDisposable(){
        getNumbersSource().subscribe(new Observer<Integer>() {
            private Disposable disposable;
            @Override
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable;
            }

            @Override
            public void onNext(Integer number) {
                if(number % 2 == 0 && !disposable.isDisposed()){
                    disposable.dispose();
                }
                System.out.println("trail.onNext.number:" + number);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private static Observable<Integer> getNumbersSource(){
        return Observable.just(1, 3, 5, 7, 9, 11, 2, 13, 15);
    }
}
