package com.example.snooze;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SnoozeUsersService {

    @GET("SnoozeUsers")
    Call<List<SnoozeUsers>> getAllUsers(@Query("access_token") String key);

    @POST("SnoozeUsers")
    Call<List<SnoozeUsers>> postNewUser(@Query("access_token") String key, @Query("data") String jsonData);


}