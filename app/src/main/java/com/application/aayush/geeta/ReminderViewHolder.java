package com.application.aayush.geeta;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Aayush on 8/8/2017.
 */

public class ReminderViewHolder extends RecyclerView.ViewHolder {
    TextView time,alarmText;
    Switch aSwitch;

    CheckBox repeat,vibrate;
    Button sunday,monday,tuesday,wednesday,thursday,friday,saturday,alarm;
    public ReminderViewHolder(View itemView) {
        super(itemView);
        time = (TextView)itemView.findViewById(R.id.textView47);
        aSwitch = (Switch)itemView.findViewById(R.id.switch3);
        repeat = (CheckBox)itemView.findViewById(R.id.checkBox3);
        vibrate = (CheckBox)itemView.findViewById(R.id.checkBox4);
        alarm = (Button)itemView.findViewById(R.id.button17);
        alarmText = (TextView)itemView.findViewById(R.id.textView49);
        sunday = (Button) itemView.findViewById(R.id.day1);
        monday = (Button)itemView.findViewById(R.id.day2);
        tuesday = (Button)itemView.findViewById(R.id.day3);
        wednesday = (Button)itemView.findViewById(R.id.day4);
        thursday = (Button)itemView.findViewById(R.id.day5);
        friday = (Button)itemView.findViewById(R.id.day6);
        saturday = (Button)itemView.findViewById(R.id.day7);

    }
}
