package com.snooze.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.snooze.api.snooze.ApiConnector;
import com.snooze.api.snooze.SnoozeUsersService;
import com.snooze.model.snooze.controller.AppController;
import com.snooze.model.snooze.controller.UserController;

import retrofit2.Retrofit;



public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private UserController uController;
    private AppController  aController;
    static MainActivity actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actv = this;

        uController = new UserController();

        aController = new AppController();

        //Switch to LogInScreen
        Intent i = new Intent(this,LogIn.class);
        startActivity(i);


        System.out.println("in main activity");

        /* CONNECTION TO API */
        ApiConnector connect = new ApiConnector();
        retrofit = connect.getRetrofitInstance();
        SnoozeUsersService service = retrofit.create(SnoozeUsersService.class);




    }

    public static MainActivity getInstance(){
        return actv;
    }

    public UserController getuController() {
        return uController;
    }

    public void setuController(UserController uController) {
        this.uController = uController;
    }

    public AppController getaController() {
        return aController;
    }

    public void setaController(AppController aController) {
        this.aController = aController;
    }
}
