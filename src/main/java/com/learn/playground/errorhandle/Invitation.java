package com.learn.playground.errorhandle;

import java.util.ArrayList;
import java.util.List;

public class Invitation {

    private String id;

    public Invitation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<Invitation> generateInvitation(){
        List<Invitation> invitations = new ArrayList<>();
        invitations.add(new Invitation("1000"));
        invitations.add(new Invitation("1001"));
        invitations.add(new Invitation("1002"));
        invitations.add(new Invitation("1003"));
        return invitations;
    }

    public static void main(String[] args) {

    }
}
