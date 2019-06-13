package com.example.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String accessToken = "GN0tME3nUBa6auETCDju80cAzMSMDaDY791UafudXydp6AwwLfVjEJDDxJTjHEg3";

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
