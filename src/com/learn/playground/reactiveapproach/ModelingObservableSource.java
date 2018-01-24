package com.learn.playground.reactiveapproach;

public class ModelingObservableSource {


    //item or error
    public <T> Type<T> single() throws Exception{
        return new Type<T>();
    }

    //complete or error
    public void completable() throws Exception{
    }

    private static class Type<T>{
        private T t;
    }
}
