package com.learn.playground.reactiveapproach;

public class UserManagerCallback {

    private User user = new User();

    public void getUser(UserCallback userCallback){
        userCallback.onFetch(user);
    }

    public interface UserCallback{
        void onFetch(User user);
    }
}
