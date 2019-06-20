package com.snooze.snooze;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snooze.api.snooze.inc.Capsules;

import java.util.ArrayList;

public class CapsuleAdapter extends RecyclerView.Adapter<CapsuleAdapter.ViewHolder> {
    private ArrayList<Capsules> mCapsule;
    private onItemClickListener mListener;


    public CapsuleAdapter(ArrayList<Capsules> capsule) {
        this.mCapsule = capsule;
    }

    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public void setOnClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mName;
        public TextView mPrice;

        public ViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mPrice = (TextView) itemView.findViewById(R.id.price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public CapsuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.capsule_row_layout,viewGroup,false);
        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CapsuleAdapter.ViewHolder viewHolder, int i) {
        Capsules currentItem = mCapsule.get(i);
        viewHolder.mImageView.setImageResource(currentItem.getImageResource());
        viewHolder.mName.setText(currentItem.getName());
        viewHolder.mPrice.setText(currentItem.getPrice() + "€");
    }

    @Override
    public int getItemCount() {
        return mCapsule.size();
    }



}
