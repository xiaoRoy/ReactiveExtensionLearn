package com.learn.reactive.chap2;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import org.omg.CORBA.TIMEOUT;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class InfiniteStreams {

    public static void main(String[] args) {
        //blockClientThread();
       // explicitConcurrency();
        testDelay();
    }


    //bad example
    private static void blockClientThread() {
        Observable<BigInteger> observable = Observable.create(new ObservableOnSubscribe<BigInteger>() {
            @Override
            public void subscribe(ObservableEmitter<BigInteger> emitter) throws Exception {
                BigInteger integer = BigInteger.ZERO;
                while (true) {
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

    //explicit concurrency
    private static void explicitConcurrency() {
        Observable<BigInteger> observable = Observable.create(new ObservableOnSubscribe<BigInteger>() {
            @Override
            public void subscribe(ObservableEmitter<BigInteger> emitter) throws Exception {
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        BigInteger integer = BigInteger.ZERO;
                        while (!emitter.isDisposed()) {
                            emitter.onNext(integer);
                            integer = integer.add(BigInteger.ONE);
                        }
                    }
                };
//                Executors.newFixedThreadPool(1).execute(runnable);
                new Thread(runnable).start();
            }
        });

        observable.subscribe(new Consumer<BigInteger>() {
            @Override
            public void accept(BigInteger bigInteger) throws Exception {
                System.out.println(bigInteger);
            }
        });
        System.out.println("Main Thread");
    }

    private static <T> Observable<T> delayNotEfficient(T t){
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        sleep(10, TimeUnit.SECONDS);
                        while (!emitter.isDisposed()) {
                            emitter.onNext(t);
                            emitter.onComplete();
                        }
                    }
                };
                Executors.newFixedThreadPool(3).execute(runnable);
            }
        });
    }

    private static <T> Observable<T> delay(T t){
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        sleep(10, TimeUnit.SECONDS);
                        while (!emitter.isDisposed()) {
                            emitter.onNext(t);
                            emitter.onComplete();
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
                emitter.setDisposable(new Disposable() {
                    @Override
                    public void dispose() {
                        thread.interrupt();
                    }

                    @Override
                    public boolean isDisposed() {
                        return false;
                    }
                });
            }
        });
    }

    private static void sleep(int timeout, TimeUnit unit){
        try {
            unit.sleep(timeout);
        } catch (InterruptedException exception) {
            System.out.println("I am interrupted.");
        }
    }

    private static void testDelay(){
        Disposable disposable = delay(new Tweet()).subscribe();
        disposable.dispose();
    }

    private static Observable<Tweet> loadAllTweets(Collection<Integer> ids){
        return Observable.create(new ObservableOnSubscribe<Tweet>() {
            @Override
            public void subscribe(ObservableEmitter<Tweet> emitter) throws Exception {
                ExecutorService pool = Executors.newFixedThreadPool(10);
                AtomicInteger countDown = new AtomicInteger(ids.size());
                //DANGER, violates Rx contract. Don't do this!
                for(Integer id : ids){
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            emitter.onNext(loadTweetById(id));
                            if(countDown.decrementAndGet() == 0){
                                pool.shutdown();
                                emitter.onComplete();
                            }
                        }
                    });
                }
            }
        });
    }

    private static Tweet loadTweetById(int id){
        /*
        * load tweet
        * */
        Tweet tweet = new Tweet();
        tweet.setId(String.valueOf(id));
        return tweet;
    }

    private static Observable<Tweet> rxLoadTweet(int id){
        return Observable.create(new ObservableOnSubscribe<Tweet>() {
            @Override
            public void subscribe(ObservableEmitter<Tweet> emitter) throws Exception {
                try {
                    emitter.onNext(loadTweetById(1));
                    emitter.onComplete();
                }catch (Exception exception){
                    emitter.onError(exception);
                }
            }
        });
    }
}
