package com.snooze.snooze;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.snooze.model.snooze.controller.AppController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.R.layout.simple_spinner_item;

public class BookDialog extends AppCompatDialogFragment {
    private  Spinner spinner;
    private TextView txtView;
    private ArrayList<RowItem> rowList;
    private SpinnerAdapter adapter;
    private AppController aController;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        super.onCreate(savedInstanceState);
        String id = getArguments().getString("capID");

        System.out.println("ID OF CAP: " + id);

        aController = MainActivity.getInstance().getaController();

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());

        aController.getAvailableCapsules(id,nowAsISO);
        aController.setOnDataListener(new AppController.DataInterface3() {
            @Override
            public void responseAvailable(JsonElement capsules) {
                System.out.println("FRAMES FOR CAP");
                System.out.println(capsules.toString());
            }
        });


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_booking, null);

        builder.setView(view)
                .setTitle("Book")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Book", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        txtView = view.findViewById(R.id.textView4);

        initList();

        spinner = view.findViewById(R.id.allTimeSlots);

        adapter = new SpinnerAdapter(this.getActivity(), rowList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RowItem item = (RowItem) parent.getItemAtPosition(position);
                String clickedItem = item.getText();
                System.out.println(clickedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return builder.create();
    }

    private void initList() {
       rowList = new ArrayList<>();
       rowList.add(new RowItem("TIME SLOT  1"));
       rowList.add(new RowItem("TIME SLOT  2"));
       rowList.add(new RowItem("TIME SLOT  3"));
       rowList.add(new RowItem("TIME SLOT  4"));
       rowList.add(new RowItem("TIME SLOT  5"));


    }
}
