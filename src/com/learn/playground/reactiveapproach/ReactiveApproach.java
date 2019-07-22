package com.learn.playground.reactiveapproach;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public class ReactiveApproach {

    private List<String> alphabet = Arrays.asList("A", "B", "C", "D");

    private String toUpperCase(String text) {
        return text.toUpperCase();
    }

    private Observable<String> toUpperCaseReactive(String text) {
        return Observable
                .just(text)
                .map(textToHandle -> textToHandle.toUpperCase());
    }

    private void xx() {
        Single<String> single = Observable
                        .fromIterable(alphabet)
                        .first("D");
        Maybe<String> maybe = Observable
                        .fromIterable(alphabet)
                        .firstElement();
        Completable completable = Observable
                        .fromIterable(alphabet)
                        .ignoreElements();
    }
}
