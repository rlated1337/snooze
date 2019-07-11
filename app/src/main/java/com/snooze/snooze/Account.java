package com.snooze.snooze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.snooze.model.snooze.controller.UserController;

import org.json.JSONObject;

public class Account extends AppCompatActivity {
    private Button btnBack;
    private Button btnChangePassword;
    private Button btnSave;
    private EditText edtEmail,edtUsername;
    private EditText edtOldPassword,edtNewPassword;
    private UserController uController;

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
        edtUsername = findViewById(R.id.edtText_Username);
        edtOldPassword = findViewById(R.id.edt_Account_PasswordOLD);
        edtNewPassword = findViewById(R.id.edt_Account_PasswordNEW);
        btnChangePassword = findViewById(R.id.btn_Account_changePassword);
        btnSave = findViewById(R.id.btn_Account_SaveAndExit);

        uController =  MainActivity.getInstance().getuController();


        edtOldPassword.setVisibility(View.INVISIBLE);
        edtNewPassword.setVisibility(View.INVISIBLE);
        btnSave.setVisibility(View.INVISIBLE);


        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNewPassword.setVisibility(View.VISIBLE);
                edtOldPassword.setVisibility(View.VISIBLE);
                edtOldPassword.setEnabled(true);
                edtNewPassword.setEnabled(true);
                btnSave.setVisibility(View.VISIBLE);
                btnChangePassword.setVisibility(View.INVISIBLE);
            }
        });

        uController.getUserData();

        uController.setOnBookingListener(new UserController.DataInterfaceBookings() {
            @Override
            public void responseBookings(JsonElement myUser) {
                getUserInfo(myUser.toString());
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = edtOldPassword.getText().toString();
                String newPassword = edtNewPassword.getText().toString();

                System.out.println("OldPassword: "+oldPassword + " newPassword: "+ newPassword);

                uController.setOnDataListener(new UserController.DataInterface(){
                    @Override
                    public void responseData(JSONObject myResponse) {
                        System.out.println(myResponse);

                        if(myResponse.length() > 0){
                            Toast.makeText(Account.this, "Successfully changed Password", Toast.LENGTH_SHORT).show();
                            //switchScreens(LogIn.class, myResponse);
                            //switchScreens(Menu.class, myResponse);
                        }
                        else{
                            Toast.makeText(Account.this, "Failed to change Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                uController.changePassword(oldPassword,newPassword);
            }
        });




    }

    private void getUserInfo(String jsonString)
    {

        JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jobject = jelement.getAsJsonObject();

        String email,username;
        email = jobject.get("email").toString();
        username = jobject.get("username").toString();
        username = username.substring(1, username.length()-1);
        email = email.substring(1, email.length()-1);
        edtEmail.setText(email);
        edtUsername.setText(username);
        System.out.println("ACCOUNT");
        System.out.println("Username: "+username);
        System.out.println("Email: "+email);
        edtEmail.setEnabled(false);
        edtUsername.setEnabled(false);


    }

}
