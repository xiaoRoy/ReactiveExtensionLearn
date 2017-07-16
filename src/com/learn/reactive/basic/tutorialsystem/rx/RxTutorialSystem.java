package com.learn.reactive.basic.tutorialsystem.rx;

import com.learn.reactive.basic.model.Tutorial;
import com.learn.reactive.basic.model.User;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RxTutorialSystem {
    public static void main(String[] args) {
        Observable<Tutorial> observable = Observable.create(new ObservableOnSubscribe<Tutorial>() {
            @Override
            public void subscribe(ObservableEmitter<Tutorial> emitter) throws Exception {

            }
        });
    }
}
