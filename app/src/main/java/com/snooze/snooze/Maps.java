package com.snooze.snooze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.snooze.api.snooze.Payment.PaymentHandler;
import com.snooze.api.snooze.inc.Capsules;
import com.snooze.model.snooze.controller.AppController;
import com.snooze.model.snooze.controller.UserController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Maps extends AppCompatActivity implements
        OnMapReadyCallback, BookDialog.dialogListener{


    private static final int REQUEST_CODE = 1;
    private GoogleMap mMap;
    private AppController aController;
    private UserController uController;
    private List<Capsules> listCapsules = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CapsuleAdapter mAdapter;
    private ArrayList<Capsules> mCapsule;
    private Button btn_Maps_back;
    private PaymentHandler payment;
    private Integer selectedCap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        aController = MainActivity.getInstance().getaController();
        uController = MainActivity.getInstance().getuController();
        payment = MainActivity.getInstance().getPaymentHandler();
        //textView4 = findViewById(R.id.textView4);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        btn_Maps_back = findViewById(R.id.btn_Maps_Back);
        mCapsule = new ArrayList<>();

        btn_Maps_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                selectedCap = capID;
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


    @Override
    public void applyTexts(final String timeFrame, final Integer timeFrameNo) {
        Log.d("Maps ApplyTexts", timeFrame);

        try {
            BraintreeFragment mBraintreeFragment = BraintreeFragment.newInstance(this, payment.getPaypalClientToken());
            Log.d("Fragment", mBraintreeFragment.toString());
            Log.d("Token", payment.getPaypalClientToken());



            mBraintreeFragment.addListener(new PaymentMethodNonceCreatedListener() {
                @Override
                public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
                    String nonce = paymentMethodNonce.getNonce();
                    Log.d("Nonce", nonce);
                    payment.setPaymentNonce(nonce);

                    Boolean success = payment.sale();
                    Log.d("SUCCESS PAYING", success.toString());
                    Log.d("PAYING ID", payment.getPaymentID());
                    Log.d("Cap: ", selectedCap.toString());
                    Log.d("Time-Frame", timeFrame);
                    Log.d("Frame No", timeFrameNo.toString());

                    if(success){
                        // TODO START / END FRAME
                        uController.placeBooking(success.toString(), payment.getPaymentID(), selectedCap, timeFrameNo,timeFrameNo);

                        Toast.makeText(Maps.this,"Succces: " + success.toString() + " with ID: " + payment.getPaymentID() , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Maps.this,"Failed to pay via paypal." , Toast.LENGTH_SHORT).show();
                    }

                }
            });

            mBraintreeFragment.addListener(new BraintreeErrorListener() {
                @Override
                public void onError(Exception error) {
                    Log.e("Error Nonce", error.toString());

                }
            });

            mBraintreeFragment.addListener(new ConfigurationListener() {
                @Override
                public void onConfigurationFetched(Configuration configuration) {
                    Log.d("Configuration", configuration.toString());
                    Log.d("Configuration2", configuration.toJson());
                }
            });



            setupBraintreeAndStartExpressCheckout(mBraintreeFragment, payment.getPaypalClientToken());

            // mBraintreeFragment is ready to use!
        } catch (InvalidArgumentException e) {
            // There was an issue with your authorization string.
            Log.e("Nonce err", e.toString());
        }
    }


    public void setupBraintreeAndStartExpressCheckout(BraintreeFragment mBraintreeFragment, String token) {
        System.out.println("express checkout");

        PayPalRequest request = new PayPalRequest("2")
                .currencyCode("EUR")
                .intent(PayPalRequest.INTENT_AUTHORIZE);
        PayPal.requestOneTimePayment(mBraintreeFragment, request);
    }
}