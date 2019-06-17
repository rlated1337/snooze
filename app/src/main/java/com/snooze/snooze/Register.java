package com.snooze.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.snooze.api.snooze.inc.SnoozeUsers;

import com.snooze.model.snooze.controller.UserController;

public class Register extends AppCompatActivity {
    private Button btnBack;
    private Button btnRegister;
    private Button btnAboutSnooze;
    private EditText edtUserName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPasswordCheck;
    private UserController ucontroller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ucontroller = new UserController();
        btnRegister = findViewById(R.id.btn_Register_Register);
        btnAboutSnooze = findViewById(R.id.btn_Register_AboutSnooze);
        btnBack = findViewById(R.id.btn_Register_back);
        edtUserName = findViewById(R.id.edt_Register_Username);
        edtEmail = findViewById(R.id.edt_Register_Email);
        edtPassword  = findViewById(R.id.edt_Register_Password);
        edtPasswordCheck = findViewById(R.id.edt_Register_PasswordCheck);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUserName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                System.out.println("Username: " + username + " Email: " + email + " PW: " + password);


                ucontroller.setOnDataListener(new UserController.DataInterface() {
                    @Override
                    public void responseData(String myResponse) {
                        System.out.println(myResponse);

                        if(myResponse.equals("OK")){
                            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            switchScreens(Menu.class);
                        }
                    }
                });



                ucontroller.register(username,email,password);



            }
        });


    }

    public void switchScreens(Class s)
    {
        Intent i = new Intent(this,s);
        startActivity(i);

    }



}
