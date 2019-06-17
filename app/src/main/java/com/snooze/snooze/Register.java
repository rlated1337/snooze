package com.snooze.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.snooze.model.snooze.controller.UserController;

public class Register extends AppCompatActivity {
    private Button btnBack;
    private Button btnRegister;
    private Button btnAboutSnooze;
    private EditText edtUserName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPasswordCheck;

    UserController ucontroller = new UserController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
