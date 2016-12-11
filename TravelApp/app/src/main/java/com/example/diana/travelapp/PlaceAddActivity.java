package com.example.diana.travelapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class PlaceAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_add);

        Button buttonAdd = (Button) findViewById(R.id.addButton);
        final EditText country = (EditText) findViewById(R.id.addCountryField);
        final EditText city = (EditText) findViewById(R.id.addCityField);
        final EditText rating = (EditText) findViewById(R.id.addRatingField);

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog d = new Dialog(PlaceAddActivity.this);
                d.setTitle("NumberPicker");
                d.setContentView(R.layout.dialog);
                Button b1 = (Button) d.findViewById(R.id.button1);
                Button b2 = (Button) d.findViewById(R.id.button2);
                final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
                np.setMinValue(1);
                np.setMaxValue(10);
                np.setWrapSelectorWheel(false);
                b1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        rating.setText(String.valueOf(np.getValue()));
                        d.dismiss();
                    }
                });
                b2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
                d.show();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBRepo db = new DBRepo(getApplicationContext(), null);
                int lastID = db.getPlacesCount();
                Place place = new Place(lastID++, country.getText().toString(), city.getText().toString(), Integer.parseInt(rating.getText().toString()));
                boolean isSaved = db.addPlace(place);
                if(isSaved){
                    Toast toast = Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG);
                    toast.show();
                }
                finish();
            }
        });
    }
}
