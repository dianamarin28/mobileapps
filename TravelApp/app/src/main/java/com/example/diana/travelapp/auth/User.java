package com.example.diana.travelapp.auth;

/**
 * Created by Diana on 1/15/2017.
 */

public class User {

    private String username;
    private boolean isLogged;

    public User(String username, boolean isLogged) {
        this.username = username;
        this.isLogged = isLogged;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
