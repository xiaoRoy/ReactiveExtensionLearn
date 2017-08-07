package com.learn.reactive.chap2;

import com.learn.reactive.chap2.http.HttpConnection;
import com.learn.reactive.chap2.http.Response;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ResponseSubject {
    private final PublishSubject<Response> responseSubject = PublishSubject.create();

    public ResponseSubject() {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.makeRequest(response -> {
            responseSubject.onNext(response);
        }, throwable ->{
            responseSubject.onError(throwable);
        });
    }

    public Observable<Response> getResponseSubject() {
        return responseSubject;
    }
}
