package com.snooze.snooze;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        public TextView capID;

        public ViewHolder(@NonNull final View itemView, final onItemClickListener listener) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mPrice = (TextView) itemView.findViewById(R.id.price);
            capID = (TextView) itemView.findViewById(R.id.capID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        Capsules currentItem2 = mCapsule.get(position);
                        int capsuleID = currentItem2.getId();

                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(capsuleID);
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
        Log.d("CURRENTITEM", currentItem.getId().toString());

        viewHolder.mImageView.setImageResource(currentItem.getImageResource());
        viewHolder.mName.setText(currentItem.getName());
        viewHolder.mPrice.setText(currentItem.getPrice() + "€");
       // System.out.println(currentItem.getId());
//        viewHolder.capID.setText(currentItem.getId());
    }

    @Override
    public int getItemCount() {
        return mCapsule.size();
    }



}
