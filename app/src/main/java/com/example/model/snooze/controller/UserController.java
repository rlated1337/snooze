package com.example.model.snooze.controller;

import com.example.api.snooze.ApiConnector;
import com.example.api.snooze.SnoozeUsersService;
import com.example.api.snooze.inc.SnoozeUsers;
import com.example.model.snooze.User;
import com.example.model.snooze.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserController {
    private UserService userservice;
    private User user;

    private Retrofit retrofit;
    private SnoozeUsersService service;
    private ApiConnector connect;
    private static final String accessToken = "VuBQEphnpyJ21kjHRCDsDtvJOCA2ULzhUDkGi4nIyOK0HxD7z7qVTEVRBynlzD6M";

    public UserController() {
        /* CONNECTION TO API */
        connect = new ApiConnector();
        retrofit = connect.getRetrofitInstance();
        service = retrofit.create(SnoozeUsersService.class);
    }

    public void register(String username, String email, String password){
        SnoozeUsers snoozeUser = new SnoozeUsers("FH",username,email,true ,password);

        Call<SnoozeUsers> call = service.postNewUser(accessToken,snoozeUser);

        call.enqueue(new Callback<SnoozeUsers>() {
            @Override
            public void onResponse(Call<SnoozeUsers> call, Response<SnoozeUsers> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SnoozeUsers> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });

    }

    public void register(){

    }

    public void login(){

    }

    public void executePayment(){

    }

    public void userLogin(){

    }

    public void userLockOut(){

    }
}
