package com.snooze.snooze;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.snooze.api.snooze.inc.Capsules;
import com.snooze.model.snooze.controller.AppController;
import com.snooze.model.snooze.controller.UserController;

import java.util.ArrayList;
import java.util.List;


public class Maps extends AppCompatActivity implements
        OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    private GoogleMap mMap;
    private Button btnBack;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code =99;
    private AppController aController;
    private List<Capsules> listCapsules = new ArrayList<Capsules>();
    private GridView textView4;
    private ScrollView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        aController = MainActivity.getInstance().getaController();
        textView4 = findViewById(R.id.textView4);

        System.out.println(aController);

        showCapsuleList();

        aController.setOnDataListener(new AppController.DataInterface2() {
            @Override
            public void responseData(List<Capsules> capsules) {
                System.out.println(capsules);
                listCapsules = capsules;
                if(capsules.get(0) != null){
                    Toast.makeText(Maps.this, "Success", Toast.LENGTH_SHORT).show();
                }
                printCapsuleList();
            }
        });

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        tb.setSubtitle("Your Location");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        //mMap.setMinZoomPreference(11);

        // define point to center on

        LatLng origin = new LatLng(50.1299187, 8.6923254);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 15));
    }

    public void showCapsuleList(){
        aController.showCapsules();
    }
    public void printCapsuleList(){
        for(Capsules capsule : listCapsules){
            String content = "";
            content += "Name: " + capsule.getName() + "\n";
            content += capsule.getIpAddress() + "\n";
            content += "Preis: " + capsule.getPrice() + "\n";
            content += "________________________" + "\n";

           // textView4.append(content);

            // Creating a marker
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng lng = new LatLng(capsule.getLatitude(), capsule.getLongitude());
            // Setting the position for the marker
            markerOptions.position(lng);

            // Placing a marker on the touched position
            mMap.addMarker(markerOptions);
        }
    }

}