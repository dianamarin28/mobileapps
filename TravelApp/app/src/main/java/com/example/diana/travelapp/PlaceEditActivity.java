package com.example.diana.travelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PlaceEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_edit);

        Bundle extras = getIntent().getExtras();

        TextView editCountry = (TextView) findViewById(R.id.editCountry);
        TextView editCity = (TextView) findViewById(R.id.editCity);
        editCountry.setText(extras.getString("country"));
        editCity.setText(extras.getString("city"));
    }
}
