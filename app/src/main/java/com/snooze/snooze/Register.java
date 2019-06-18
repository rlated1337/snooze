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

public class Register extends AppCompatActivity {
    private Button btnBack;
    private Button btnRegister;
    private Button btnAboutSnooze;
    private EditText edtUserName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPasswordCheck;
    private MainActivity actv;
    private UserController uController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        actv = new MainActivity();

        uController = actv.getuController();

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


                uController.setOnDataListener(new UserController.DataInterface() {
                    @Override
                    public void responseData(JSONObject myResponse) {
                        System.out.println(myResponse);

                        if(myResponse.length() > 0){
                            Toast.makeText(Register.this, "Successfully Registered - Please confirm Email and Login", Toast.LENGTH_SHORT).show();

                            switchScreens(LogIn.class, myResponse);

                            //  switchScreens(Menu.class, myResponse);
                        }
                    }
                });



                uController.register(username,email,password);



            }
        });


    }

    public void switchScreens(Class s, JSONObject obj)
    {
        Intent i = new Intent(this,s);
        i.putExtra("ACC_TOKEN", obj.toString());
        startActivity(i);

    }



}
