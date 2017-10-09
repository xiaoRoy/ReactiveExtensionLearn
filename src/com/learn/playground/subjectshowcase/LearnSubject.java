package com.learn.playground.subjectshowcase;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class LearnSubject {

    public static void main(String[] args) {
        new LearnSubject().learnAsyncSubject();
    }

    private void learnPublishSubject(){
        PublishSubject<Integer> publishSubject =PublishSubject.create();
        // It will get 1, 2, 3, 4 and onComplete
        publishSubject.subscribe(new TagObserver("first"));
        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.onNext(3);
        // It will get 4 and onComplete for second observer also.
        publishSubject.subscribe(new TagObserver("second"));
        publishSubject.onNext(4);
        publishSubject.onComplete();
    }

    private void learnReplaySubject(){
        ReplaySubject<Integer> replaySubject = ReplaySubject.create();
        // It will get 1, 2, 3, 4 and onComplete
        replaySubject.subscribe(new TagObserver("first"));
        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);
        // It will get 1, 2, 3, 4 and onComplete too.
        replaySubject.subscribe(new TagObserver("second"));
        replaySubject.onNext(4);
        replaySubject.onComplete();
    }

    private void learnBehaviorSubject(){
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();
        // It will get 1, 2, 3, 4 and onComplete
        behaviorSubject.subscribe(new TagObserver("first"));
        behaviorSubject.onNext(1);
        behaviorSubject.onNext(2);
        behaviorSubject.onNext(3);
        // It will get 3(last emitted)and 4(subsequent item) and onComplete
        behaviorSubject.subscribe(new TagObserver("second"));
        behaviorSubject.onNext(4);
        behaviorSubject.onComplete();
    }

    private void learnAsyncSubject(){
        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        // It will get only 4 and onComplete
        asyncSubject.subscribe(new TagObserver("first"));
        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.onNext(3);
        // It will also get only get 4 and onComplete
        asyncSubject.subscribe(new TagObserver("second"));
        asyncSubject.onNext(4);
        asyncSubject.onComplete();
    }

    private static class TagObserver implements Observer<Integer>{
        private final String tag;

        public TagObserver(String tag) {
            this.tag = tag;
        }

        public void onSubscribe(Disposable disposable) {
            System.out.println(tag + ":trail.onSubscribe");
        }

        @Override
        public void onNext(Integer integer) {
            System.out.println(tag + ":trail.onNext");
            System.out.println("trail.onNext.number:" + integer);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println(tag + ":trail.onError");
        }

        @Override
        public void onComplete() {
            System.out.println(tag + ":trail.onComplete");
        }
    }

}

