package com.learn.reactive.chap2;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.math.BigInteger;

public class InfiniteStreams {

    public static void main(String[] args) {
        blockClientThread();
    }

    private static void blockClientThread(){
        Observable<BigInteger> observable = Observable.create(new ObservableOnSubscribe<BigInteger>() {
            @Override
            public void subscribe(ObservableEmitter<BigInteger> emitter) throws Exception {
                BigInteger integer = BigInteger.ZERO;
                while (true){
                    emitter.onNext(integer);
                    integer = integer.add(BigInteger.ONE);
                }
            }
        });

        observable.subscribe(bigInteger -> {
            System.out.println(bigInteger);
        });

        System.out.println("Never land.");
    }
}
