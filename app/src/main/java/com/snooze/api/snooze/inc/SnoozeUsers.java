package com.snooze.api.snooze.inc;

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

    @SerializedName("password")
    private String password;

    public SnoozeUsers(String realm, String username, String email, Boolean emailVerified, String password){
        this.realm = realm;
        this.username = username;
        this.email = email;
        this.emailVerified = emailVerified;
        this.password = password;
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
    public String getPassword(){return password;}

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }


}
