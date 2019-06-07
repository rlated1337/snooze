package com.example.snooze;

import com.google.gson.annotations.SerializedName;

public class CapsulePrefernces {

    @SerializedName("SnoozeUser_id")
    private Integer snoozeuser_id;

    @SerializedName("BedLegAngle")
    private Integer bedLegAngle;

    @SerializedName("BedBackAngle")
    private Integer bedBackAngle;

    @SerializedName("LightLevel")
    private Integer lightLevel;

    @SerializedName("VolumenLevel")
    private Integer volumenLevel;

    @SerializedName("id")
    private Integer id;

    public CapsulePrefernces(Integer snoozeuser_id, Integer bedLegAngle, Integer bedBackAngle, Integer lightLevel,
                             Integer volumenLevel, Integer id) {
        this.snoozeuser_id = snoozeuser_id;
        this.bedLegAngle = bedLegAngle;
        this.bedBackAngle = bedBackAngle;
        this.lightLevel = lightLevel;
        this.volumenLevel = volumenLevel;
        this.id = id;
    }

    public Integer getSnoozeuser_id() {
        return snoozeuser_id;
    }

    public void setSnoozeuser_id(Integer snoozeuser_id) {
        this.snoozeuser_id = snoozeuser_id;
    }

    public Integer getBedLegAngle() {
        return bedLegAngle;
    }

    public void setBedLegAngle(Integer bedLegAngle) {
        this.bedLegAngle = bedLegAngle;
    }

    public Integer getBedBackAngle() {
        return bedBackAngle;
    }

    public void setBedBackAngle(Integer bedBackAngle) {
        this.bedBackAngle = bedBackAngle;
    }

    public Integer getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(Integer lightLevel) {
        this.lightLevel = lightLevel;
    }

    public Integer getVolumenLevel() {
        return volumenLevel;
    }

    public void setVolumenLevel(Integer volumenLevel) {
        this.volumenLevel = volumenLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
