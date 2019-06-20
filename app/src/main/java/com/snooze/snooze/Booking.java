package com.snooze.snooze;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.snooze.model.snooze.controller.AppController;

import java.util.ArrayList;
import java.util.List;

public class Booking extends AppCompatActivity {
    private Button btn_back;
    private Button btn_history;
    private ScrollView sView_List;
    private AppController aController;
    private List<com.snooze.api.snooze.inc.Bookings> listBookings = new ArrayList<com.snooze.api.snooze.inc.Bookings>();

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
        aController = MainActivity.getInstance().getaController();

        /*
        aController.getBookings();

        aController.setBookingListener(new AppController.DataInterfaceBookings() {
            @Override
            public void responseBookings(List<com.snooze.api.snooze.inc.Bookings> myBookings) {
                System.out.println(myBookings);
            }
        });
        */




    }
    public void printBookingsList(){
        for(com.snooze.api.snooze.inc.Bookings booking : listBookings){
            String content = "";
            content += "Name: " + booking.getDate() + "\n";
            content += "Preis: " + booking.getAmount()  + "\n";
            content += "________________________" + "\n";
            System.out.println(content);
        }
    }
}


