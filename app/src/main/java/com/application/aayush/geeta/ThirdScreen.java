package com.application.aayush.geeta;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Aayush on 4/29/2017.
 */

public class ThirdScreen extends AppCompatActivity {
    TextView heading,content,getStarted;
    public boolean dialogflag = false;
    private GestureDetectorCompat gestureDetectorCompat;
    SharedPreferences sharedPreferences;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.SEND_SMS);

        int receiveSMS = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.RECEIVE_SMS);

        int readSMS = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdscreen);
        //setContentView(R.layout.test);
        checkAndRequestPermissions();//Added to extract the permissions

        heading = (TextView)findViewById(R.id.textView);
        content = (TextView)findViewById(R.id.textView16);
        getStarted = (TextView)findViewById(R.id.textView24);

        sharedPreferences = getSharedPreferences("shloka_data", Context.MODE_PRIVATE);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lato-Regular.ttf");

        heading.setTypeface(customFont);
        content.setTypeface(customFont);
        getStarted.setTypeface(customFont);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               try{
                    readData();
                    finish();
                    dialogflag = true;
                   Intent intent = new Intent(ThirdScreen.this,UserMenu.class);
                   overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                   startActivity(intent);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        gestureDetectorCompat = new GestureDetectorCompat(this,new MyGestureListener());

    }

    protected void readData() throws IOException, JSONException {
        InputStream inputStream = getAssets().open("shloka_details.json");
        Scanner sc = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();
        while(sc.hasNextLine()){
            builder.append(sc.nextLine());
        }
        parseJSON(builder.toString());
    }
    private void parseJSON(String s ) throws JSONException {
        String string1="",string2="",string3="",default_value = "false";
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONObject root = new JSONObject(s);
        JSONObject shloka = root.getJSONObject("Geeta_Shlokas");//define array
        JSONArray items = shloka.getJSONArray("shlokas");
        for(int i = 0;i<items.length();i++){
            JSONObject item = items.getJSONObject(i);
//            System.out.print(items.length());
            string1 = item.getString("verse");
            string2 = item.getString("translation");
            string3 = item.getString("purpose");
            editor.putString("shloka_id",String.valueOf(i));
            editor.putString("shloka_verse",string1);
            editor.putString("accessed_flag",default_value);
            editor.apply();
            DataBaseHandlerShloka db = new DataBaseHandlerShloka(this);
            db.addShloka(new Shlokas(string1,string2,string3));

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.gestureDetectorCompat.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);

    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            return true;
        }
    }
}