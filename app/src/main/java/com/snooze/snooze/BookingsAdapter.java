package com.snooze.snooze;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snooze.api.snooze.inc.Bookings;

import java.util.ArrayList;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {
    private ArrayList<Bookings> mBooking;
    private onItemClickListener mListener;

    public BookingsAdapter(ArrayList<Bookings> bookings) {
        this.mBooking = bookings;
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate;
        public TextView mPrice;

        public ViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            mDate = (TextView) itemView.findViewById(R.id.bookings_name);
            mPrice = (TextView) itemView.findViewById(R.id.bookings_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public BookingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookings_row_layout, viewGroup, false);
        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsAdapter.ViewHolder viewHolder, int i) {
        Bookings currentItem = mBooking.get(i);

        viewHolder.mDate.setText(currentItem.getDate().toString());
        viewHolder.mPrice.setText(currentItem.getPayedAmount() + "€");
    }

    @Override
    public int getItemCount() {
        return mBooking.size();
    }
}