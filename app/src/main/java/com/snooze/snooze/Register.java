package com.snooze.snooze;

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

        edtUserName = findViewById(R.id.edt_Register_Firstname);
        edtEmail = findViewById(R.id.edt_Register_Email);
        edtPassword = findViewById(R.id.edt_Register_Password);

        btnRegister = findViewById(R.id.btn_Register_Register);

        Button btn_Back = findViewById(R.id.btn_Register_back);

        btn_Back.setOnClickListener(new View.OnClickListener() {
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
}
