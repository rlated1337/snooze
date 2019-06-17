package com.snooze.model.snooze.controller;

import com.snooze.api.snooze.ApiConnector;
import com.snooze.api.snooze.SnoozeUsersService;
import com.snooze.api.snooze.inc.Credentials;
import com.snooze.api.snooze.inc.Session;
import com.snooze.api.snooze.inc.SnoozeUsers;
import com.snooze.model.snooze.User;
import com.snooze.model.snooze.service.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;

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
    private static final String accessToken = "GN0tME3nUBa6auETCDju80cAzMSMDaDY791UafudXydp6AwwLfVjEJDDxJTjHEg3";
    private String userAccessToken;
    private DataInterface mListener;

    public UserController() {
        /* CONNECTION TO API */
        connect = new ApiConnector();
        retrofit = connect.getRetrofitInstance();
        service = retrofit.create(SnoozeUsersService.class);
    }

    public void register(String username, String email, String password){
        SnoozeUsers snoozeUser = new SnoozeUsers("FH",username,email,false ,password);

        Call<SnoozeUsers> call = service.postNewUser(accessToken,snoozeUser);

        call.enqueue(new Callback<SnoozeUsers>() {
            @Override
            public void onResponse(Call<SnoozeUsers> call, Response<SnoozeUsers> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }

                if (response!=null && response.body() != null && mListener != null) {

                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("userId", response.body().getId());
                        obj.put("realm", response.body().getRealm());
                        obj.put("username", response.body().getUsername());
                        obj.put("email", response.body().getEmail());
                        obj.put("emailVerified", response.body().getEmailVerified());
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }

                    mListener.responseData(obj);



                }



            }

            @Override
            public void onFailure(Call<SnoozeUsers> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });

    }

    public void login(String email, String password){
        String username = "";

        if(email.contains("@stud.fra-uas.de")){
            // WILL PER EMAIL ANMELDEN
            username = email;
        }
        else {
            // WILL PER USERNAME ANMELDEN
            username = email;
            email = "";
        }

        Credentials creds = new Credentials(email, username, password);

        final String respUsername = username;
        final String respPassword = password;

        Call<Session> call = service.login(accessToken,creds);

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }

                if (response!=null && response.body() != null && mListener != null) {
                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("id", response.body().getId());
                        obj.put("ttl", response.body().getTtl());
                        obj.put("created", response.body().getCreated());
                        obj.put("userId", response.body().getUserId());
                        obj.put("username", respUsername);
                        obj.put("password", respPassword);


                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }

                    mListener.responseData(obj);

                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }

    public void executePayment(){

    }

    public void userLogin(){

    }

    public void userLockOut(){

    }

    public void setOnDataListener(DataInterface listener) {
        mListener = listener;
    }

    public interface DataInterface {
        void responseData( JSONObject myResponse );
    }


}
