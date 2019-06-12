package com.example.snooze;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SnoozeUsersService {

    /* GENERAL USER GETTING */
    @GET("SnoozeUsers")
    Call<List<SnoozeUsers>> getAllUsers(@Query("access_token") String key);

    @POST("SnoozeUsers")
    Call<SnoozeUsers> postNewUser(@Query("access_token") String key, @Body SnoozeUsers jsonUser);


    /* USER GETTING/PATCHING/DELETING {ID} */
    @GET("SnoozeUsers/{id}")
    Call<SnoozeUsers> getSpecificUser(@Path("id") String id, @Query("access_token") String key);

    @PUT("SnoozeUsers/{id}")
    Call<SnoozeUsers> patchSpecificUser(@Query("access_token") String key, @Path("id") String id, @Query("data") String jsonData);

    @DELETE("SnoozeUsers/{id}")
    Call<SnoozeUsers> deleteSpecificUser(@Query("access_token") String key, @Path("id") String id);


    /* ACCESS TOKEN RELATED STUFF GET/POST/DELETE/COUNT */
    @GET("SnoozeUsers/{id}/accessTokens")
    Call<List<SnoozeUsers>> getAccessToken(@Query("access_token") String key, @Path("id") String id);

    @POST("SnoozeUsers/{id}/accessTokens")
    Call<POST> postAccessToken(@Query("access_token") String key, @Path("id") String id, @Query("data") String jsonData);

    @DELETE("SnoozeUsers/{id}/accessTokens")
    Call<List<SnoozeUsers>> deleteAccessToken(@Query("access_token") String key, @Path("id") String id);

    @GET("SnoozeUsers/{id}/accessTokens/count")
    Call<List<SnoozeUsers>> countAccessToken(@Query("access_token") String key, @Path("id") String id);


    /**/



}
