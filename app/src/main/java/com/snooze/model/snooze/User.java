package com.snooze.model.snooze;

public class User {
    private String username;
    private String email;
    private int id;
    private String payment;

    public User(String username, String email, int id, String payment) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.payment = payment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
