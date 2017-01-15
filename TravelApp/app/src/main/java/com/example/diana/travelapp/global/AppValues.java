package com.example.diana.travelapp.global;

import android.app.Application;

import com.example.diana.travelapp.auth.AuthManager;

/**
 * Created by Diana on 1/15/2017.
 */

public class AppValues extends Application {

    private AuthManager authManager = new AuthManager();

    public AuthManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }
}
