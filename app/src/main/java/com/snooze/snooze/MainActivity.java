package com.snooze.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.snooze.api.snooze.ApiConnector;
import com.snooze.api.snooze.SnoozeUsersService;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Switch to LogInScreen
        Intent i = new Intent(this,LogIn.class);
        startActivity(i);


        System.out.println("in main activity");

        /* CONNECTION TO API */
        ApiConnector connect = new ApiConnector();
        retrofit = connect.getRetrofitInstance();
        SnoozeUsersService service = retrofit.create(SnoozeUsersService.class);


    }
}
