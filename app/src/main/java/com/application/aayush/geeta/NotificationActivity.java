package com.application.aayush.geeta;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Aayush on 3/11/2018.
 */

public class NotificationActivity extends AppCompatActivity {
    Toolbar toolbar;
    String name, mobile_number, email, address, city;
    TextView title, notify, last_modified_time;
    ImageButton navigate;
    SharedPreferences sharedPreferences;
    public static final String DEFAULT = "N/A";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lato-Regular.ttf");
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        title = (TextView)findViewById(R.id.toobar_title);
        notify = (Button)findViewById(R.id.notification_button1);
        last_modified_time = (TextView)findViewById(R.id.textView3);
        sharedPreferences = getSharedPreferences("app_data", Context.MODE_PRIVATE);
        last_modified_time.setText(sharedPreferences.getString("last_modified_date",DEFAULT)+","+sharedPreferences.getString("last_modified_time",DEFAULT));
        title.setTypeface(customFont);
        navigate = (ImageButton) findViewById(R.id.button14);
        name = getIntent().getStringExtra("user_name");
        mobile_number = getIntent().getStringExtra("user_mobilenumber");
        email = getIntent().getStringExtra("user_email");
        address = getIntent().getStringExtra("user_address");
        city = getIntent().getStringExtra("user_city");
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationActivity.super.onBackPressed();
            }
        });


    }
}
