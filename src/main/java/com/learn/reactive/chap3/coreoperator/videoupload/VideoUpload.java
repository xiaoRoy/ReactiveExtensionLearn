package com.learn.reactive.chap3.coreoperator.videoupload;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.Random;

public class VideoUpload {

    private void storeVideoIncorrect(String id){
        // rating observable got lost
        upload(id).subscribe(
                progress -> {},
                illegalStateException -> System.out.println("Error"),
                () -> rate(id)
        );
    }

    private void storeVideo(String id){
        Observable<Rating> observable = upload(id).flatMap(
                progress -> Observable.empty(),
                illegalStateException -> Observable.error(illegalStateException),
                () -> rate(id)
        );
    }


    private Observable<Integer> upload(String id){
       return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if(new Random(10).nextInt() > 5){
                    //upload progress, but in this case we do not mind the progress
                    emitter.onNext(10);
                    emitter.onNext(32);
                    emitter.onNext(44);
                    emitter.onNext(50);
                    emitter.onNext(100);
                    emitter.onComplete();
                } else{
                    emitter.onError(new IllegalStateException());
                }
            }
        });
    }

    private Observable<Rating> rate(String id){
        return Observable.just(new Rating());
    }

}
