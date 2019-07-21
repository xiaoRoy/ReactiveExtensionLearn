package com.learn.reactive.chap2;

import com.learn.reactive.chap2.http.HttpConnection;
import com.learn.reactive.chap2.http.Response;
import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


//DON'T DO THIS, very brittle and error prone
public class LazyResponseObservable {

    private Set<ObservableEmitter<Response>> emitterSet = new CopyOnWriteArraySet<>();

    private HttpConnection httpConnection;

    public LazyResponseObservable() {
       this.httpConnection = new HttpConnection();
       this.httpConnection.makeRequest(
               response -> {
                   for(Emitter<Response> emitter : emitterSet){
                       emitter.onNext(response);
                   }
               },
               exception ->{
                   for(Emitter<Response> emitter : emitterSet){
                       emitter.onError(exception);
                   }
               }
               );
    }

    private Observable<Response> observable = Observable.create(new ObservableOnSubscribe<Response>() {
        @Override
        public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
            register(emitter);
            emitter.setDisposable(Disposables.fromRunnable(() -> {deregister(emitter);}));
        }
    });

    Observable<Response> getObservable() {
        return observable;
    }

    private synchronized void register(ObservableEmitter<Response> emitter){
        if(this.emitterSet.isEmpty()){
            /*
            * some code to start the observable stream
            * */
        }
        this.emitterSet.add(emitter);
    }

    private synchronized void deregister(ObservableEmitter<Response> emitter){
        this.emitterSet.remove(emitter);
        if(this.emitterSet.isEmpty()){
            /*
            *clean up
            * */
        }
    }

}
