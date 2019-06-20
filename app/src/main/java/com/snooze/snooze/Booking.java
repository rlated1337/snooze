package com.snooze.snooze;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.snooze.api.snooze.inc.Bookings;
import com.snooze.api.snooze.inc.SnoozeUsers;
import com.snooze.model.snooze.controller.AppController;
import com.snooze.model.snooze.controller.UserController;

import org.json.JSONObject;

import java.text.DateFormat;
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


                //buildRecyclerView();
                getBookings(myBookings.toString());
            }
        });





    }
    public void printBookingsList(){

    }
    public void buildRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new BookingsAdapter(mBookings);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new BookingsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

    }
    public void getBookings(String jsonString) {

        JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray jarray = jobject.getAsJsonArray("bookings");
        String resultAmount,resultDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");



        for(int i=0;i<jarray.size();i++){
            JsonObject jsonObject = jarray.get(i).getAsJsonObject();
            resultAmount = jsonObject.get("PayedAmount").toString();
            listAmount.add(i,resultAmount);
            resultDate = jsonObject.get("Date").toString();
            String text = resultDate.format(formatter);
            System.out.println(text);
        }
    }
}


