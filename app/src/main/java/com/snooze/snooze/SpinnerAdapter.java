package com.snooze.snooze;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



public class SpinnerAdapter extends ArrayAdapter<RowItem> {

    public SpinnerAdapter(Context context, ArrayList<RowItem> items){
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView =  LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_row, parent, false
            );
        }


        TextView textViewName = convertView.findViewById(R.id.rowItemText);

        RowItem currentItem = getItem(position);

        if(currentItem != null){
            textViewName.setText(currentItem.getText());
            textViewName.setEnabled(currentItem.getAvailable());
        }


        return convertView;
    }
}
