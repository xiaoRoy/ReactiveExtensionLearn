package com.learn.reactive.basic.tutorialsystem.observer.model;

import com.learn.reactive.basic.model.User;
import com.learn.reactive.basic.tutorialsystem.observer.Observer;

public class UserObserver extends User implements Observer {

    public UserObserver(String name, String email) {
        super(name, email);
    }

    public UserObserver() {
    }

    @Override
    public void update() {
        User.sendEmail(this);
    }
}
