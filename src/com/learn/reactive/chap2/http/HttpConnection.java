package com.learn.reactive.chap2.http;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class HttpConnection {

    private void makeRequest(ConnectionCallback connectionCallback){
        /*
        * mock http request
        * */
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        int result = new Random(10).nextInt();
        if(result >= 5){
            connectionCallback.onSuccess(new Response());
        } else {
            connectionCallback.onFailure(new IOException());
        }
    }

    public void makeRequest(Consumer<Response> onSuccess, Consumer<Exception> onFailure){
        makeRequest(new ConnectionCallback() {
            @Override
            public void onSuccess(Response response) {
                onSuccess.accept(response);
            }

            @Override
            public void onFailure(Exception exception) {
                onFailure.accept(exception);
            }
        });
    }

}
