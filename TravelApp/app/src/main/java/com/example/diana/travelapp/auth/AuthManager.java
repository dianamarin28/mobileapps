package com.example.diana.travelapp.auth;

import com.example.diana.travelapp.serverUtils.Request;
import com.example.diana.travelapp.serverUtils.RequestBuilder;
import com.example.diana.travelapp.serverUtils.RequestTags;

/**
 * Created by Diana on 1/15/2017.
 */

public class AuthManager {

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean logUser(String username, String password) {
        String URL = new RequestBuilder().setType(RequestTags.TYPE_AUTENTICATION)
                .setParam(RequestTags.KEY_USERNAME, username)
                .setParam(RequestTags.KEY_PASSWORD, password)
                .build();

        Request request = new Request();
        String response = request.makeRequest(URL);

        if (this.user == null && response.equals("SUCCESS\n")) {
            User u = new User(username, true);
            this.user = u;
        }
        if (response.equals("SUCCESS\n")) {
            return true;
        }
        else {
            return false;
        }
    }
}
