package com.snooze.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button btnBack;
    private Button btnMaps;
    private Button btnAccount;
    private Button btnBookings;
    private Button btnSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnBack = findViewById(R.id.btn_menu_back);
        btnMaps = findViewById(R.id.btn_menu_maps);
        btnAccount = findViewById(R.id.btn_menu_account);
        btnBookings = findViewById(R.id.btn_menu_bookings);
        btnSettings = findViewById(R.id.btn_menu_Settings);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreens(AboutSnooze.class);
            }
        });
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreens(Maps.class);
            }
        });


    }
    public void switchScreens(Class s)
    {
        Intent i = new Intent(this,s);

        startActivity(i);

    }
}
