package com.learn.reactive.chap2.connectable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observables.ConnectableObservable;

public class EventUpdateListener implements OnEventChangeListener {

    private final ConnectableObservable<String> observable =
            Observable.<String>create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    System.out.println("Staring");
                    emitter.onNext("");
                }
            }).publish();

    @Override
    public void onEventUpdated(Event event) {
        System.out.println("Connecting");
        this.observable.connect();
    }

    public ConnectableObservable<String> getObservable() {
        return observable;
    }
}
