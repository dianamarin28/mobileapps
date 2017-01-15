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

import com.example.diana.travelapp.serverUtils.Request;
import com.example.diana.travelapp.serverUtils.RequestBuilder;
import com.example.diana.travelapp.serverUtils.RequestTags;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONObject;

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
                // LOCAL STORAGE
//                final DBRepo db = new DBRepo(getApplicationContext(), null);
//                final int placeID = Integer.parseInt(extras.getString("id"));
//
//                Place updatedPlace = new Place(placeID, editCountry.getText().toString(), editCity.getText().toString(), Integer.parseInt(editRating.getText().toString()));
//                boolean isUpdated = db.addPlace(updatedPlace);

                // RMEOTE STORAGE
                String URL = new RequestBuilder().setType(RequestTags.TYPE_REPOSITORY)
                        .setAction(RequestTags.ACTION_UPDATE)
                        .setParam(RequestTags.KEY_ID, extras.getString("id"))
                        .setParam(RequestTags.KEY_NAME, editCountry.getText().toString())
                        .setParam(RequestTags.KEY_TYPE, editCity.getText().toString())
                        .setParam(RequestTags.KEY_YEAR, editRating.getText().toString())
                        .build();
                Request request = new Request();
                String isUpdated = request.makeRequest(URL);

                if(isUpdated != null){
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
//                LOCAL STORAGE
//                final DBRepo db = new DBRepo(getApplicationContext(), null);
//                final int placeID = Integer.parseInt(extras.getString("id"));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.are_you_sure) //
                        .setMessage(R.string.remove_message) //
                        .setPositiveButton(getString(R.string.positive), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();

                                // LOCAL STORAGE
//                                db.deletePlace(placeID);

//                                REMOTE STORAGE
                                String URL = new RequestBuilder().setType(RequestTags.TYPE_REPOSITORY)
                                        .setAction(RequestTags.ACTION_DELETE)
                                        .setParam(RequestTags.KEY_ID, extras.getString("id"))
                                        .build();
                                Request request = new Request();
                                String isSaved = request.makeRequest(URL);

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
//        GraphView chart = (GraphView) findViewById(R.id.chart);
//        DBRepo db = new DBRepo(getApplicationContext(), null);
//
//        DataPoint[] dataForChart = new DataPoint[10];
//        for (int i=0; i<10; i++) {
//            dataForChart[i] = new DataPoint(i, db.getRatingCount(i));
//        }
//
//        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataForChart);
//
//        chart.addSeries(series);

        int countRating5 = 0;
        int countRating4 = 0;
        int countRating3 = 0;
        JSONArray places = Request.readRequest();
        for(int i=0; i<places.length(); i++) {
            try {
                JSONObject place = places.getJSONObject(i);
                if (place.getInt("rating") == 5) {
                    countRating5++;
                }
                else if (place.getInt("rating") == 4) {
                    countRating4++;
                }
                else if (place.getInt("rating") == 3) {
                    countRating3++;
                }

            } catch (Exception ex) {
                System.out.println("Parsing child exception!");
            }
        }

        DataPoint[] dataForChart = new DataPoint[3];
        dataForChart[0] = new DataPoint(3, countRating3);
        dataForChart[1] = new DataPoint(4, countRating4);
        dataForChart[2] = new DataPoint(5, countRating5);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataForChart);

        GraphView chart = (GraphView) findViewById(R.id.chart);
        chart.addSeries(series);
    }
}
