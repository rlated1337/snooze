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
        Intent i = new Intent(this,Register.class);
        startActivity(i);

        ApiConnector connect = new ApiConnector();

        retrofit = connect.getRetrofitInstance();

        SnoozeUsersService service = retrofit.create(SnoozeUsersService.class);

        Call<List<SnoozeUsers>> call = service.getAllUsers(accessToken);

        call.enqueue(new Callback<List<SnoozeUsers>>() {
            @Override
            public void onResponse(Call<List<SnoozeUsers>> call, Response<List<SnoozeUsers>> response) {
                    if(!response.isSuccessful()){
                        System.out.println(response.code());
                    }

                    List<SnoozeUsers> users = response.body();

                    for (SnoozeUsers user : users) {
                        String content = "";

                        content += "ID: " + user.getId() +  "\n";
                        content += "Name: " + user.getUsername() + "\n";
                        content += "Email: " + user.getEmail() + "\n";
                        content += "Realm: " + user.getRealm() + "\n";
                        content += "-----------";
                        System.out.println(content);
                    }
            }

            @Override
            public void onFailure(Call<List<SnoozeUsers>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
}
