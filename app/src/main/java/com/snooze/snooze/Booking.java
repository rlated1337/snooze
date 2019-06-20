package com.snooze.snooze;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.snooze.api.snooze.inc.SnoozeUsers;
import com.snooze.model.snooze.controller.AppController;
import com.snooze.model.snooze.controller.UserController;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Booking extends AppCompatActivity {
    private Button btn_back;
    private Button btn_history;
    private ScrollView sView_List;
    private UserController uController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        btn_back = findViewById(R.id.btn_Bookings_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sView_List = findViewById(R.id.sView_Bookings_BookingList);
        uController = MainActivity.getInstance().getuController();


        uController.getBookings();
        uController.setOnBookingListener(new UserController.DataInterfaceBookings() {
            @Override
            public void responseBookings(JSONObject myBookings) {
                System.out.println(myBookings);
            }
        });





    }
    public void printBookingsList(){

    }
}


