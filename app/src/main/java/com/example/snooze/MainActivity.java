package com.example.snooze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiConnector connect = new ApiConnector();

        retrofit = connect.getRetrofitInstance();



        SnoozeUsersService service = retrofit.create(SnoozeUsersService.class);
        Call<List<SnoozeUsers>> call = service.getAllUsers();


    }
}
