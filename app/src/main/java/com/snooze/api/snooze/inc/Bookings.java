package com.snooze.api.snooze.inc;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Bookings {

    @SerializedName("SnoozeUser_id")
    private Integer snoozeuser_id;

    @SerializedName("Capsule_id")
    private Integer capsule_id;

    @SerializedName("Pin")
    private String pin;

    @SerializedName("Date")
    private String date;

    @SerializedName("FirstTimeFrame")
    private Integer firstTimeFrame;

    @SerializedName("LastTimeFrame")
    private Integer lastTimeFrame;

    @SerializedName("Vendor")
    private String vendor;

    @SerializedName("Amount")
    private Integer amount;

    @SerializedName("IsVerified")
    private Boolean isVerified;

    @SerializedName("PayerEmail")
    private String payerEmail;

    @SerializedName("PayedAmount")
    private Integer payedAmount;

    @SerializedName("PayedDate")
    private String payedDate;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @SerializedName("Payment_id")
    private String paymentId;

    @SerializedName("id")
    private Integer id;


    public Bookings(Integer snoozeuser_id, Integer capsule_id, String pin, String date, Integer firstTimeFrame,
                    Integer lastTimeFrame, String vendor, Integer amount, Boolean isVerified, String payerEmail,
                    Integer payedAmount, String payedDate, String paymentId) {
        this.snoozeuser_id = snoozeuser_id;
        this.capsule_id = capsule_id;
        this.pin = pin;
        this.date = date;
        this.firstTimeFrame = firstTimeFrame;
        this.lastTimeFrame = lastTimeFrame;
        this.vendor = vendor;
        this.amount = amount;
        this.isVerified = isVerified;
        this.payerEmail = payerEmail;
        this.payedAmount = payedAmount;
        this.payedDate = payedDate;
        this.paymentId = paymentId;
    }

    public Integer getSnoozeuser_id() {
        return snoozeuser_id;
    }

    public void setSnoozeuser_id(Integer snoozeuser_id) {
        this.snoozeuser_id = snoozeuser_id;
    }

    public Integer getCapsule_id() {
        return capsule_id;
    }

    public void setCapsule_id(Integer capsule_id) {
        this.capsule_id = capsule_id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getFirstTimeFrame() {
        return firstTimeFrame;
    }

    public void setFirstTimeFrame(Integer firstTimeFrame) {
        this.firstTimeFrame = firstTimeFrame;
    }

    public Integer getLastTimeFrame() {
        return lastTimeFrame;
    }

    public void setLastTimeFrame(Integer lastTimeFrame) {
        this.lastTimeFrame = lastTimeFrame;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public Integer getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(Integer payedAmount) {
        this.payedAmount = payedAmount;
    }

    public String getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(String payedDate) {
        this.payedDate = payedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
