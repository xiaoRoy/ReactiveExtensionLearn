package com.learn.reactive.chap3.coreoperator.flatorder;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import org.omg.CORBA.OBJ_ADAPTER;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FlatOrder {

    private static final int DAY_OF_WEEK_SUNDAY = 0;
    private static final int DAY_OF_WEEK_MONDAY = 1;


    public static void main(String[] args) throws InterruptedException {
        FlatOrder flatOrder = new FlatOrder();
        Observable
                .just(DAY_OF_WEEK_SUNDAY, DAY_OF_WEEK_MONDAY)
//                .flatMap(dayOfWeek -> new FlatOrder().loadRecord(dayOfWeek))
                .flatMap(dayOfWeek -> flatOrder.loadRecord(dayOfWeek))
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(10);
    }

    private Observable<String> loadRecord(int dayOfWeek){
        Observable<String> result;
        switch (dayOfWeek){
            case DAY_OF_WEEK_SUNDAY:
                result = getNumberObservable()
                        .filter(number -> number % 2 == 0)
                        .map(number -> "evenResult:" + number)
                        .subscribeOn(Schedulers.newThread())
                        ;
                break;
            case DAY_OF_WEEK_MONDAY:
            default:
                result = getNumberObservable()
                        .filter(number -> number % 2 != 0)
                        .map(number -> "oddResult:" + number)
                        ;
                break;
        }
        return result;
    }

    private Observable<String> loadRecordInterval(int dayOfWeek){
        switch (dayOfWeek){
            case DAY_OF_WEEK_SUNDAY:
                return Observable
                        .interval(90, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(i -> "Sun-" + i)
                        ;
            case DAY_OF_WEEK_MONDAY:
            default:
                return Observable
                        .interval(65, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(i -> "Mon-" + i)
                        ;
        }
    }

    private Observable<Integer> getNumberObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
                @Override
                public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                    for(int number = 0; number < 299; number ++){
                        emitter.onNext(number);
                    }
                }
            });
    }
}
