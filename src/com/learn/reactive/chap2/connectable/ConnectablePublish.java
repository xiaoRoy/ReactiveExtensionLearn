package com.learn.reactive.chap2.connectable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observables.ConnectableObservable;

public class ConnectablePublish {

    private Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            emitter.onNext("");
        }
    });

    //naive approach
    private Observable<String> textObservable(){
        return observable.doOnNext(string -> System.out.println("doOnNext"));
    }

    //publish
    private void publishConnect(){
        observable.publish().connect();
    }

    public static void main(String[] args) {
        new ConnectablePublish().publishConnect();
    }
}
