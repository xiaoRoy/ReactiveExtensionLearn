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
        new LearnRepeatWhen().testRepeatC();
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

    private Observable<Boolean> mockBooleanServer(int number) {
        System.out.println("number:" + number);
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
        Queue<Integer> integerQueue = new ArrayDeque<>();
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(1, 3, 4, 6));
        mockBooleanServer(1)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Observable<Object> objectObservable) throws Exception {
                      return objectObservable
                                .flatMap(object -> Observable
                                        .fromIterable(numbers)
                                        .flatMap(numbers -> mockBooleanServer(numbers)));
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
        Queue<Integer> integerQueue = new ArrayDeque<>();
        integerQueue.addAll(Arrays.asList(1, 4, 6, 7));
        mockBooleanServer(integerQueue.peek())
                .repeat()
                .takeUntil(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean aBoolean) throws Exception {
                        return aBoolean;
                    }
                })
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        integerQueue.remove();
                        System.out.println("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    private void testRepeatC(){
        Observable
                .fromArray(5, 6, 7, 9)
                .flatMap(number -> mockBooleanServer(number))
                .takeUntil(aBoolean -> aBoolean)
                .subscribe();
    }

}
