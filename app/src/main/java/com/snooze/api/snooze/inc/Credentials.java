package com.snooze.api.snooze.inc;

import com.google.gson.annotations.SerializedName;

public class Credentials {

    @SerializedName("username")
    private String email;

    @SerializedName("password")
    private String password;


    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
