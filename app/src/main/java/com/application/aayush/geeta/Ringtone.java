package com.application.aayush.geeta;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Aayush on 9/10/2017.
 */

public class Ringtone extends AppCompatActivity {
    Toolbar toolbar;
    TextView title;
    ListView listView;
    Button back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringtonelayout);
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        back = (Button)findViewById(R.id.button14);
        title = (TextView)findViewById(R.id.toobar_title);
        listView = (ListView)findViewById(R.id.ringtone_list);
        final ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.ring_tones,android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Ringtone.this,AddReminder.class));
                AddReminder addReminder = new AddReminder();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.setRingtone,addReminder,addReminder.getTag()).commit();
            }
        });


    }
}
