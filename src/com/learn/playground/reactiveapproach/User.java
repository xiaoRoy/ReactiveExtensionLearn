package com.learn.playground.reactiveapproach;


public class User implements Cloneable{

    private String id;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User)super.clone();
    }

}
