package com.example.snooze;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnector {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://platania.info:3000/api/SnoozeUsersService/";
    private static final String accessToken = "TCmDmaQGQt76PArgLl6BbAaEsgQvPX3vqwG82Pzq4DXujexKguwnXXwghGgKQVsF";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }



}



