package com.snooze.snooze;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        mRecyclerView = findViewById(R.id.RecyclerView_Bookings);
        mBookings = new ArrayList<>();

        uController.getBookings();

        uController.setOnBookingListener(new UserController.DataInterfaceBookings() {
            @Override
            public void responseBookings(JsonElement myBookings) {



                setBookings(myBookings.toString());
                buildRecyclerView();
            }
        });





    }
    public void buildRecyclerView(){
    mAdapter = new BookingsAdapter(listAmount,listDate,listPeriod,listName,this);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void setBookings(String jsonString) {

        JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("bookings");




        for(int i=0;i<jarray.size();i++){
            int firstTimeFrame,lastTimeFrame;
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

            JsonObject capsuleObject = jsonObject.getAsJsonObject("capsule");
            resultName = capsuleObject.get("Name").toString();
            resultName.substring(1);
            removeLastChar(resultName);

            listName.add(i,resultName);
            System.out.println(resultName);


            if (firstTimeFrame == lastTimeFrame)
             {
                countBookings = firstTimeFrame;
             }
            else
            {
                countBookings = lastTimeFrame-firstTimeFrame;
            }
            while(countBookings>0)
            {
                period = period + 20;
                countBookings--;
            }
            listPeriod.add(i,period + " minutes");

        }
    }


    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}


