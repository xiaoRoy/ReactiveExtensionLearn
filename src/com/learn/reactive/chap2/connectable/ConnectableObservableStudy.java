package com.learn.reactive.chap2.connectable;


import com.learn.reactive.chap2.http.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class ConnectableObservableStudy {

    public static void main(String[] args) {
        connectable();
    }
    private static void normal(){
        Observable<Response> observable = Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                System.out.println("Establish connection");
                emitter.setDisposable(Disposables.fromRunnable(() -> System.out.println("Disconnection")));
            }
        });
        Disposable disposableA = observable.subscribe();
        System.out.println("DisposableA");
        Disposable disposableB = observable.subscribe();
        System.out.println("DisposableB");
        disposableA.dispose();
        System.out.println("Un-subscribedA");
        disposableB.dispose();
        System.out.println("Un-subscribedB");
    }

    private static void connectable(){
        Observable<Response> observable = Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                System.out.println("Establish connection");
                emitter.setDisposable(Disposables.fromRunnable(() -> System.out.println("Disconnection")));
            }
        }).publish().refCount();
        Disposable disposableA = observable.subscribe();
        System.out.println("DisposableA");
        Disposable disposableB = observable.subscribe();
        System.out.println("DisposableB");
        disposableA.dispose();
        System.out.println("Un-subscribedA");
        disposableB.dispose();
        System.out.println("Un-subscribedB");
    }
}
