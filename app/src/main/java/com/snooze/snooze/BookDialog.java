package com.snooze.snooze;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.util.SparseArray;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import static android.R.layout.simple_spinner_item;

public class BookDialog extends AppCompatDialogFragment {
    private  Spinner spinner;
    private TextView txtView;
    private ArrayList<RowItem> rowList;
    private SpinnerAdapter adapter;
    private AppController aController;
    private static Integer TIME_FRAME_AMOUNT = 27;
    private SparseArray<String> timeFrameTranslation;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        super.onCreate(savedInstanceState);
        String id = getArguments().getString("capID");

        System.out.println("ID OF CAP: " + id);

        rowList = new ArrayList<>();
        initTimeFrameMap();

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


                initList(capsules);
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

        initList(null);

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

    private void initList(JsonElement capsules) {
        if(capsules == null){
            rowList.add(new RowItem("TIME SLOT  1", true));
            rowList.add(new RowItem("TIME SLOT  2", true));
            rowList.add(new RowItem("TIME SLOT  3", true));
            rowList.add(new RowItem("TIME SLOT  4", true));
        }
        else{
            rowList.clear();
            System.out.println("JSON ELEMENT");
            System.out.println(capsules);

            JSONObject jsonObj = null;
            Boolean jsonMembers = null;


            try {
                jsonObj = new JSONObject(capsules.toString());

                for (int i = 1; i <= TIME_FRAME_AMOUNT; i++) {
                    jsonMembers = jsonObj.getBoolean(String.valueOf(i));

                    Log.d("test", timeFrameTranslation.get(i));

                    if(String.valueOf(jsonMembers).equals("true")){
                        rowList.add(new RowItem(timeFrameTranslation.get(i), true));
                        Log.d("TIMEFRAME TRUE " + i, String.valueOf(jsonMembers));
                    }
                    else{
                        // DISABLED ANZEIGE {not verfügbare Termine}
                        rowList.add(new RowItem(timeFrameTranslation.get(i), false));
                        Log.d("TIMEFRAME FALSE " + i, String.valueOf(jsonMembers));
                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }



//will return "{"id":1172327,"assets":[{"id":436379,"licenseState":"MI","odometer":"12345"}]}"

        }




    }

    private void initTimeFrameMap(){
        timeFrameTranslation = new SparseArray<>();

        timeFrameTranslation.append(1, "09.00 bis 09.20 Uhr");
        timeFrameTranslation.append(2, "09.20 bis 09.40 Uhr");
        timeFrameTranslation.append(3, "09.40 bis 10.00Uhr");

        timeFrameTranslation.append(4, "10.00 bis 10.20 Uhr");
        timeFrameTranslation.append(5, "10.20 bis 10.40 Uhr");
        timeFrameTranslation.append(6, "10.40 bis 11.00 Uhr");

        timeFrameTranslation.append(7, "11.00 bis 11.20 Uhr");
        timeFrameTranslation.append(8, "11.20 bis 11.40 Uhr");
        timeFrameTranslation.append(9, "11.40 bis 12.00 Uhr");

        timeFrameTranslation.append(10, "12.00 bis 12.20 Uhr");
        timeFrameTranslation.append(11, "12.20 bis 12.40 Uhr");
        timeFrameTranslation.append(12, "12.40 bis 13.00 Uhr");

        timeFrameTranslation.append(13, "13.00 bis 13.20 Uhr");
        timeFrameTranslation.append(14, "13.20 bis 13.40 Uhr");
        timeFrameTranslation.append(15, "13.40 bis 14.00 Uhr");

        timeFrameTranslation.append(16, "14.00 bis 14.20 Uhr");
        timeFrameTranslation.append(17, "14.20 bis 14.40 Uhr");
        timeFrameTranslation.append(18, "14.40 bis 15.00 Uhr");

        timeFrameTranslation.append(19, "15.00 bis 15.20 Uhr");
        timeFrameTranslation.append(20, "15.20 bis 15.40 Uhr");
        timeFrameTranslation.append(21, "15.40 bis 16.00 Uhr");

        timeFrameTranslation.append(22, "16.00 bis 16.20 Uhr");
        timeFrameTranslation.append(23, "16.20 bis 16.40 Uhr");
        timeFrameTranslation.append(24, "16.40 bis 17.00 Uhr");

        timeFrameTranslation.append(25, "17.00 bis 17.20 Uhr");
        timeFrameTranslation.append(26, "17.20 bis 17.40 Uhr");
        timeFrameTranslation.append(27, "17.40 bis 18.00 Uhr");

        Log.d("Time Frame Translation", timeFrameTranslation.toString());


    }
}
