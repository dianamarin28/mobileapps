package com.example.diana.travelapp.serverUtils;

/**
 * Created by Diana on 1/15/2017.
 */

public class RequestBuilder {
    private static final String SERVER_URL = "http://locohomeserver.go.ro:8081/server_api/";
    private String FINAL_URL;

    public RequestBuilder() {
        this.FINAL_URL = "" + SERVER_URL;
    }

    public RequestBuilder setType(String type) {
        this.FINAL_URL += type + "/?";
        return this;
    }

    public RequestBuilder setAction(String action) {
        this.FINAL_URL += "action=" + action + "&";
        return this;
    }

    public RequestBuilder setParam(String key, String value) {
        this.FINAL_URL += key + "=" + value + "&";
        return this;
    }

    public String build() {
        return this.FINAL_URL;
    }
}
