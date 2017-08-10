package com.learn.reactive.chap3.playground;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

public class LearnMerge {

    Observable<Integer> odds = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            emitter.onNext(1);
            emitter.onNext(3);
//            emitter.onNext(5);
            emitter.onError(new NullPointerException());
        }
    }).onErrorReturnItem(5);
    Observable<Integer> evens = Observable.just(2, 4, 6);

    Observable<String> texts = Observable.just("A", "B", "C");

    private Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Integer integer) {
            System.out.println("onNext(), number:" + integer);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("onError()");
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete()");
        }
    };

    public static void main(String[] args) {
        LearnMerge learnMerge = new LearnMerge();
    }


    private void merge(){
        Observable.merge(odds, evens).subscribe(observer);
    }

    private void mergeDelayError(){
        Observable.mergeDelayError(odds, evens).subscribe(observer);
    }
}

