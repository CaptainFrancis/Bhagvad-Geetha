package com.application.aayush.geeta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Praveen K S
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>{
    private LayoutInflater layoutInflater;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onEditOnClick(int position);
        void onDeleteOnClick(int position,List<DiaryContents> contentsList);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    List<DiaryContents> contentsList = Collections.emptyList();
    public DiaryAdapter(Context context,List<DiaryContents> contentsList) {
        layoutInflater = LayoutInflater.from(context);
        this.contentsList = contentsList;
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.diary_repeat_layout,parent,false);
        DiaryViewHolder diaryViewHolder =  new DiaryViewHolder(view,mListener);
        return diaryViewHolder;
    }

    @Override
    public void onBindViewHolder(DiaryViewHolder holder, int position) {
        DiaryContents current = contentsList.get(position);
        holder.chapter_no.setText(current.chapter_no);
        holder.chapter_content.setText(current.chapter_content);

    }

    @Override
    public int getItemCount() {
        return contentsList.size();
    }
    class DiaryViewHolder extends RecyclerView.ViewHolder{
        TextView chapter_no,chapter_content;
        ImageView edit,delete,share;
        Spinner spinner;
        String temp,temp1;
        public DiaryViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            chapter_no = (TextView)itemView.findViewById(R.id.textView36);
            chapter_content = (TextView)itemView.findViewById(R.id.textView35);
            edit = (ImageView)itemView.findViewById(R.id.imageView10);
            delete = (ImageView)itemView.findViewById(R.id.imageView11);
            share = (ImageView)itemView.findViewById(R.id.imageView12);
            spinner = (Spinner) itemView.findViewById(R.id.spinner3);
            temp = chapter_no.getText().toString();
            temp1 = chapter_content.getText().toString();
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        int position  = getAdapterPosition();
                        //long id = getItemId(position);
                            if (position != RecyclerView.NO_POSITION){
                            listener.onEditOnClick(position);
                        }
                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        int position  = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteOnClick(position,contentsList);
                        }
                    }
                }
            });
        }
    }
}
