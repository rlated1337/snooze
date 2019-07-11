package com.snooze.api.snooze.Payment;

import android.util.Log;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import java.math.BigDecimal;

public class SaleThread implements Runnable {
    private String nonce;
    private String accessToken;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private Boolean success;

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    private String paymentID;

    public SaleThread(String nonce, String ac){
        this.nonce = nonce;
        this.accessToken = ac;
    }

    @Override
    public void run() {
        Log.d("AC", accessToken);
        Log.d("Nonce", nonce);

        TransactionRequest request = new TransactionRequest()
                .amount(new BigDecimal("2.00"))
                .paymentMethodNonce(nonce)
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> saleResult = PaymentHandler.getGateway().transaction().sale(request);

        if (saleResult.isSuccess()) {
            Transaction transaction = saleResult.getTarget();
            this.setPaymentID(transaction.getPayPalDetails().getAuthorizationId());
            this.setSuccess(true);
        } else {
            System.out.println("Message: " + saleResult.getMessage());
            this.setSuccess(false);
        }
    }


}
