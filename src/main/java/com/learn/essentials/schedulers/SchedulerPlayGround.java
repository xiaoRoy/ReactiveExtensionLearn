package com.learn.essentials.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SchedulerPlayGround {


    public static void main(String[] args) {
        new SchedulerPlayGround().intervalScheduler();
    }

    private void singleThreadDefault() {
        System.out.println("Single Thread start");
        Observable.create(emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(3);
                    emitter.onNext(5);
                }
        )
                .doOnEach(RxLogUtils.log("Test", ""))
                .subscribe(System.out::println);
        System.out.println("Single Thread end");
    }

    private void intervalScheduler() {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
        Observable
            .interval(500L, TimeUnit.MILLISECONDS)
            .take(5)
            .doOnEach(RxLogUtils.log("Default Interval"))
            //not working on the current thread
            //.observeOn(Schedulers.trampoline())
            .subscribe();
        timeSleep(5);
    }

    private void internalSchedulerImmediate(){
        Observable
            .interval(500L, TimeUnit.MILLISECONDS, Schedulers.trampoline())
            .take(5)
            .doOnEach(RxLogUtils.log("Immediate Interval"))
            .subscribe();


    }

    private static void timeSleep(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
