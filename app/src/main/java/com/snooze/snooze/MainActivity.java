package com.snooze.snooze;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.exceptions.BraintreeError;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.snooze.api.snooze.ApiConnector;
import com.snooze.api.snooze.Payment.PaymentThread;
import com.snooze.api.snooze.SnoozeUsersService;
import com.snooze.model.snooze.controller.AppController;
import com.snooze.model.snooze.controller.UserController;

import retrofit2.Retrofit;

import com.snooze.api.snooze.Payment.PaymentHandler;



public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private UserController uController;
    private AppController  aController;
    private PaymentHandler payment;
    static MainActivity actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actv = this;

        uController = new UserController();
        aController = new AppController();
        payment = new PaymentHandler();

        //Switch to LogInScreen
        Intent i = new Intent(this,LogIn.class);
        startActivity(i);


        System.out.println("in main activity");

        /* CONNECTION TO API */
        ApiConnector connect = new ApiConnector();
        retrofit = connect.getRetrofitInstance();
        SnoozeUsersService service = retrofit.create(SnoozeUsersService.class);





    }



    public static MainActivity getInstance(){
        return actv;
    }

    public UserController getuController() {
        return uController;
    }

    public void setuController(UserController uController) {
        this.uController = uController;
    }

    public AppController getaController() {
        return aController;
    }

    public void setaController(AppController aController) {
        this.aController = aController;
    }

    public PaymentHandler getPaymentHandler(){
        return payment;
    }

}

