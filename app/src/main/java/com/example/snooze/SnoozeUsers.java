package com.example.snooze;

import com.google.gson.annotations.SerializedName;

public class SnoozeUsers {

    @SerializedName("realm")
    private String realm;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("emailVerified")
    private boolean emailVerified;

    @SerializedName("id")
    private Integer id;

    public SnoozeUsers(String realm, String username, String email, Boolean emailVerfified, Integer id){
        this.realm = realm;
        this.username = username;
        this.email = email;
        this.emailVerified = emailVerfified;
        this.id = id;
    }

    public String getRealm(){
        return realm;
    }
    public void setRealm(String realm){
        this.realm = realm;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public Boolean getEmailVerified(){
        return emailVerified;
    }
    public void setEmailVerfified(Boolean emailVerfified){
        this.emailVerified = emailVerfified;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }


}
