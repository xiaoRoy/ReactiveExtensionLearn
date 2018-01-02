package com.learn.playground.errorhandle;

import java.util.ArrayList;
import java.util.List;

public class Guest {

    private String id;

    private List<Invitation> invitations = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations.clear();
        this.invitations.addAll(invitations);
    }



}
