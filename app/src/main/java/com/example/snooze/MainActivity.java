package com.example.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.api.snooze.ApiConnector;
import com.example.api.snooze.SnoozeUsersService;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String accessToken = "VuBQEphnpyJ21kjHRCDsDtvJOCA2ULzhUDkGi4nIyOK0HxD7z7qVTEVRBynlzD6M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this,LogIn.class);
        startActivity(i);

        /* CONNECTION TO API */
        ApiConnector connect = new ApiConnector();
        retrofit = connect.getRetrofitInstance();
        SnoozeUsersService service = retrofit.create(SnoozeUsersService.class);


    }
}
