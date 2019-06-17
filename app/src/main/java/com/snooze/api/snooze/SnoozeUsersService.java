package com.snooze.api.snooze;

import com.snooze.api.snooze.inc.Bookings;
import com.snooze.api.snooze.inc.Credentials;
import com.snooze.api.snooze.inc.SnoozeUsers;
import com.snooze.api.snooze.inc.CapsulePreferences;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
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
    Call<SnoozeUsers> updateSpecificUser(@Path("id") String id, @Query("access_token") String key, @Body SnoozeUsers user);

    @DELETE("SnoozeUsers/{id}")
    Call<Void> deleteSpecificUser(@Path("id") String id, @Query("access_token") String key);

    /* ACCESS TOKEN RELATED STUFF GET/POST/DELETE/COUNT */
    @GET("SnoozeUsers/{id}/accessTokens")
    Call<List<SnoozeUsers>> getAccessToken(@Path("id") String id, @Query("access_token") String key);

    @POST("SnoozeUsers/{id}/accessTokens")
    Call<SnoozeUsers> postAccessToken(@Path("id") String id, @Query("access_token") String key, @Query("data") String jsonData);

    @DELETE("SnoozeUsers/{id}/accessTokens")
    Call<Void> deleteAccessToken(@Path("id") String id, @Query("access_token") String key);

    @GET("SnoozeUsers/{id}/accessTokens/count")
    Call<List<SnoozeUsers>> countAccessToken(@Path("id") String id, @Query("access_token") String key);


    /* SNOOZEUSER BOOKINGS */
    @GET("SnoozeUsers/{id}/bookings")
    Call<List<SnoozeUsers>> getBookings(@Path("id") String id, @Query("access_token") String key);

    @POST("SnoozeUsers/{id}/bookings")
    Call<SnoozeUsers> placeBooking(@Path("id") String id, @Query("access_token") String key, @Body Bookings booking);

    @DELETE("SnoozeUsers/{id}/bookings")
    Call<Void> deleteAllBookings(@Path("id") String id, @Query("access_token") String key);

    @GET("SnoozeUsers/{id}/bookings/count")
    Call<List<SnoozeUsers>> countBookings(@Path("id") String id, @Query("access_token") String key);


    /* PREFERENCES */
    @GET("SnoozeUsers/{id}/capsulePreference")
    Call<List<SnoozeUsers>> getCapsulePreference(@Path("id") String id, @Query("access_token") String key);

    @PUT("SnoozeUsers/{id}/capsulePreference")
    Call<SnoozeUsers> updateCapsulePreferences(@Path("id") String id, @Query("access_token") String key, @Body CapsulePreferences capsulePreferences);

    @POST("SnoozeUsers/{id}/capsulePreference")
    Call<SnoozeUsers>  postCapsulePreferences(@Path("id") String id, @Query("access_token") String key, @Body CapsulePreferences capsulePreferences);

    @DELETE("SnoozeUsers/{id}/capsulePreference")
    Call<Void> deleteCapsulePreferences(@Path("id") String id, @Query("access_token") String key);


    /* GERNEAL */
    @GET("SnoozeUsers/{id}/exists")
    Call<List<SnoozeUsers>> checkExistanceUser(@Path("id") String id, @Query("access_token") String key);

    @POST("SnoozeUsers/{id}/verify")
    Call<SnoozeUsers> verifyUser(@Path("id") String id, @Query("access_token") String key);

    @POST("SnoozeUsers/change-password")
    Call<SnoozeUsers> changePassword(@Query("access_token") String key, @Query("old_pw") String oldPW, @Query("new_pw") String newPW);

    @GET("SnoozeUsers/change-stream")
    Call<List<SnoozeUsers>> getStream(@Query("access_token") String key, @Query("options") String options);

    @POST("SnoozeUsers/change-stream")
    Call<SnoozeUsers> postStream(@Query("access_token") String key, @Query("options") String options);

    @GET("SnoozeUsers/confirm")
    Call<List<SnoozeUsers>> confirmRegistration(@Query("uid") String uid, @Query("token") String token, @Query("access_token") String key);

    @GET("SnoozeUsers/GetUserData")
    Call<List<SnoozeUsers>> getUserData(@Query("access_token") String key);

    /* LOGIN / LOGOUT / RESET / PW */
    @POST("SnoozeUsers/login")
    Call<SnoozeUsers> login(@Query("access_token") String key, @Body Credentials creds);

    @POST("SnoozeUsers/logout")
    Call<SnoozeUsers> logout(@Query("access_token") String key);
}
