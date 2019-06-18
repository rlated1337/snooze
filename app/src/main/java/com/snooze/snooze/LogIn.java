package com.snooze.snooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.snooze.snooze.MainActivity;
import com.snooze.model.snooze.controller.UserController;


import org.json.JSONObject;

public class LogIn extends AppCompatActivity {

    private Button btnLogIn;
    private Button btnRegister;
    private Button btnInfo;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtInfoText;
    private UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        uController =  MainActivity.getInstance().getuController();

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


                uController.setOnDataListener(new UserController.DataInterface() {
                    @Override
                    public void responseData(JSONObject myResponse) {
                        System.out.println(myResponse);

                        if(myResponse.length() > 0){
                            Toast.makeText(LogIn.this, "Success", Toast.LENGTH_SHORT).show();
                            switchScreensWithObject(Menu.class, myResponse);
                        }
                    }
                });



                uController.login(email,password);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            JSONObject obj = new JSONObject();

            @Override
            public void onClick(View v) {
                switchScreensWithObject(Register.class, obj);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            JSONObject obj = new JSONObject();
            @Override
            public void onClick(View v) {
                switchScreensWithoutObject(Menu.class);
            }
        });
    }


    public void switchScreensWithObject(Class s, JSONObject obj)
    {
        Intent i = new Intent(this,s);
        i.putExtra("ACC_TOKEN", obj.toString());
        startActivity(i);

    }
    public void switchScreensWithoutObject(Class s)
    {
        Intent i = new Intent(this,s);
        startActivity(i);

    }



}



