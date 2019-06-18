package com.snooze.model.snooze.controller;

import com.snooze.api.snooze.ApiConnector;
import com.snooze.api.snooze.CapsulePreferencesService;
import com.snooze.api.snooze.CapsuleService;
import com.snooze.api.snooze.inc.Capsules;
import com.snooze.snooze.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppController {
    private UserController usercontroller;
    private Retrofit retrofit;
    private ApiConnector connect;
    private CapsuleService service;
    private String accessToken = "";



    public AppController() {
        connect = new ApiConnector();
        retrofit = connect.getRetrofitInstance();
        service = retrofit.create(CapsuleService.class);

        usercontroller =  MainActivity.getInstance().getuController();

        accessToken = usercontroller.getUserAccessToken();
        System.out.println("ACCESS TOKEN2: " + accessToken);
    }

    public void showCapsules(){

        Call<List<Capsules>> call = service.getAllCapsules(accessToken);

        call.enqueue(new Callback<List<Capsules>>() {
            @Override
            public void onResponse(Call<List<Capsules>> call, Response<List<Capsules>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }

                List<Capsules> capsules = response.body();

                for(Capsules capsule : capsules){
                    String content = "";
                    content += "Name: " + capsule.getName() + "\n";
                    content += "Latitude: " + capsule.getLatitude() + "\n";
                    content += "Longitude: " + capsule.getLongitude() + "\n";
                    content += "IP-Address: " + capsule.getIpAddress() + "\n";
                    content += "Price" + capsule.getPrice() + "\n";
                    content += "-----------" + "\n";

                    System.out.println(content);


                }

            }

            @Override
            public void onFailure(Call<List<Capsules>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void showCalender(){

    }

    public void verifyBooking(){

    }

    public void payment(){

    }

    public void sendMailOnUser(){

    }

    public void generateDoorCode(){

    }

    public void sendDataToRaspberryPi(){

    }

    public void showView(){

    }
}
