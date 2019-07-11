package com.snooze.snooze;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.snooze.model.snooze.controller.UserController;

import org.json.JSONObject;

public class Settings extends AppCompatActivity {
    private Button btnBack;
    private Button btnSubmit;
    private EditText edtLightLevel;
    private EditText edtVolumenLevel;
    private EditText edtLightColor;
    private EditText edtBedLegAngleNumber;
    private EditText edtBedBackAngleNumber;
    private EditText edtBedMidAngleNumber;
    private SeekBar skbBedLegAngle;
    private SeekBar skbBedBackAngle;
    private SeekBar skbBedMidAngle;
    private SeekBar skbLightLevel;
    private SeekBar skbVolumenLevel;
    private UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        uController = MainActivity.getInstance().getuController();

        btnBack = findViewById(R.id.btn_Settings_Back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSubmit = findViewById(R.id.btnSubmit);

        edtLightLevel = findViewById(R.id.edtLightLevel);
        edtVolumenLevel = findViewById(R.id.edtVolumenLevel);
        edtLightColor = findViewById(R.id.edtLightColor);
        edtBedLegAngleNumber = findViewById(R.id.edtBedLegAngleNumber);
        edtBedBackAngleNumber = findViewById(R.id.edtBedBackAngleNumber);
        edtBedMidAngleNumber = findViewById(R.id.edtBedMidAngleNumber);

        skbBedLegAngle = findViewById(R.id.skbBedLegAngle);
        skbBedBackAngle = findViewById(R.id.skbBedBackAngle);
        skbBedMidAngle = findViewById(R.id.skbBedMidAngle);
        skbLightLevel = findViewById(R.id.skbLightLevel);
        skbVolumenLevel = findViewById(R.id.skbVolumenLevel);


        skbBedLegAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edtBedLegAngleNumber.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbBedBackAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edtBedBackAngleNumber.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbBedMidAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edtBedMidAngleNumber.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbLightLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edtLightLevel.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skbVolumenLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edtVolumenLevel.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        edtBedLegAngleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    skbBedLegAngle.setProgress(Integer.parseInt(s.toString()));
                    edtBedLegAngleNumber.setSelection(edtBedLegAngleNumber.getText().length());
                }
            }
        });

        edtBedBackAngleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    skbBedBackAngle.setProgress(Integer.parseInt(s.toString()));
                    edtBedBackAngleNumber.setSelection(edtBedBackAngleNumber.getText().length());
                }
            }
        });

        edtBedMidAngleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    skbBedMidAngle.setProgress(Integer.parseInt(s.toString()));
                    edtBedMidAngleNumber.setSelection(edtBedMidAngleNumber.getText().length());
                }
            }
        });

        edtLightLevel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    skbLightLevel.setProgress(Integer.parseInt(s.toString()));
                    edtLightLevel.setSelection(edtLightLevel.getText().length());
                }
            }
        });

        edtVolumenLevel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    skbVolumenLevel.setProgress(Integer.parseInt(s.toString()));
                    edtVolumenLevel.setSelection(edtVolumenLevel.getText().length());
                }
            }
        });

        getCapsulePreferences();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            Integer bedLegAngle;
            Integer bedBackAngle;
            Integer lightLevel;
            Integer volumenLevel;
            String lightColor;
            Integer bedMidAngle;

            @Override
            public void onClick(View v) {
                bedLegAngle = Integer.parseInt(edtBedLegAngleNumber.getText().toString());
                bedBackAngle = Integer.parseInt(edtBedBackAngleNumber.getText().toString());
                lightLevel = Integer.parseInt(edtLightLevel.getText().toString());
                volumenLevel = Integer.parseInt(edtVolumenLevel.getText().toString());
                lightColor = edtLightColor.getText().toString();
                bedMidAngle = Integer.parseInt(edtBedMidAngleNumber.getText().toString());

                uController.setOnDataListener(new UserController.DataInterface() {
                    @Override
                    public void responseData(JSONObject myResponse) {
                        System.out.println(myResponse);

                        if(myResponse.length() > 0){
                            Toast.makeText(Settings.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Settings.this, "Failed to submit", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                uController.setCapsulePreferences(bedLegAngle,bedBackAngle,lightLevel,volumenLevel,lightColor,bedMidAngle);

            }
        });


    }

    public void getCapsulePreferences(){
        uController.setOnBookingListener(new UserController.DataInterfaceBookings() {
            @Override
            public void responseBookings(JsonElement myBookings) {
                JsonElement jElement = myBookings;
                JsonObject jObject = jElement.getAsJsonObject();
                JsonObject capPref = jObject.getAsJsonObject("capsulePreference");

                edtBedLegAngleNumber.setText(capPref.get("BedLegAngle").toString());
                edtBedBackAngleNumber.setText(capPref.get("BedBackAngle").toString());
                edtLightLevel.setText(capPref.get("LightLevel").toString());
                edtVolumenLevel.setText(capPref.get("VolumenLevel").toString());
                edtLightColor.setText(capPref.get("LightColor").toString());
                edtBedMidAngleNumber.setText(capPref.get("BedMidAngle").toString());

            }
        });

        uController.getCapsulePreferencesById();

    }
}
