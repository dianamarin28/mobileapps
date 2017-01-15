package com.example.diana.travelapp.serverUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Diana on 1/15/2017.
 */

public class ResponseParser {

    private JSONObject response;

    public ResponseParser(String response) {
        try {
            this.response = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getStringValue(String key) {
        try {
            return this.response.get(key).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public JSONArray getArray(String key) {
        try {
            return this.response.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
