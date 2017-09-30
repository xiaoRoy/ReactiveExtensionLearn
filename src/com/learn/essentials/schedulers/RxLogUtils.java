package com.learn.essentials.schedulers;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class RxLogUtils {

    static <T> Consumer<Notification<? super T>> log(String description, String offset) {
        AtomicReference<String> nextOffset = new AtomicReference<>(">");
        return (Notification<? super T> notification) -> {
            if (notification.isOnNext()) {
                System.out.println(
                        Thread.currentThread().getName() + "|"
                                + description + ": " + offset + nextOffset.get()
                                + notification.getValue()
                );
            } else if (notification.isOnError()) {
                System.out.println(
                        Thread.currentThread().getName() + "|"
                                + description + ": " + offset + nextOffset.get() + "X"
                                + notification.getError()
                );
            } else {
                System.out.println(
                        Thread.currentThread().getName() + "|"
                                + description + ": " + offset + nextOffset.get()
                                + "|"
                );
            }
            nextOffset.getAndUpdate(oldOffset ->  "-" + oldOffset);
        };
    }

    static <T> Consumer<Notification<? super T>> log(String description){
        return log(description, "");
    }



    public static void main(String[] args) throws InterruptedException {
        Observable
            .range(5, 5)
            .doOnEach(log("Test", ""))
            .subscribe();
    }
}
