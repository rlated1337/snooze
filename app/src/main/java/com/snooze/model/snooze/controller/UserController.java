package com.snooze.model.snooze.controller;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.snooze.api.snooze.ApiConnector;
import com.snooze.api.snooze.BookingService;
import com.snooze.api.snooze.CapsulePreferencesService;
import com.snooze.api.snooze.SnoozeUsersService;
import com.snooze.api.snooze.inc.Bookings;
import com.snooze.api.snooze.inc.CapsulePreferences;
import com.snooze.api.snooze.inc.Credentials;
import com.snooze.api.snooze.inc.Session;
import com.snooze.api.snooze.inc.SnoozeUsers;
import com.snooze.model.snooze.User;
import com.snooze.model.snooze.service.UserService;
import com.snooze.snooze.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserController {
    private SnoozeUsers sUser;
    private UserService userservice;
    private User user;
    private Retrofit retrofit;
    private SnoozeUsersService service;
    private CapsulePreferencesService capsulePreferencesService;
    private ApiConnector connect;
    private BookingService bookingService;
    private String userAccessToken;
    private DataInterface mListener;
    private DataInterfaceBookings bListener;
    private AppController aController;
    private HashMap<String, String> pinCapsule;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    private Integer userID;

    public UserController() {
        /* CONNECTION TO API */
        connect = new ApiConnector();
        retrofit = connect.getRetrofitInstance();
        service = retrofit.create(SnoozeUsersService.class);
        capsulePreferencesService = retrofit.create(CapsulePreferencesService.class);
        aController =  MainActivity.getInstance().getaController();
        bookingService = retrofit.create(BookingService.class);
        aController =  MainActivity.getInstance().getaController();
        pinCapsule = new HashMap<>();

    }

    public void register(String username, String email, String password){
        SnoozeUsers snoozeUser = new SnoozeUsers("FH",username,email,false ,password);

        Call<SnoozeUsers> call = service.postNewUser(userAccessToken,snoozeUser);

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

        Call<Session> call = service.login(userAccessToken,creds);

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if(!response.isSuccessful()){

                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());

                    JSONObject obj = new JSONObject();
                    mListener.responseData(obj);
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
                    setUserAccessToken(response.body().getId());
                    setUserID(Integer.parseInt(response.body().getUserId()));
                    System.out.println("ACCESS TOKEN: " + getUserAccessToken());
                }

            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                System.out.println(t.getMessage());

                JSONObject obj = new JSONObject();
                mListener.responseData(obj);

            }
        });
    }

    public void getUserData() {
        System.out.println("GET USERDATA");
        System.out.println(userAccessToken);

        Call<JsonElement> call = service.getUserData(userAccessToken);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }

                if (response!=null && response.body() != null && mListener != null) {


                    System.out.println(response.body());

                    JsonElement element = response.body();

                    JsonObject obj = element.getAsJsonObject();

//                    pinCapsule.put(obj.get("Capsule_id").toString(), obj.get("pin").toString());

                    bListener.responseBookings(response.body());

                }



            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });

    }

    public void changePassword(String oldPw,String newPw)
    {
        Call<SnoozeUsers> call = service.changePassword(userAccessToken,oldPw,newPw);

        call.enqueue(new Callback<SnoozeUsers>() {
            @Override
            public void onResponse(Call<SnoozeUsers> call, Response<SnoozeUsers> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }

                if (response!=null && response.body() != null && mListener != null) {


                    System.out.println(response.body());


                }

            }

            @Override
            public void onFailure(Call<SnoozeUsers> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }

    public void placeBooking(String successPaypal, String paymentID, Integer capsuleID, Integer startTimeFrame, Integer endTimeFrame){
        System.out.println("PLACE BOOKINGS");

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        System.out.println(nowAsISO);

        Bookings booking = new Bookings(this.getUserID(),capsuleID,"string", nowAsISO, startTimeFrame, endTimeFrame, "fh", (endTimeFrame - startTimeFrame) + 1, true, "mobapptest@gmail.com", 2, nowAsISO, paymentID);

        Log.d("Booking Object", booking.toString());
        Log.d("Booking Date", booking.getDate());
        Log.d("Access Token", userAccessToken);

        Call<JsonElement> call = bookingService.postBooking(userAccessToken, booking);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }

                if (response!=null && response.body() != null && mListener != null) {


                    System.out.println(response.body());
                    //bListener.responseBookings(response.body());

                }



            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }


    public void userLogOut(){

    }

    public void setOnDataListener(DataInterface listener) {
        mListener = listener;
    }

    public void setOnBookingListener(DataInterfaceBookings listener) {
        bListener = listener;
    }

    public interface DataInterface {
        void responseData( JSONObject myResponse );
    }

    public interface DataInterfaceBookings {
        void responseBookings(JsonElement myBookings);
    }


    public String getUserAccessToken() {
        return userAccessToken;
    }

    public void setUserAccessToken(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public void setCapsulePreferences(Integer bedLegAngle, Integer bedBackAngle, Integer lightLevel,
                                     Integer volumenLevel, String lightColor, Integer bedMidAngle){

        CapsulePreferences capsulePreferences = new CapsulePreferences(this.getUserID(),bedLegAngle,bedBackAngle,lightLevel,
                volumenLevel,lightColor,bedMidAngle);

        Call<CapsulePreferences> call = capsulePreferencesService.patchCapsulePreferences(userAccessToken,capsulePreferences);

        call.enqueue(new Callback<CapsulePreferences>() {
            @Override
            public void onResponse(Call<CapsulePreferences> call, Response<CapsulePreferences> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }

                if (response!=null && response.body() != null && mListener != null) {


                    System.out.println(response.body());
                    //bListener.responseBookings(response.body());

                }

            }

            @Override
            public void onFailure(Call<CapsulePreferences> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void getCapsulePreferencesById(){
        Call<JsonElement> call = service.getUserData(userAccessToken);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Message: " + response.message());
                }

                if (response!=null && response.body() != null && mListener != null) {
                    System.out.println(response.body());
                    bListener.responseBookings(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

    }


}
