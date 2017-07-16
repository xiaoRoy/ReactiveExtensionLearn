package com.learn.reactive.basic.tutorialsystem.observer.model;


import com.learn.reactive.basic.model.Tutorial;
import com.learn.reactive.basic.tutorialsystem.observer.Observable;
import com.learn.reactive.basic.tutorialsystem.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class TutorialObservable  extends Tutorial implements Observable{

    private List<Observer> mObserverList = new ArrayList<>();


    @Override
    public void register(Observer observer) {
        mObserverList.add(observer);
    }

    @Override
    public void unRegister(Observer observer) {
        int index = mObserverList.indexOf(observer);
        if(index > -1){
            mObserverList.remove(index);
        }
    }

    @Override
    public void notifyAllObservers() {
        for(Observer observer : mObserverList){
            observer.update();
        }
    }

    public void publishTutorial(){
        notifyAllObservers();
    }
}
