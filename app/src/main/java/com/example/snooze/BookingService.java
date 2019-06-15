package com.example.snooze;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookingService {

    /* BOOKINGS GET/POST/PUT/PATCH */

    @GET("Bookings")
    Call<List<Bookings>> getAllBookings(@Query("access_token") String key);

    @POST("Bookings")
    Call<Bookings> postBooking(@Query("access_token") String key, @Body Bookings booking);

    @PUT("Bookings")
    Call<Bookings> putBooking(@Query("access_token") String key, @Body Bookings booking);

    @PATCH("Bookings")
    Call<Bookings> patchBooking(@Query("access_token") String key, @Body Bookings booking);

    /* BOOKINGS{id} GET/PUT/PATCH/DELETE */

    @GET("Bookings/{id}")
    Call<Bookings> getSpecificBooking(@Path("id") String id , @Query("access_token") String key);

    @PUT("Bookings/{id}")
    Call<Bookings> putSpecificBooking(@Path("id") String id, @Query("access_token") String key, @Body Bookings booking);

    @PATCH("Bookings/{id}")
    Call<Bookings> patchSpecificBooking(@Path("id") String id, @Query("access_token") String key, @Body Bookings booking);

    @DELETE("Bookings/{id}")
    Call<Void> deleteSpecificBooking(@Path("id") String id, @Query("access_token") String key);

    /* BOOKINGS{id}/.... GET/POST */

    //@GET("Booking/{id}/capsule")


    @GET("Bookings/{id}/exists")
    Call<Boolean> getBookingExists(@Path("id") String id, @Query("access_token") String key);

    @POST("Bookings/{id}/extend")
    Call<Bookings> postExtendBooking(@Path("id") String id, @Query("access_token") String key, @Body Bookings booking);

    /* BOOKINGS{id} SNOOZEUSER */

    @GET("Bookings/{id}/snoozeUser")
    Call<List<Bookings>> getSnoozUsers(@Path("id") String id, @Query("access_token") String key);

    /* BOOKINGS/COUNT */

    @GET("Bookings/count")
    Call<Integer> getCount(@Query("access_token") String key);

    /* BOOKINGS/FINDONE */

    @GET("Bookings/findOne")
    Call<Bookings> getOneBooking(@Query("access_token") String key);








}
