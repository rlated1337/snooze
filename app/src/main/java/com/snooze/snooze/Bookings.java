package com.snooze.snooze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import java.util.List;

public class Bookings extends AppCompatActivity {
    private Button btn_back;
    private ScrollView sView_List;
    private List<String> sList_History;

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


    }
}


