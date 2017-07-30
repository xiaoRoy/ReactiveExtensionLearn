package com.learn.reactive.chap2.http;

public interface ConnectionCallback {
    void onSuccess(Response response);
    void onFailure(Exception exception);
}
