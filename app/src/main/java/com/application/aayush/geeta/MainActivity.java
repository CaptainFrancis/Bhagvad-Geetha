package com.application.aayush.geeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
TextView textView1,textView2;
    boolean flag = false,login_flag = false;
    DataBaseHandlerShloka db;
    DatabaseHandler user;
    int count = 0;
    SQLiteDatabase db1;
    public static final String DEFAULT = "N/A";
    public static final boolean default_value = false;

    Bundle bundle;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//removes the notofication bar
        setContentView(R.layout.activity_main);
        textView1 = (TextView)findViewById(R.id.text1);
        textView2 = (TextView)findViewById(R.id.textView27);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Hairline.ttf");
        sharedPreferences = getSharedPreferences("app_data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        textView1.setTypeface(custom_font);
        PackageManager packageManager =  getApplicationContext().getPackageManager();
        long updateTimeInMilliseconds = 0; // install time is conveniently provided in milliseconds
        Date updateDate = null;
        String updateDateString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        try {
            ApplicationInfo appInfo = packageManager.getApplicationInfo(getApplicationContext().getPackageName(), 0);
            String appFile = appInfo.sourceDir;
            updateTimeInMilliseconds = new File(appFile).lastModified();
            sdf.format(updateTimeInMilliseconds);
            sdf1.format(updateTimeInMilliseconds);
        } catch (PackageManager.NameNotFoundException e) {
            updateDate = new Date(0);
            e.printStackTrace();
        }
        editor.putString("last_modified_date",sdf.format(updateTimeInMilliseconds));
        editor.putString("last_modified_time",sdf1.format(updateTimeInMilliseconds));
        editor.apply();
      /*  Toast.makeText(getApplicationContext(),String.valueOf(installed),Toast.LENGTH_LONG).show();
        System.out.print(installed);
        if (flag == false){
            //Inserting Shlokas

               Log.d("Insert","Inserting Shlokas...");
                try {
                  //  readData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            flag = true;
        }*/

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                user = new DatabaseHandler(getApplicationContext());
               // user.deleteUser(new UserProfile(0,"","","","",""));
                int count = user.userCount();
                SharedPreferences sharedPreferences1 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                SharedPreferences sharedPreferences = getSharedPreferences("app_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                login_flag = sharedPreferences.getBoolean("login_flag",default_value);
                int count1 = sharedPreferences1.getAll().size();
                if( count1 == 0 && !login_flag ){
                //          startActivity(new Intent(MainActivity.this, FirstScreen.class));
                    startActivity(new Intent(MainActivity.this, ThirdScreen.class));
                }
                else if ( count1 != 0 && !login_flag ){
                    Intent intent = new Intent(MainActivity.this,ThirdScreen.class);
                    intent.putExtra("user_name",sharedPreferences1.getString("name",DEFAULT));
                    intent.putExtra("user_mobilenumber",sharedPreferences1.getString("mobile_no",DEFAULT));
                    intent.putExtra("user_email",sharedPreferences1.getString("email_id",DEFAULT));
                    intent.putExtra("user_address",sharedPreferences1.getString("address",DEFAULT));
                    intent.putExtra("user_city",sharedPreferences1.getString("city",DEFAULT));
                    startActivity(intent);

                }
                else if ( count1 != 0 && login_flag ){
//                    System.out.println("count="+count1);
                    Intent intent = new Intent(MainActivity.this,UserMenu.class);
                    intent.putExtra("user_name",sharedPreferences1.getString("name",DEFAULT));
                    intent.putExtra("user_mobilenumber",sharedPreferences1.getString("mobile_no",DEFAULT));
                    intent.putExtra("user_email",sharedPreferences1.getString("email_id",DEFAULT));
                    intent.putExtra("user_address",sharedPreferences1.getString("address",DEFAULT));
                    intent.putExtra("user_city",sharedPreferences1.getString("city",DEFAULT));
                    startActivity(intent);

                }
            }
        }, 3000);

    }


}