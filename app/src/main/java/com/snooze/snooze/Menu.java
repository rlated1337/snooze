package com.snooze.snooze;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.snooze.api.snooze.inc.Session;

import org.json.JSONObject;

public class Menu extends AppCompatActivity {
    private Button btnBack;
    private Button btnMaps;
    private Button btnAccount;
    private Button btnBookings;
    private Button btnSettings;
    private Session session;
    private JSONObject json_object;

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
                switchScreensWithoutObject(AboutSnooze.class);
            }
        });
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreensWithoutObject(Maps.class);
            }
        });
        btnBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreensWithoutObject(Bookings.class);
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreensWithoutObject(Account.class);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreensWithoutObject(Settings.class);
            }
        });

        System.out.println(getIntent().getStringExtra("ACC_TOKEN"));
/*
        try {
            json_object = new JSONObject(getIntent().getStringExtra("ACC_TOKEN"));
            System.out.println(json_object.getString("id"));
            System.out.println(json_object.getString("userId"));
            System.out.println(json_object.getString("username"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        */





    }
    public void switchScreensWithObject(Class s, JSONObject obj)
    {
        Intent i = new Intent(this,s);
        i.putExtra("ACC_TOKEN", obj.toString());
        startActivity(i);

    }
    public void switchScreensWithoutObject(Class s)
    {
        Intent i = new Intent(this,s);
        startActivity(i);

    }
}
