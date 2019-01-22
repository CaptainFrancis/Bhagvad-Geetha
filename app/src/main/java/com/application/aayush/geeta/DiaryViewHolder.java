package com.application.aayush.geeta;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Aayush on 11/12/2017.
 */

public class DiaryViewHolder extends RecyclerView.ViewHolder {
    Spinner spinner;
    TextView textView1,textView2;
    ImageView save,delete,share;


    public DiaryViewHolder(View itemView) {
     super(itemView);
     spinner = (Spinner)itemView.findViewById(R.id.spinner3);
     textView1 = (TextView)itemView.findViewById(R.id.textView36);
     textView2 = (TextView)itemView.findViewById(R.id.textView35);
     save = (ImageView)itemView.findViewById(R.id.imageView10);
     delete = (ImageView)itemView.findViewById(R.id.imageView11);
     share = (ImageView)itemView.findViewById(R.id.imageView12);
    }
}