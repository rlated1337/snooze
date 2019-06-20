package com.snooze.api.snooze.inc;

import com.google.gson.annotations.SerializedName;

public class Capsules {

    @SerializedName("Name")
    private String name;

    @SerializedName("Latitude")
    private double latitude;

    @SerializedName("Longitude")
    private double longitude;

    @SerializedName("IPAddress")
    private String ipAddress;

    @SerializedName("Price")
    private Integer price;

    @SerializedName("id")
    private Integer id;

    private int imageResource;



    public Capsules(String name, Double latitude, Double longitude, String ipAddress, Integer price) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ipAddress = ipAddress;
        this.price = price;
    }
    public Capsules(Integer imageResource, String name, Integer price, Integer id){
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
