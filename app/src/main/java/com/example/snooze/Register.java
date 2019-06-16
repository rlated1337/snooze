package com.example.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    private Button btnBack;
    private Button btnRegister;
    private Button btnAboutSnooze;
    private EditText edtUserName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPasswordCheck;


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

            }
        });

        btnAboutSnooze.setOnClickListener(new View.OnClickListener() {
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
