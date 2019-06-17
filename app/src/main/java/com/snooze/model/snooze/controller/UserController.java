package com.snooze.model.snooze.controller;

import com.snooze.api.snooze.ApiConnector;
import com.snooze.api.snooze.SnoozeUsersService;
import com.snooze.api.snooze.inc.SnoozeUsers;
import com.snooze.model.snooze.User;
import com.snooze.model.snooze.service.UserService;

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
    private String userAccessToken;
    private DataInterface mListener;

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

                if (response!=null && response.body() != null && mListener != null) {
                    mListener.responseData(response.message());
                    createUserAccessToken();
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

    public void createUserAccessToken(){
        System.out.println("should create user acc token");
    }

    public void setOnDataListener(DataInterface listener) {
        mListener = listener;
    }

    public interface DataInterface {
        void responseData( String myResponse );
    }


}
