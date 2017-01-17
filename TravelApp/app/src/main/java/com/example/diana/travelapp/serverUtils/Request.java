package com.example.diana.travelapp.serverUtils;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Diana on 1/15/2017.
 */

public class Request {

    public static JSONArray readRequest() {
        String URL = new RequestBuilder().setType(RequestTags.TYPE_REPOSITORY)
                .setAction(RequestTags.ACTION_READ)
                .build();
        Request request = new Request();
        String response = request.makeRequest(URL);
        ResponseParser data = new ResponseParser(response);

        JSONArray places = data.getArray("destinations");
        return places;
    }

    public String makeRequest(String URL) {
        MakeRequestGetResponseTask makeRequestGetResponseTask = new MakeRequestGetResponseTask();
        try {
            return makeRequestGetResponseTask.execute(URL).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class MakeRequestGetResponseTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String content = "";
                String line;
                while((line = br.readLine()) != null) {
                    content += line + "\n";
                 }
                return content;
            } catch (MalformedURLException e) {
                return "Request error!";
            } catch (IOException e) {
                e.printStackTrace();
                return "Request error!";

            }
        }
    }
}
