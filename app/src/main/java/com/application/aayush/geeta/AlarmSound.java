package com.application.aayush.geeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by Praveen K S
 */

public class AlarmSound extends AppCompatActivity {
    ListView listView;
    ImageButton back;
    SharedPreferences sharedPreferences;
    Bundle bundle;
    public static final int default_value = 0;
    int hour = 0,minute = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
        setContentView(R.layout.layout_alarm_sounds);
        listView = (ListView) findViewById(R.id.sounds);
        back = (ImageButton)findViewById(R.id.button14);
        final String[] alarm_sounds = listRaw();
        CustomList adapter = new CustomList(AlarmSound.this, alarm_sounds);
        listView.setAdapter(adapter);
        sharedPreferences = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        hour = sharedPreferences.getInt("hour",default_value);
        minute = sharedPreferences.getInt("minute",default_value);
        bundle.putInt("hour",hour);
        bundle.putInt("minute",minute);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a frame layout
                FrameLayout fragmentLayout = new FrameLayout(AlarmSound.this);
                // set the layout params to fill the activity
                fragmentLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                // set an id to the layout
                fragmentLayout.setId(R.id.addReminder);
                // set the layout as Activity content
                setContentView(fragmentLayout);
                // Finally , add the fragment
                AddReminder addReminder = new AddReminder();
                addReminder.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.addReminder,addReminder).commit();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editor.putString("alarm_sound",alarm_sounds[position]);
                editor.apply();
                //Toast.makeText(getApplicationContext(),alarm_sounds[position],Toast.LENGTH_LONG).show();
            }
        });
    }
    public String[] listRaw(){
        Field[] fields=R.raw.class.getFields();
        int count = fields.length;
        String alarmSounds[] = new String[count];
        for(int i=0; i < count; i++){
            alarmSounds[i] = fields[i].getName();
        }
        return alarmSounds;
    }
}
