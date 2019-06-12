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
    Call<SnoozeUsers> postNewUser(@Query("access_token") String key, @Body SnoozeUsers user);

    /* USER GETTING/PATCHING/DELETING {ID} */
    @GET("SnoozeUsers/{id}")
    Call<List<SnoozeUsers>> getSpecificUser(@Path("id") String id, @Query("access_token") String key);

    @PUT("SnoozeUsers/{id}")
    Call<SnoozeUsers> patchSpecificUser(@Path("id") String id, @Query("access_token") String key, @Body SnoozeUsers user);

    @DELETE("SnoozeUsers/{id}")
    Call<SnoozeUsers> deleteSpecificUser(@Path("id") String id, @Query("access_token") String key);

    /* ACCESS TOKEN RELATED STUFF GET/POST/DELETE/COUNT */
    @GET("SnoozeUsers/{id}/accessTokens")
    Call<List<SnoozeUsers>> getAccessToken(@Path("id") String id, @Query("access_token") String key);

    @POST("SnoozeUsers/{id}/accessTokens")
    Call<POST> postAccessToken(@Path("id") String id, @Query("access_token") String key, @Query("data") String jsonData);

    @DELETE("SnoozeUsers/{id}/accessTokens")
    Call<SnoozeUsers> deleteAccessToken(@Path("id") String id, @Query("access_token") String key);

    @GET("SnoozeUsers/{id}/accessTokens/count")
    Call<List<SnoozeUsers>> countAccessToken(@Path("id") String id, @Query("access_token") String key);


    /* SNOOZEUSER BOOKINGS */
    @GET("SnoozeUsers/{id}/bookings")
    Call<List<SnoozeUsers>> getBookings(@Path("id") String id, @Query("access_token") String key);

    @POST("SnoozeUsers/{id}/bookings")
    Call<POST> placeBooking(@Path("id") String id, @Query("access_token") String key, @Body Bookings booking);

    @DELETE("SnoozeUsers/{id}/bookings")
    Call<DELETE> deleteAllBookings(@Path("id") String id, @Query("access_token") String key);

}
