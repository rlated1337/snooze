package com.snooze.snooze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Account extends AppCompatActivity {
    private Button btnBack;
    private Button btnChangePassword;
    private Button btnSave;
    private EditText edtEmail;
    private EditText edtPassword,edtPasswordCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        btnBack = findViewById(R.id.btn_Account_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edtEmail = findViewById(R.id.edt_Account_Email);
        edtPassword = findViewById(R.id.edt_Account_Password);
        edtPasswordCheck = findViewById(R.id.edt_Account_Password2);
        btnChangePassword = findViewById(R.id.btn_Account_changePassword);
        btnSave = findViewById(R.id.btn_Account_SaveAndExit);

        edtPasswordCheck.setVisibility(View.INVISIBLE);
        edtPassword.setEnabled(false);
        edtPasswordCheck.setEnabled(false);
        edtEmail.setEnabled(false);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPasswordCheck.setVisibility(View.VISIBLE);
                edtPassword.setEnabled(true);
                edtPasswordCheck.setEnabled(true);
            }
        });



    }
}
