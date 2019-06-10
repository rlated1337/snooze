package com.example.model.snooze.controller;

import com.example.model.snooze.User;
import com.example.model.snooze.service.UserService;

public class UserController {
    private UserService userservice;
    private User user;

    public UserController(UserService userservice, User user) {
        this.userservice = userservice;
        this.user = user;
    }

    public void register(){

    }

    public void login(){

    }

    public void executePayment(){

    }

    public void userLogin(){

    }

    public void userLockOut(){

    }
}
