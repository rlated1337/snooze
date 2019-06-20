package com.snooze.snooze;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.snooze.api.snooze.inc.Capsules;
import com.snooze.model.snooze.controller.AppController;

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
    private List<Capsules> listCapsules = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CapsuleAdapter mAdapter;
    private ArrayList<Capsules> mCapsule;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        aController = MainActivity.getInstance().getaController();
        //textView4 = findViewById(R.id.textView4);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mCapsule = new ArrayList<>();

        System.out.println(aController);


        showCapsuleList();



        aController.setOnDataListener(new AppController.DataInterface2() {
            @Override
            public void responseData(List<Capsules> capsules) {
                System.out.println(capsules);
                if(capsules.get(0) != null) {
                    Toast.makeText(Maps.this, "Success", Toast.LENGTH_SHORT).show();
                    listCapsules = capsules;
                    System.out.println("CAPSULE AMOUNT: " + listCapsules.size());
                    System.out.println("TEST: " + listCapsules);
                }
                System.out.println(listCapsules.get(1).getName());
                printCapsuleList();
                buildRecyclerView();
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

        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        mMap.setOnMyLocationClickListener(onMyLocationClickListener);
        enableMyLocationIfPermitted();

        mMap.getUiSettings().setZoomControlsEnabled(true);
        //mMap.setMinZoomPreference(11);

        // define point to center on
        LatLng origin = new LatLng(50.1299187, 8.6923254);
        CameraUpdate panToOrigin = CameraUpdateFactory.newLatLng(origin);
        mMap.moveCamera(panToOrigin);

        // set zoom level with animation
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 200, null);
    }

    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng redmond = new LatLng(50.11552, 8.68417);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(redmond));
    }

    public boolean checkUserLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
                return;
            }

        }
    }

    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(15);
                    return false;
                }
            };

    private GoogleMap.OnMyLocationClickListener onMyLocationClickListener =
            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {

                    mMap.setMinZoomPreference(12);

                    CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(new LatLng(location.getLatitude(),
                            location.getLongitude()));

                    circleOptions.radius(200);
                    circleOptions.fillColor(Color.RED);
                    circleOptions.strokeWidth(6);

                    mMap.addCircle(circleOptions);
                }
            };

    public void showCapsuleList(){
        aController.showCapsules();
    }
    public void printCapsuleList() {
        for (int i = 0; i < listCapsules.size(); i++) {
            System.out.println(listCapsules.get(i).getPrice());
            mCapsule.add(new Capsules(R.drawable.snoozelogo,listCapsules.get(i).getName(),listCapsules.get(i).getPrice()));
        }
    }

    public void buildRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new CapsuleAdapter(mCapsule);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new CapsuleAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

}