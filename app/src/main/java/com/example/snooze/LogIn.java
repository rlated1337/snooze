package com.example.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {

    private Button btnLogIn;
    private Button btnRegister;
    private Button btnInfo;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnLogIn = findViewById(R.id.btn_LogIn_LogIn);
        btnRegister = findViewById(R.id.btn_LogIn_Register);
        btnInfo = findViewById(R.id.btn_LogIn_AboutSnooze);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchScreens(Menu.class);
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



