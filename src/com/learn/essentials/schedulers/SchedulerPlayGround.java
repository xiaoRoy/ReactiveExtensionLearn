package com.learn.essentials.schedulers;

import io.reactivex.Observable;

public class SchedulerPlayGround {


    public static void main(String[] args) {
        new SchedulerPlayGround().singleThreadDefault();
    }

    private void singleThreadDefault(){
        System.out.println("Single Thread start");
        Observable.create(emitter ->{
            emitter.onNext(1);
            emitter.onNext(3);
            emitter.onNext(5);})
            .doOnEach(RxLogUtils.log("Test", ""))
        .subscribe(System.out::println);
        System.out.println("Single Thread end");
    }
}
