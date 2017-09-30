package com.learn.reactive.chap2.connectable;

public class ConnectableMain {
    public static void main(String[] args) {
        EventUpdateListener eventUpdateListener
                        = new EventUpdateListener();
        Apple apple = new Apple(eventUpdateListener.getObservable());
        Bear bear = new Bear(eventUpdateListener.getObservable());
        eventUpdateListener.onEventUpdated(new Event());
    }
}
