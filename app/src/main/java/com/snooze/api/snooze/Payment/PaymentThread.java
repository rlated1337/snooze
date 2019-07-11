package com.snooze.api.snooze.Payment;

import android.util.Log;

import com.braintreegateway.ClientTokenRequest;

public class PaymentThread implements Runnable{
    private String token;

    @Override
    public void run(){
        String clientToken = PaymentHandler.getGateway().clientToken().generate();
        token = clientToken;
    }

    public String getValue(){
        return token;
    }

}
