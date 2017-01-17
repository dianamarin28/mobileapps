package com.example.diana.travelapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // email
        Button buttonEmail = (Button) findViewById(R.id.buttonEmail);
        final TextView emailDestination = (TextView) findViewById(R.id.emailDestination);
        final TextView emailText = (TextView) findViewById(R.id.emailText);

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Check this awesome app!";

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailDestination.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailText.getText());

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // go to list
        Button buttonList = (Button) findViewById(R.id.buttonList);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PlacesActivity.class));
            }
        });
    }
}
