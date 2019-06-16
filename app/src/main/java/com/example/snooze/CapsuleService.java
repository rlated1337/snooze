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

public interface CapsuleService {

    /* CAPSULES GET/POST/PUT/PATCH */

    @GET("Capsules")
    Call<List<Capsules>> getAllCapsules(@Query("access_token") String key);

    @POST("Capsules")
    Call<Capsules> postCapsule(@Query("access_token") String key, @Body Capsules capsule);

    @PUT("Capsules")
    Call<Capsules> putCapsule(@Query("access_token") String key, @Body Capsules capsule);

    @PATCH("Capsules")
    Call<Capsules> patchCapsule(@Query("access_token") String key, @Body Capsules capsule);

    /* CAPSULES/{id} GET/PUT/PATCH/DELETE */

    @GET("Capsules/{id}")
    Call<Capsules> getSpecificCapsule(@Path("id") String id, @Query("access_token") String key);

    @PUT("Capsules/{id}")
    Call<Capsules> putSpecificCapsule(@Path("id") String id, @Query("access_token") String key, @Body Capsules capsule);

    @PATCH("Capsules/{id}")
    Call<Capsules> patchSpecificCapsule(@Path("id") String id, @Query("access_token") String key, @Body Capsules capsule);

    @DELETE("Capsules/{id}")
    Call<Void> deleteSpecificCapsule(@Path("id") String id, @Query("access_token") String key);

    /* CAPSULES/{id}/BOOKINGS */

    @GET("Capsules/{id}/bookings")
    Call<List<Bookings>> getSpecificBookingFromCapsule(@Path("id") String id, @Query("access_token") String key);

    @POST("Capsules/{id}/bookings")
    Call<Bookings> postSpecificBookingFromCapsule(@Path("id") String id, @Query("access_token") String key, @Body Bookings booking);

    @DELETE("Capsules/{id}/bookings")
    Call<Void> deleteBookingFromCapsule(@Path("id") String id, @Query("access_token") String key);

    @GET("Capsules/{id}/bookings/count")
    Call<Integer> getBookingsofCapsule(@Path("id") String id, @Query("access_token") String key);

    @GET("Capsules/{id}/exists")
    Call<Boolean> getCapsuleExists(@Path("id") String id, @Query("access_token") String key);

    @POST("Capsules/{id}/replace")
    Call<Capsules> postReplaceCapsule(@Path("id") String id, @Query("access_token") String key, @Body Capsules capsule);





}
