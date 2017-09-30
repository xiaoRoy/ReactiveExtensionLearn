package com.learn.reactive.chap2;

import com.learn.reactive.chap2.http.HttpConnection;
import com.learn.reactive.chap2.http.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.subscribers.ResourceSubscriber;


public class UseCaseCallback {

    public static void main(String[] args) {
        //makeRequestWithCallback();
        makeRequestWithObservable();
    }

    private static void makeRequestWithCallback(){
        new HttpConnection().makeRequest(
                response -> {System.out.println("onSuccess");},
                exception ->{ System.out.println("onFailure");});
    }

    private static void makeRequestWithObservable(){
        observer().subscribe(new Observer<Response>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable;
            }

            @Override
            public void onNext(Response response) {
                System.out.println("onSuccess");
                if(!this.disposable.isDisposed()){
                    this.disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onFailure");
                if(!this.disposable.isDisposed()){
                    this.disposable.dispose();
                }
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private static Observable<Response> observer(){
        return Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                new HttpConnection().makeRequest(
                        response -> {emitter.onNext(response);},
                        exception -> {emitter.onError(exception);});
                emitter.setDisposable(Disposables.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        /*
                        * clean up
                        * */
                        System.out.println("clean up");
                    }
                }));
            }
        });
    }
}
