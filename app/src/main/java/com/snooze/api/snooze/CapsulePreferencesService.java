package com.snooze.api.snooze;

import com.snooze.api.snooze.inc.CapsulePreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CapsulePreferencesService {

    @GET("CapsulePreferences")
    Call<List<CapsulePreferences>> getAllCapsulePreferences(@Query("access_token") String key);

    @POST("CapsulePreferences")
    Call<String> postCapsulePreferences(@Query("access_token") String key, @Body CapsulePreferences capsulePreferences);

    @GET("CapsulePreferences/{id}")
    Call<String> getSpecificPreferences (@Path("id") String id, @Query("access_token") String key);

    @PATCH("CapsulePreferences/{id}")
    Call<String> patchSpecificPreferences (@Path("id") String id, @Query("access_token") String key, @Body CapsulePreferences capsulePreferences);

    @PUT("CapsulePreferences/{id}")
    Call<String> putSpecificPreferences (@Path("id") String id, @Query("access_token") String key, @Body CapsulePreferences capsulePreferences);



}
