package com.example.diana.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacesActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        // list
        DBRepo db = new DBRepo(getApplicationContext(), null);
        Place[] places = db.getAllPlaces();
        List<String> placesForList = new ArrayList<>();
        for (Place p : places) {
            placesForList.add(p.toString());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, placesForList);
        final ListView placesList = (ListView) findViewById(R.id.placesList);
        placesList.setAdapter(adapter);

        placesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent editItem = new Intent(PlacesActivity.this, PlaceEditActivity.class);
                String[] item = placesList.getItemAtPosition(i).toString().split(" ");

                editItem.putExtra("id", item[0]);
                editItem.putExtra("country", item[1]);
                editItem.putExtra("city", item[2]);
                editItem.putExtra("rating", item[3]);

                startActivity(editItem);
            }
        });

        // go to add place activity
        Button buttonList = (Button) findViewById(R.id.addPlaceButton);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlacesActivity.this, PlaceAddActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DBRepo db = new DBRepo(getApplicationContext(), null);
        Place[] places = db.getAllPlaces();
        List<String> placesForList = new ArrayList<>();
        for(Place p : places) {
            placesForList.add(p.toString());
        }
        this.adapter.clear();
        this.adapter.addAll(placesForList);
    }
}
