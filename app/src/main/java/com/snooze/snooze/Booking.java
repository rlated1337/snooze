package com.snooze.snooze;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import com.snooze.api.snooze.inc.Bookings;
import com.snooze.api.snooze.inc.SnoozeUsers;
import com.snooze.model.snooze.controller.AppController;
import com.snooze.model.snooze.controller.UserController;

import org.json.JSONException;
import org.json.JSONObject;



import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Booking extends AppCompatActivity {
    private Button btn_back;

    private UserController uController;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private BookingsAdapter mAdapter;
    private ArrayList<Bookings> mBookings;
    private List<String> listAmount = new ArrayList<>();
    private List<String> listDate = new ArrayList<>();
    private List<String> listPeriod = new ArrayList<>();
    private List<String> listName = new ArrayList<>();
    private List<String> listPin = new ArrayList<>();
    private List<String> listTime = new ArrayList<>();
    private SparseArray timeFrameTranslation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        btn_back = findViewById(R.id.btn_Bookings_back);
        mRecyclerView = findViewById(R.id.RecyclerView_Bookings);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        uController = MainActivity.getInstance().getuController();
        initTimeFrameMap();
        mRecyclerView = findViewById(R.id.RecyclerView_Bookings);
        mBookings = new ArrayList<>();

        uController.getUserData();

        uController.setOnBookingListener(new UserController.DataInterfaceBookings() {
            @Override
            public void responseBookings(JsonElement myBookings) {



                setBookings(myBookings.toString());
                buildRecyclerView();
            }
        });





    }
    public void buildRecyclerView(){
    mAdapter = new BookingsAdapter(listAmount,listDate,listPeriod,listName,this,listPin,listTime);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void setBookings(String jsonString) {

        JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("bookings");




        for(int i=0;i<jarray.size();i++){
            int firstTimeFrame,lastTimeFrame;
            String pin;
            int countBookings;
            int period=0;
            String resultAmount,resultDate,resultName;
            JsonObject jsonObject = jarray.get(i).getAsJsonObject();
            resultAmount = jsonObject.get("PayedAmount").toString();
            listAmount.add(i,resultAmount+" € ");
            resultDate = jsonObject.get("Date").toString();
            listDate.add(i,resultDate.substring(1, resultDate.indexOf('T')));
            firstTimeFrame = Integer.parseInt(jsonObject.get("FirstTimeFrame").toString());
            lastTimeFrame = Integer.parseInt(jsonObject.get("LastTimeFrame").toString());
            pin = jsonObject.get("Pin").toString();
            listPin.add(pin);
            JsonObject capsuleObject = jsonObject.getAsJsonObject("capsule");
            resultName = capsuleObject.get("Name").toString();
            resultName.substring(1);
            removeLastChar(resultName);

            listName.add(i,resultName);
            System.out.println(resultName);


            listTime.add(timeFrameTranslation.get(firstTimeFrame).toString());



            listPeriod.add(i,"20" + " minutes");

        }
    }

    private void initTimeFrameMap() {
        timeFrameTranslation = new SparseArray<>();

        timeFrameTranslation.append(1, "09.00 bis 09.20 Uhr");
        timeFrameTranslation.append(2, "09.20 bis 09.40 Uhr");
        timeFrameTranslation.append(3, "09.40 bis 10.00Uhr");

        timeFrameTranslation.append(4, "10.00 bis 10.20 Uhr");
        timeFrameTranslation.append(5, "10.20 bis 10.40 Uhr");
        timeFrameTranslation.append(6, "10.40 bis 11.00 Uhr");

        timeFrameTranslation.append(7, "11.00 bis 11.20 Uhr");
        timeFrameTranslation.append(8, "11.20 bis 11.40 Uhr");
        timeFrameTranslation.append(9, "11.40 bis 12.00 Uhr");

        timeFrameTranslation.append(10, "12.00 bis 12.20 Uhr");
        timeFrameTranslation.append(11, "12.20 bis 12.40 Uhr");
        timeFrameTranslation.append(12, "12.40 bis 13.00 Uhr");

        timeFrameTranslation.append(13, "13.00 bis 13.20 Uhr");
        timeFrameTranslation.append(14, "13.20 bis 13.40 Uhr");
        timeFrameTranslation.append(15, "13.40 bis 14.00 Uhr");

        timeFrameTranslation.append(16, "14.00 bis 14.20 Uhr");
        timeFrameTranslation.append(17, "14.20 bis 14.40 Uhr");
        timeFrameTranslation.append(18, "14.40 bis 15.00 Uhr");

        timeFrameTranslation.append(19, "15.00 bis 15.20 Uhr");
        timeFrameTranslation.append(20, "15.20 bis 15.40 Uhr");
        timeFrameTranslation.append(21, "15.40 bis 16.00 Uhr");

        timeFrameTranslation.append(22, "16.00 bis 16.20 Uhr");
        timeFrameTranslation.append(23, "16.20 bis 16.40 Uhr");
        timeFrameTranslation.append(24, "16.40 bis 17.00 Uhr");

        timeFrameTranslation.append(25, "17.00 bis 17.20 Uhr");
        timeFrameTranslation.append(26, "17.20 bis 17.40 Uhr");
        timeFrameTranslation.append(27, "17.40 bis 18.00 Uhr");

        Log.d("Time Frame Translation", timeFrameTranslation.toString());
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}


