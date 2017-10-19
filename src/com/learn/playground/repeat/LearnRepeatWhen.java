package com.learn.playground.repeat;

import io.reactivex.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

import java.util.*;

public class LearnRepeatWhen {

    public static void main(String[] args) {
        new LearnRepeatWhen().testRepeat();
    }

    private Observable<String> mockServer() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                int flag = new Random().nextInt(1000);
                System.out.println("response.flag:" + flag);
                if (flag > 500) {
                    emitter.onNext("Success");
                } else {
                    emitter.onError(new IllegalArgumentException());
                }
            }
        });
    }

    private Observable<Integer> mockIntServer() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                int flag = new Random().nextInt(1000);
                System.out.println("response.flag:" + flag);
                emitter.onNext(flag);
            }
        });
    }

    private Observable<Boolean> mockBooleanServer() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                int flag = new Random().nextInt(1000);
                System.out.println("flag:" + flag);
                boolean isCompleted = flag > 500;
                emitter.onNext(isCompleted);
                emitter.onComplete();
            }
        });
    }

    private void testRepeat() {
        mockBooleanServer()
                .repeatWhen(new Function<Observable<Object>, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Observable<Object> objectObservable) throws Exception {
                        return Observable.just(400, 401, 100, 500, 100);
                    }
                })
                .takeUntil(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean aBoolean) throws Exception {
                        return aBoolean;
                    }
                })
                .subscribe();
    }

    private final PublishSubject<Integer> updateSubject = PublishSubject.create();

    private void testRepeatB() {
        mockIntServer().repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        return null;
                    }
                });
                return null;
            }
        });
    }

}
