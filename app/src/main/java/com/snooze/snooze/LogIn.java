package com.snooze.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.snooze.model.snooze.controller.UserController;

public class LogIn extends AppCompatActivity {

    private Button btnLogIn;
    private Button btnRegister;
    private Button btnInfo;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtInfoText;
    private UserController ucontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ucontroller = new UserController();
        btnLogIn = findViewById(R.id.btn_LogIn_LogIn);
        btnRegister = findViewById(R.id.btn_LogIn_Register);
        btnInfo = findViewById(R.id.btn_LogIn_AboutSnooze);
        edtEmail = findViewById(R.id.edt_LogIn_Email);
        edtPassword = findViewById(R.id.edt_LogIn_Password);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();


                ucontroller.setOnDataListener(new UserController.DataInterface() {
                    @Override
                    public void responseData(String myResponse) {
                        System.out.println(myResponse);

                        if(myResponse.equals("OK")){
                            Toast.makeText(LogIn.this, "Success", Toast.LENGTH_SHORT).show();
                            switchScreens(Menu.class);
                        }
                    }
                });



                ucontroller.login(email,password);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreens(Register.class);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreens(AboutSnooze.class);
            }
        });




    }


    public void switchScreens(Class s)
    {
        Intent i = new Intent(this,s);
        startActivity(i);

    }



}



