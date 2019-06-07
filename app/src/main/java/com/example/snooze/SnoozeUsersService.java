package com.example.snooze;

import retrofit2.http.GET;

public interface SnoozeUsersService {

    @GET("/SnoozeUsers")
    Call<List<SnoozeUsers>> getAllUsers();

}
