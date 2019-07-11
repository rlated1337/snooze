package com.snooze.snooze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.snooze.model.snooze.controller.UserController;

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
    private UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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


    }
}
