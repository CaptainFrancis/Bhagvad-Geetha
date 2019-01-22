package com.application.aayush.geeta;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
/*public class SetReminder extends Fragment {*/
public class SetReminder extends AppCompatActivity{
    Button addReminder;
    ImageButton back;
    Toolbar toolbar;
    Bundle bundle;
    String name,mobile_number,email,address,city;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_set_reminder);
        bundle = new Bundle();
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        name = getIntent().getStringExtra("user_name");
        mobile_number = getIntent().getStringExtra("user_mobilenumber");
        email = getIntent().getStringExtra("user_email");
        address = getIntent().getStringExtra("user_address");
        city = getIntent().getStringExtra("user_city");
        back = (ImageButton) findViewById(R.id.button14);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetReminder.super.onBackPressed();
            }
        });
        addReminder = (Button)findViewById(R.id.button8);
        /*cancel = (Button)findViewById(R.id.button9);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserMenu.class);
                intent.putExtra("user_name",name);
                intent.putExtra("user_mobilenumber",mobile_number);
                intent.putExtra("user_email",email);
                intent.putExtra("user_address",address);
                intent.putExtra("user_city",city);
                startActivity(intent);
            }
        });*/
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SetReminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    //    eReminderTime.setText( selectedHour + ":" + selectedMinute);
                   //     Toast.makeText(getApplicationContext(),hour+":"+minute,Toast.LENGTH_SHORT).show();
                        bundle.putInt("hour",selectedHour);
                        bundle.putInt("minute",selectedMinute);
                        // create a frame layout
                        FrameLayout fragmentLayout = new FrameLayout(getApplicationContext());
                        // set the layout params to fill the activity
                        fragmentLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        // set an id to the layout
                          fragmentLayout.setId(R.id.addReminder); // some positive integer
//                        fragmentLayout.setId(R.id.dynamic_layout);
                        // set the layout as Activity content
                        setContentView(fragmentLayout);
                        // Finally , add the fragment
                        AddReminder addReminder = new AddReminder();
//                        DynamicallyAddReminder dynamicallyAddReminder = new DynamicallyAddReminder();
                       addReminder.setArguments(bundle);
//                        dynamicallyAddReminder.setArguments(bundle);
/*
                        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                        Intent myIntent = new Intent(SetReminder.this, AlarmReceiver.class);
                        pendingIntent = PendingIntent.getBroadcast(SetReminder.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        //sets the alarm manager
                        alarmManager.set(AlarmManager.RTC_WAKEUP,mcurrentTime.getTimeInMillis(),pendingIntent);
*/

                        FragmentManager fragmentManager = getSupportFragmentManager();

                        fragmentManager.beginTransaction().add(R.id.addReminder,addReminder).commit();
//                        fragmentManager.beginTransaction().add(R.id.dynamic_layout,dynamicallyAddReminder).commit();
//                        fragmentManager.beginTransaction().replace(R.id.setReminder,addReminder,addReminder.getTag()).commit();

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
}
