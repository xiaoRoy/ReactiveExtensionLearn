package com.learn.reactive.basic.tutorialsystem.observer;

public interface Observable {

    void register(Observer observer);

    void unRegister(Observer observer);

    void notifyAllObservers();
}
