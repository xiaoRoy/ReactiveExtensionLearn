package com.learn.reactive.basic.model;

import java.util.List;

public class User {


    private String name;

    private String email;

    public static void sendEmail(List<User> users) {
        if (users != null) {
            for (User user : users) {
                System.out.format("Sent new tutorial email to %1$s\n", user.getName());
            }
        }
    }

    public static void sendEmail(User user) {
        System.out.format("Sent new tutorial email to %1$s\n", user.getName());
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
