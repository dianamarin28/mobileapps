package com.example.diana.travelapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class PlaceEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_edit);

        final Bundle extras = getIntent().getExtras();

        final TextView editCountry = (TextView) findViewById(R.id.editCountry);
        final TextView editCity = (TextView) findViewById(R.id.editCity);
        final TextView editRating = (TextView) findViewById(R.id.editRating);
        editCountry.setText(extras.getString("country"));
        editCity.setText(extras.getString("city"));
        editRating.setText(extras.getString("rating"));

        Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);

        final Context context = this;

        // actions for update and delete buttons
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DBRepo db = new DBRepo(getApplicationContext(), null);
                final int placeID = Integer.parseInt(extras.getString("id"));

                Place updatedPlace = new Place(placeID, editCountry.getText().toString(), editCity.getText().toString(), Integer.parseInt(editRating.getText().toString()));
                boolean isUpdated = db.addPlace(updatedPlace);
                if(isUpdated){
                    Toast toast = Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG);
                    toast.show();
                }
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DBRepo db = new DBRepo(getApplicationContext(), null);
                final int placeID = Integer.parseInt(extras.getString("id"));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.are_you_sure) //
                        .setMessage(R.string.remove_message) //
                        .setPositiveButton(getString(R.string.positive), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                db.deletePlace(placeID);
                                Toast toast = Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_LONG);
                                toast.show();
                                finish();
                            }
                        })
                        .setNegativeButton(getString(R.string.negative), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });

        // chart
        GraphView chart = (GraphView) findViewById(R.id.chart);
        DBRepo db = new DBRepo(getApplicationContext(), null);

        DataPoint[] dataForChart = new DataPoint[10];
        for (int i=0; i<10; i++) {
            dataForChart[i] = new DataPoint(i, db.getRatingCount(i));
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataForChart);

        chart.addSeries(series);
    }
}
