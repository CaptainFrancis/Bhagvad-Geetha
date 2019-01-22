package com.application.aayush.geeta;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Aayush on 8/8/2017.
 */

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ReminderViewHolder> {
    private List<ReminderDetails> reminderDetailsList;
    private Context context;
    boolean clicked=false;
    TextView alarmText;
    CheckBox repeat,vibrate;
    Button alarm,sunday,monday,tuesday,wednesday,thursday,friday,saturday,close,open,delete;
    RelativeLayout.LayoutParams alarmImage,alarmtext,vibratecheckbox,labelText,params;
    CardView.LayoutParams layoutParams;
    CardView cardView;
    RelativeLayout layout;
    public TimeAdapter(List<ReminderDetails> reminderDetailsList) {
        this.reminderDetailsList = reminderDetailsList;

    }



    @Override
    public ReminderViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
        params = new RelativeLayout.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        layoutParams = new CardView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        cardView = (CardView)itemView.findViewById(R.id.card_view);
        layout = (RelativeLayout)itemView.findViewById(R.id.alarm_layout);
        repeat = (CheckBox)itemView.findViewById(R.id.checkBox3);
        sunday = (Button) itemView.findViewById(R.id.day1);
        vibrate = (CheckBox)itemView.findViewById(R.id.checkBox4);
        alarm = (Button)itemView.findViewById(R.id.button17);
        alarmText = (TextView)itemView.findViewById(R.id.textView49);
        monday = (Button)itemView.findViewById(R.id.day2);
        tuesday = (Button)itemView.findViewById(R.id.day3);
        wednesday = (Button)itemView.findViewById(R.id.day4);
        thursday = (Button)itemView.findViewById(R.id.day5);
        friday = (Button)itemView.findViewById(R.id.day6);
        saturday = (Button)itemView.findViewById(R.id.day7);
        close = (Button)itemView.findViewById(R.id.button19);
        alarmImage = (RelativeLayout.LayoutParams) alarm.getLayoutParams();
        alarmtext = (RelativeLayout.LayoutParams) alarmText.getLayoutParams();
        vibratecheckbox = (RelativeLayout.LayoutParams) vibrate.getLayoutParams();
        open = (Button)itemView.findViewById(R.id.hidden_button19);
        delete = (Button)itemView.findViewById(R.id.button18);

        //labelText = (FrameLayout.LayoutParams) label.getLayoutParams();

        repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(repeat.isChecked()){

                    alarmImage.topMargin = 150;
                    alarmtext.topMargin = 150;
                    vibratecheckbox.topMargin = 150;
                    sunday.setVisibility(View.VISIBLE);
                    monday.setVisibility(View.VISIBLE);
                    tuesday.setVisibility(View.VISIBLE);
                    wednesday.setVisibility(View.VISIBLE);
                    thursday.setVisibility(View.VISIBLE);
                    friday.setVisibility(View.VISIBLE);
                    saturday.setVisibility(View.VISIBLE);
                    alarm.setLayoutParams(alarmImage);
                    alarmText.setLayoutParams(alarmtext);
                    vibrate.setLayoutParams(vibratecheckbox);

                }
                else{
                    alarmImage.topMargin = 15;
                    alarmtext.topMargin = 15;
                    vibratecheckbox.topMargin = 15;
                    sunday.setVisibility(View.GONE);
                    monday.setVisibility(View.GONE);
                    tuesday.setVisibility(View.GONE);
                    wednesday.setVisibility(View.GONE);
                    thursday.setVisibility(View.GONE);
                    friday.setVisibility(View.GONE);
                    saturday.setVisibility(View.GONE);
                    alarm.setLayoutParams(alarmImage);
                    alarmText.setLayoutParams(alarmtext);
                    vibrate.setLayoutParams(vibratecheckbox);

                }
            }
        });
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

    Intent intent = new Intent(v.getContext(),Ringtone.class);
                v.getContext().startActivity(intent);


                //Toast.makeText(v.getContext(),"hello ",Toast.LENGTH_SHORT).show();
            }
        });
        alarmText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Ringtone.class);
                v.getContext().startActivity(intent);

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.setVisibility(View.VISIBLE);
                close.setVisibility(View.GONE);
                alarm.setVisibility(View.GONE);
                sunday.setVisibility(View.GONE);
                monday.setVisibility(View.GONE);
                tuesday.setVisibility(View.GONE);
                wednesday.setVisibility(View.GONE);
                thursday.setVisibility(View.GONE);
                friday.setVisibility(View.GONE);
                repeat.setVisibility(View.GONE);
                vibrate.setVisibility(View.GONE);
                alarmText.setVisibility(View.GONE);
            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.setVisibility(View.GONE);
                close.setVisibility(View.VISIBLE);
                alarm.setVisibility(View.VISIBLE);
                sunday.setVisibility(View.VISIBLE);
                monday.setVisibility(View.VISIBLE);
                tuesday.setVisibility(View.VISIBLE);
                wednesday.setVisibility(View.VISIBLE);
                thursday.setVisibility(View.VISIBLE);
                friday.setVisibility(View.VISIBLE);
                repeat.setVisibility(View.VISIBLE);
                vibrate.setVisibility(View.VISIBLE);
                alarmText.setVisibility(View.VISIBLE);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        cardView.removeView(layout);    }
        });

        return new ReminderViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {
        ReminderDetails reminderDetails = reminderDetailsList.get(position);
        holder.time.setText(reminderDetails.time);

    }

    @Override
    public int getItemCount() {
        return reminderDetailsList.size();
    }
    public static class ReminderViewHolder extends RecyclerView.ViewHolder {
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
}
