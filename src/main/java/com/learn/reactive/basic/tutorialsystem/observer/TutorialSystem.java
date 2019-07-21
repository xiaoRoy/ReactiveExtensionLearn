package com.learn.reactive.basic.tutorialsystem.observer;

import com.learn.reactive.basic.tutorialsystem.observer.model.TutorialObservable;
import com.learn.reactive.basic.tutorialsystem.observer.model.UserObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TutorialSystem {

    private List<UserObserver> mUsers = new ArrayList<>();
    private TutorialObservable mTutorialView = new TutorialObservable();
    private TutorialObservable mTutorialGeneric = new TutorialObservable();

    public static void main(String[] args) {
        TutorialSystem tutorialSystem = new TutorialSystem();
        tutorialSystem.initUser();
        System.out.println("Tutorial updated, send email to the user.");
        tutorialSystem.publishNewTutorial();
        System.out.println("New user has subscribe the tutorial.");
        tutorialSystem.subscribe(new UserObserver("Peter", "peter@world.com"));
        System.out.println("Tutorial updated again, send email to the user.");
        tutorialSystem.publishNewTutorial();
    }

    private void initUser(){
        UserObserver userJack = new UserObserver("Jack", "jack@blackpearl.com");
        UserObserver userWill = new UserObserver("Will", "will@blackpearl.com");
        UserObserver userHector = new UserObserver("Hector", "hector@blackpearl.com");
        Collections.addAll(mUsers, userJack, userWill, userHector);
        for(UserObserver userObserver : mUsers){
            mTutorialGeneric.register(userObserver);
//            mTutorialView.register(userObserver);
        }
//        mTutorialGeneric.publishTutorial();
//        mTutorialView.publishTutorial();
    }

    private void subscribe(UserObserver user){
        mTutorialGeneric.register(user);
    }

    private void publishNewTutorial(){
        mTutorialGeneric.publishTutorial();
    }

}
