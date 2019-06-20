package com.snooze.snooze;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.snooze.api.snooze.inc.Capsules;
import com.snooze.model.snooze.controller.AppController;

import java.util.ArrayList;
import java.util.Date;
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

        mMap.getUiSettings().setZoomControlsEnabled(true);
        //mMap.setMinZoomPreference(11);

        // define point to center on
        LatLng origin = new LatLng(50.1299187, 8.6923254);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 15));
    }

    public void showCapsuleList(){
        aController.showCapsules();
    }

    public void printCapsuleList() {
        for (int i = 0; i < listCapsules.size(); i++) {
            System.out.println("GET ID");
            System.out.println(listCapsules.get(i).getId());
            System.out.println(listCapsules.get(i).getPrice());
            mCapsule.add(new Capsules(R.drawable.snoozelogo,listCapsules.get(i).getName(),listCapsules.get(i).getPrice(), listCapsules.get(i).getId()));

            // Creating a marker
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng lng = new LatLng(listCapsules.get(i).getLatitude(), listCapsules.get(i).getLongitude());
            // Setting the position for the marker
            markerOptions.position(lng);

            // Placing a marker on the touched position
            mMap.addMarker(markerOptions);
        }
    }

    public void buildRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new CapsuleAdapter(mCapsule);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new CapsuleAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int capID) {
                System.out.println("on item click");
                System.out.println("ID: " + capID);

                // CREATE POPUP WITH TIMEFRAMES AND BOOK BUTTON

                openDialog(capID);

            }
        });

    }

    public void openDialog(int id){
        BookDialog ex = new BookDialog();

        Bundle args = new Bundle();
        args.putString("capID", String.valueOf(id));

        ex.setArguments(args);
        ex.show(getSupportFragmentManager(), "Book");

    }


}