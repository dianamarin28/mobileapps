package com.example.diana.travelapp.auth;

import com.example.diana.travelapp.serverUtils.Request;
import com.example.diana.travelapp.serverUtils.RequestBuilder;
import com.example.diana.travelapp.serverUtils.RequestTags;
import com.example.diana.travelapp.serverUtils.ResponseParser;

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
        ResponseParser responseParser = new ResponseParser(response);
        String status = responseParser.getStringValue("status");

        if (this.user == null && status.equals("SUCCESS")) {
            User u = new User(username, true);
            this.user = u;
        }
        if (status.equals("SUCCESS")) {
            return true;
        }
        else {
            return false;
        }
    }
}
