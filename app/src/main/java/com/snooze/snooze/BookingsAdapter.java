package com.snooze.snooze;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {
    private List<String> listPrice;
    private List<String> listDate;
    private List<String> listPeriod;
    private List<String> listCapsuleName;
    private Context mContext;

    public BookingsAdapter(List<String> listPrice, List<String> listDate,List<String> listPeriod,List<String> listCapsuleName,Context mContext) {
        this.listPrice = listPrice;
        this.listDate = listDate;
        this.listPeriod = listPeriod;
        this.mContext = mContext;
        this.listCapsuleName=listCapsuleName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookings_row_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.price.setText(listPrice.get(position));
        holder.date.setText(listDate.get(position));
        holder.period.setText(listPeriod.get(position));
        holder.name.setText(listCapsuleName.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listDate.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView price;
        TextView date;
        TextView period;
        TextView name;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            date = itemView.findViewById(R.id.bookings_date);
            price = itemView.findViewById(R.id.bookings_price);
            period = itemView.findViewById(R.id.bookings_period);
            name = itemView.findViewById(R.id.bookings_capsuleName);
        }
    }

}


