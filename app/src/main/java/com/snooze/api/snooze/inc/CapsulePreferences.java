package com.snooze.api.snooze.inc;

import com.google.gson.annotations.SerializedName;

public class CapsulePreferences {

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

    @SerializedName("LightColor")
    private String lightColor;

    @SerializedName("BedMidAngle")
    private Integer bedMidAngle;

    @SerializedName("id")
    private Integer id;

    public CapsulePreferences(Integer snoozeuser_id, Integer bedLegAngle, Integer bedBackAngle, Integer lightLevel,
                              Integer volumenLevel, String lightColor, Integer bedMidAngle) {
        this.snoozeuser_id = snoozeuser_id;
        this.bedLegAngle = bedLegAngle;
        this.bedBackAngle = bedBackAngle;
        this.lightLevel = lightLevel;
        this.volumenLevel = volumenLevel;
        this.lightColor = lightColor;
        this.bedMidAngle = bedMidAngle;
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

    public String getLightColor() {
        return lightColor;
    }

    public void setLightColor(String lightColor) {
        this.lightColor = lightColor;
    }

    public Integer getBedMidAngle() {
        return bedMidAngle;
    }

    public void setBedMidAngle(Integer bedMidAngle) {
        this.bedMidAngle = bedMidAngle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
