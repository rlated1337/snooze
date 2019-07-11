package com.snooze.api.snooze.Payment;

import android.os.AsyncTask;
import android.util.Log;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import java.math.BigDecimal;

public class PaymentHandler {
    private String customerID;
    private String paypalClientToken;
    private String paymentNonce;
    private PaymentThread thread;

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    private String paymentID;

    private static BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "ttcvr6x62dpshszc",
            "bff8brxtspsm8972",
            "ab113676a2619a4b30bc826312c7e842"
    );



    public PaymentHandler(){
        getPToken();
    }

    private void getPToken() {
        PaymentThread thread = new PaymentThread();
        Thread t2 = new Thread(thread);

        t2.start();

        try {
            t2.join();
            paypalClientToken = thread.getValue();
            Log.d("Token set?", paypalClientToken);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPaypalClientToken() {
        return paypalClientToken;
    }

    public void setPaypalClientToken(String clientToken) {
        this.paypalClientToken = clientToken;
    }

    public String getPaymentNonce() {
        return paymentNonce;
    }

    public void setPaymentNonce(String paymentNonce) {
        this.paymentNonce = paymentNonce;
    }

    public static BraintreeGateway getGateway() {
        return gateway;
    }

    public static void setGateway(BraintreeGateway gateway) {
        PaymentHandler.gateway = gateway;
    }



    public boolean sale(){
        SaleThread thread = new SaleThread(this.getPaymentNonce(), this.paypalClientToken);
        Thread t2 = new Thread(thread);
        Boolean success = false;
        t2.start();

        try {
            t2.join();
            success = thread.getSuccess();

            if(success){
                this.setPaymentID(thread.getPaymentID());
            }

           // paypalClientToken = thread.getValue();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return success;
    }
}


