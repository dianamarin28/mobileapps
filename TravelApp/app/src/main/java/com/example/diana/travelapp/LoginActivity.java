package com.example.diana.travelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diana.travelapp.global.AppValues;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AppValues applicationContext = (AppValues) getApplication();
                boolean loginStatus = applicationContext.getAuthManager().logUser(username.getText().toString(), password.getText().toString());

                Toast toast;
                if (loginStatus) {
                    toast = Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG);
                } else {
                    toast = Toast.makeText(getApplicationContext(), "Error! Recheck credentials!", Toast.LENGTH_LONG);
                }
                toast.show();
            }
        });

    }
}
