package com.snooze.api.snooze.inc;

import com.google.gson.annotations.SerializedName;

public class Credentials {

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;


    public Credentials(String email, String username, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
