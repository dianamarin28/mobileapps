package com.example.diana.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        Map<String, String> entryData1 = new HashMap<>();
        entryData1.put("country", "Romania");
        entryData1.put("city", "Cluj-Napoca");
        data.add(entryData1);

        Map<String, String> entryData2 = new HashMap<>();
        entryData2.put("country", "Germany");
        entryData2.put("city", "Berlin");
        data.add(entryData2);

        Map<String, String> entryData3 = new HashMap<>();
        entryData3.put("country", "Romania");
        entryData3.put("city", "Sibiu");
        data.add(entryData3);

        Map<String, String> entryData4 = new HashMap<>();
        entryData4.put("country", "France");
        entryData4.put("city", "Paris");
        data.add(entryData4);

        Map<String, String> entryData5 = new HashMap<>();
        entryData5.put("country", "Australia");
        entryData5.put("city", "Sydney");
        data.add(entryData5);

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"country", "city"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});

        final ListView placesList = (ListView) findViewById(R.id.placesList);
        placesList.setAdapter(adapter);

        placesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent editItem = new Intent(PlacesActivity.this, PlaceEditActivity.class);
                Map<String, String> selectedFromList = (Map<String, String>) placesList.getItemAtPosition(i);
                editItem.putExtra("country", selectedFromList.get("country"));
                editItem.putExtra("city", selectedFromList.get("city"));

                startActivity(editItem);
            }
        });
    }
}
