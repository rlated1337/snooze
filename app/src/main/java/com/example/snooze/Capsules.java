package com.example.snooze;

import com.google.gson.annotations.SerializedName;

public class Capsules {

    @SerializedName("Name")
    private String name;

    @SerializedName("Latitude")
    private Integer latitude;

    @SerializedName("Longitude")
    private Integer longitude;

    @SerializedName("IPAddress")
    private String ipAddress;

    @SerializedName("id")
    private Integer id;

    public Capsules(String name, Integer latitude, Integer longitude, String ipAddress, Integer id) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ipAddress = ipAddress;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
