package com.learn.playground.errorhandle;

import io.reactivex.*;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import javax.naming.event.ObjectChangeListener;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DelayError {

    public static void main(String[] args) {
        new DelayError().doDeleteGuest();
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private Observable<String> mockDeleteInvitation(Invitation invitation){
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            TimeUnit.MILLISECONDS.sleep(randomApiTime());
            emitter.onNext("OK");
        }).subscribeOn(Schedulers.io())
                .map(response -> invitation.getId())
                .onErrorReturnItem("ERROR");
    }

    private Observable<String> mockDeleteGuest(String guestId){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                TimeUnit.MILLISECONDS.sleep(randomApiTime());
                emitter.onNext("OK");
            }
        });
    }

    private static int randomApiTime(){
        Random random = new Random();
        int range = 500 - 200 + 1;
        return random.nextInt(range) + 200;
    }

    private void doDeleteGuest(){
        List<Observable<String>> observableList = new ArrayList<>();
        for(Invitation invitation : Invitation.generateInvitation()){
            observableList.add(mockDeleteInvitation(invitation));
        }
        Observable.zipIterable(observableList, new Function<Object[], Invitation>() {
            @Override
            public Invitation apply(Object[] objects) throws Exception {
                return null;
            }
        }, false, 4);
    }
}
