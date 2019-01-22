package com.application.aayush.geeta;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aayush on 10/30/2017.
 */

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private List<Item> itemList = Collections.emptyList();
    final int left = 0,
              top = 0,
              right = 0,
              bottom = 0,
              left1 = 0,
              top1 = 0 ,
              right1 = 0,
              top2 = 0,
              bottom1 = 0,
              top3 = 0,
              right2 = 0,
              top4 = 0,
              top5 = 0,
              top6 = 0,
              top7 = 0;
    public ViewAdapter(Context context,List<Item> itemList){
        inflater = LayoutInflater.from(context);
        this.itemList = itemList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View  view = inflater.inflate(R.layout.repeat_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
       // final int left = parent.getContext().getResources().getDimensionPixelSize(R.dimen.ai_top);
        final int left = (int)parent.getResources().getDimensionPixelSize(R.dimen.ai_left);
        final int top = (int)parent.getResources().getDimensionPixelSize(R.dimen.ai_top);
        final int right = (int)parent.getResources().getDimensionPixelSize(R.dimen.ai_right);
        final int bottom = (int)parent.getResources().getDimensionPixelSize(R.dimen.ai_bottom);
        final int left1 = (int)parent.getResources().getDimensionPixelSize(R.dimen.p_left);
        final int top1 = (int)parent.getResources().getDimensionPixelSize(R.dimen.p_top);
        final int right1 = (int)parent.getResources().getDimensionPixelSize(R.dimen.p_right);
        final int top2 = (int)parent.getResources().getDimensionPixelSize(R.dimen.d_top);
        final int bottom1 = (int)parent.getResources().getDimensionPixelSize(R.dimen.d_bottom);
        final int top3 = (int)parent.getResources().getDimensionPixelSize(R.dimen.oc_top);
        final int right2 = (int)parent.getResources().getDimensionPixelSize(R.dimen.oc_right);
        final int top4 = (int)parent.getResources().getDimensionPixelSize(R.dimen.ai_top1);
        final int top5 = (int)parent.getResources().getDimensionPixelSize(R.dimen.p_top2);
        final int top6 = (int)parent.getResources().getDimensionPixelSize(R.dimen.d_top1);
        final int top7 = (int)parent.getResources().getDimensionPixelSize(R.dimen.oc_top1);

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Item  current =  itemList.get(position);

        holder.time.setText(current.time);
        holder.repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.repeat.isChecked()){
                    holder.alarmImage.setMargins(left,top,right,bottom);
                    holder.params.setMargins(left1,top1,right,bottom);
                    holder.delete_params.setMargins(left,top2,right,bottom1);
                    holder.openclose_params.setMargins(0,top3,right2,bottom1);
                    holder.sunday.setVisibility(View.VISIBLE);
                    holder.monday.setVisibility(View.VISIBLE);
                    holder.tuesday.setVisibility(View.VISIBLE);
                    holder.wednesday.setVisibility(View.VISIBLE);
                    holder.thursday.setVisibility(View.VISIBLE);
                    holder.friday.setVisibility(View.VISIBLE);
                    holder.saturday.setVisibility(View.VISIBLE);
                    holder.alarm.setLayoutParams(holder.alarmImage);
                    holder.textInputLayout.setLayoutParams(holder.params);
                    holder.delete.setLayoutParams(holder.delete_params);
                    holder.open_close.setLayoutParams(holder.openclose_params);

                }
                else {
                    holder.alarmImage.setMargins(left,top4,right,0);
                    holder.params.setMargins(left1,top5,right1,0);
                    holder.delete_params.setMargins(left,top6,right,bottom1);
                    holder.openclose_params.setMargins(0,top7,right,bottom1);
                    holder.sunday.setVisibility(View.GONE);
                    holder.monday.setVisibility(View.GONE);
                    holder.tuesday.setVisibility(View.GONE);
                    holder.wednesday.setVisibility(View.GONE);
                    holder.thursday.setVisibility(View.GONE);
                    holder.friday.setVisibility(View.GONE);
                    holder.saturday.setVisibility(View.GONE);
                    holder.alarm.setLayoutParams(holder.alarmImage);
                    holder.textInputLayout.setLayoutParams(holder.params);
                    holder.delete.setLayoutParams(holder.delete_params);
                    holder.open_close.setLayoutParams(holder.openclose_params);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView time;
       Switch aSwitch;
        CheckBox repeat,vibrate;
        Button sunday,monday,tuesday,wednesday,thursday,friday,saturday,alarm,delete,open_close;
        RelativeLayout.LayoutParams alarmImage,params,delete_params,openclose_params,param1,day;
        EditText label;
        TextInputLayout textInputLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView)itemView.findViewById(R.id.textView47);
            aSwitch = (Switch)itemView.findViewById(R.id.switch3);
            repeat = (CheckBox)itemView.findViewById(R.id.checkBox3);
            vibrate = (CheckBox)itemView.findViewById(R.id.checkBox4);
            sunday = (Button) itemView.findViewById(R.id.day1);
            monday = (Button)itemView.findViewById(R.id.day2);
            tuesday = (Button)itemView.findViewById(R.id.day3);
            wednesday = (Button)itemView.findViewById(R.id.day4);
            thursday = (Button)itemView.findViewById(R.id.day5);
            friday = (Button)itemView.findViewById(R.id.day6);
            saturday = (Button)itemView.findViewById(R.id.day7);
            alarm = (Button)itemView.findViewById(R.id.button17);
            textInputLayout = (TextInputLayout)itemView.findViewById(R.id.input_layout_label);
            label = (EditText)itemView.findViewById(R.id.input_label);
            delete = (Button)itemView.findViewById(R.id.button18);
            open_close = (Button)itemView.findViewById(R.id.button19);
            param1 = (RelativeLayout.LayoutParams)sunday.getLayoutParams();
            day  = (RelativeLayout.LayoutParams)sunday.getLayoutParams();
            alarmImage = (RelativeLayout.LayoutParams)alarm.getLayoutParams();
            delete_params = (RelativeLayout.LayoutParams)delete.getLayoutParams();
            params = (RelativeLayout.LayoutParams)textInputLayout.getLayoutParams();
            openclose_params = (RelativeLayout.LayoutParams)open_close.getLayoutParams();
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}