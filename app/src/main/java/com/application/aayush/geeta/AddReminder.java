package com.application.aayush.geeta;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddReminder extends Fragment implements View.OnClickListener {

    int hour,minute,alarmTime;
    Toolbar toolbar;
    Bundle bundle;
    TextView time,changeRingtone;
    Switch aSwitch;
    CheckBox repeat,vibrate;
    Button sunday,monday,tuesday,wednesday,thursday,friday,saturday,alarm,delete,open_close;
    RelativeLayout.LayoutParams alarmImage,params,delete_params,openclose_params,param1,day;
    EditText label;
    TextInputLayout textInputLayout;
    String text,text1;
    SharedPreferences sharedPreferences,sharedPreferences1;
    public static final int default_value = 1;
    public static final String default_value1 = "N/A";
    public static final String default_sound = "Default Ringtone";
    ImageButton back;
    boolean isSundayClicked = false,isMondayClicked = false,isTuesdayClicked = false,isWednesdayClicked = false,isThursdayClicked = false,isFridayClicked = false,isSaturdayClicked = false; // You should add a boolean flag to record the button on/off state
    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
    HashMap<Button,Integer> sun,mon,tue,wed,thu,fri,sat;
    public AddReminder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    final View view =  inflater.inflate(R.layout.fragment_add_reminder, container, false);
        final View view =  inflater.inflate(R.layout.layout_add_reminder, container, false);
        bundle = getArguments();
        back = (ImageButton)view.findViewById(R.id.button14);
        sharedPreferences = getActivity().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        sharedPreferences1 = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final int left = (int)getResources().getDimensionPixelSize(R.dimen.ai_left);
        final int top = (int)getResources().getDimensionPixelSize(R.dimen.ai_top);
        final int right = (int)getResources().getDimensionPixelSize(R.dimen.ai_right);
        final int bottom = (int)getResources().getDimensionPixelSize(R.dimen.ai_bottom);
        final int left1 = (int)getResources().getDimensionPixelSize(R.dimen.p_left);
        final int top1 = (int)getResources().getDimensionPixelSize(R.dimen.p_top);
        final int right1 = (int)getResources().getDimensionPixelSize(R.dimen.p_right);
        final int top2 = (int)getResources().getDimensionPixelSize(R.dimen.d_top);
        final int bottom1 = (int)getResources().getDimensionPixelSize(R.dimen.d_bottom);
        final int top3 = (int)getResources().getDimensionPixelSize(R.dimen.oc_top);
        final int right2 = (int)getResources().getDimensionPixelSize(R.dimen.oc_right);
        final int top4 = (int)getResources().getDimensionPixelSize(R.dimen.ai_top1);
        final int top5 = (int)getResources().getDimensionPixelSize(R.dimen.p_top2);
        final int top6 = (int)getResources().getDimensionPixelSize(R.dimen.d_top1);
        final int top7 = (int)getResources().getDimensionPixelSize(R.dimen.oc_top1);

        time = (TextView)view.findViewById(R.id.textView47);
        aSwitch = (Switch)view.findViewById(R.id.switch3);
        repeat = (CheckBox)view.findViewById(R.id.checkBox3);
        vibrate = (CheckBox)view.findViewById(R.id.checkBox4);
        sunday = (Button) view.findViewById(R.id.day1);
        monday = (Button)view.findViewById(R.id.day2);
        tuesday = (Button)view.findViewById(R.id.day3);
        wednesday = (Button)view.findViewById(R.id.day4);
        thursday = (Button)view.findViewById(R.id.day5);
        friday = (Button)view.findViewById(R.id.day6);
        saturday = (Button)view.findViewById(R.id.day7);
        alarm = (Button)view.findViewById(R.id.button17);
        textInputLayout = (TextInputLayout)view.findViewById(R.id.input_layout_label);
        label = (EditText)view.findViewById(R.id.input_label);
        delete = (Button)view.findViewById(R.id.button18);
        open_close = (Button)view.findViewById(R.id.button19);
        param1 = (RelativeLayout.LayoutParams)sunday.getLayoutParams();
        day  = (RelativeLayout.LayoutParams)sunday.getLayoutParams();
        alarmImage = (RelativeLayout.LayoutParams)alarm.getLayoutParams();
        delete_params = (RelativeLayout.LayoutParams)delete.getLayoutParams();
        params = (RelativeLayout.LayoutParams)textInputLayout.getLayoutParams();
        openclose_params = (RelativeLayout.LayoutParams)open_close.getLayoutParams();
        changeRingtone = (TextView)view.findViewById(R.id.textView49);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
        Date currentLocalTime = cal.getTime();

        final int h = currentLocalTime.getHours();
        final int m = currentLocalTime.getMinutes();
        changeRingtone.setText(sharedPreferences.getString("alarm_sound",default_sound));
        aSwitch.setChecked(true);
        sun = new HashMap<>();
        mon = new HashMap<>();
        tue = new HashMap<>();
        wed = new HashMap<>();
        thu=  new HashMap<>();
        fri = new HashMap<>();
        sat = new HashMap<>();
        sun.put(sunday,R.drawable.button_selected);
        mon.put(monday,R.drawable.button_selected);
        tue.put(tuesday,R.drawable.button_selected);
        wed.put(wednesday,R.drawable.button_selected);
        thu.put(thursday,R.drawable.button_selected);
        fri.put(friday,R.drawable.button_selected);
        sat.put(saturday,R.drawable.button_selected);

        changeRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AlarmSound.class));
            }
        });
        repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (repeat.isChecked()){
                    alarmImage.setMargins(left,top,right,bottom);
                    params.setMargins(left1,top1,right,bottom);
                    delete_params.setMargins(left,top2,right,bottom1);
                    openclose_params.setMargins(0,top3,right2,bottom1);
                    sunday.setVisibility(View.VISIBLE);
                    monday.setVisibility(View.VISIBLE);
                    tuesday.setVisibility(View.VISIBLE);
                    wednesday.setVisibility(View.VISIBLE);
                    thursday.setVisibility(View.VISIBLE);
                    friday.setVisibility(View.VISIBLE);
                    saturday.setVisibility(View.VISIBLE);
                    alarm.setLayoutParams(alarmImage);
                    textInputLayout.setLayoutParams(params);
                    delete.setLayoutParams(delete_params);
                    open_close.setLayoutParams(openclose_params);

                }
                else {
                    alarmImage.setMargins(left,top4,right,0);
                    params.setMargins(left1,top5,right1,0);
                    delete_params.setMargins(left,top6,right,bottom1);
                    openclose_params.setMargins(0,top7,right,bottom1);
                    sunday.setVisibility(View.GONE);
                    monday.setVisibility(View.GONE);
                    tuesday.setVisibility(View.GONE);
                    wednesday.setVisibility(View.GONE);
                    thursday.setVisibility(View.GONE);
                    friday.setVisibility(View.GONE);
                    saturday.setVisibility(View.GONE);
                    alarm.setLayoutParams(alarmImage);
                    textInputLayout.setLayoutParams(params);
                    delete.setLayoutParams(delete_params);
                    open_close.setLayoutParams(openclose_params);

                }
            }
        });
/*
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isButtonClicked = !isButtonClicked;
                v.setBackgroundResource(isButtonClicked ? R.drawable.button_normal:R.drawable.button_selected);
            }
        });*/
        sunday.setOnClickListener(this);
        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        wednesday.setOnClickListener(this);
        thursday.setOnClickListener(this);
        friday.setOnClickListener(this);
        saturday.setOnClickListener(this);

        vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putBoolean("vibrate_flag",true);
                }
                else{
                    editor.putBoolean("vibrate_flag",false);
                }
                editor.apply();
            }
        });
        /*
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(AddReminder.this.getContext());
*/
        toolbar = (Toolbar)view.findViewById(R.id.my_toolbar);
/*
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
*/
        if(bundle == null){
            Toast.makeText(AddReminder.this.getContext(),"Null object reference",Toast.LENGTH_SHORT).show();
        }
        else {
            hour = getArguments().getInt("hour");
            minute = getArguments().getInt("minute");
            editor.putInt("hour",hour);
            editor.putInt("minute",minute);
            editor.apply();
            if(hour >= 1 && hour < 12){
                if(minute<10){
                    text = hour+":0"+minute+" a.m. ";
                }
                else {
                    text = hour + ":" + minute + " a.m. ";
                }
            }
            else{
                if(minute<10){
                    text= hour+":0"+minute+" p.m. ";
                }
                else {
                    text = hour + ":" + minute + " p.m. ";
                }
            }
            time.setText(text);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SetReminder.class);
                intent.putExtra("user_name",sharedPreferences1.getString("name",default_value1));
                intent.putExtra("user_mobilenumber",sharedPreferences1.getString("mobile_no",default_value1));
                intent.putExtra("user_email",sharedPreferences1.getString("email_id",default_value1));
                intent.putExtra("user_address",sharedPreferences1.getString("address",default_value1));
                intent.putExtra("user_city",sharedPreferences1.getString("city",default_value1));
                startActivity(intent);
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    int hr = 0,min = 0 ;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    if(Calendar.getInstance().after(calendar)){
                        // Move to tomorrow
                        calendar.add(Calendar.DATE, 1);
                    }
                  /*  if(calendar.before(Calendar.getInstance())) {
                        calendar.add(Calendar.DATE, 1);
                    }*/
                    /*Toast.makeText(getContext(),"Current hour ="+h+":Minute = "+m,  Toast.LENGTH_SHORT).show();
                    //x = hour - hr ;
                    //y = minute - min ;
                    hour = hour - h;
                    minute = minute - m;
*/

                    if(hour>1&&minute>1){
                     text1 =" Alarm set for "+hour+"hours and "+minute+"minutes from now on";
                    }
                    else if(hour<=1 && minute<=1){
                        text1 =" Alarm set for "+hour+"hour and "+minute+"minute from now on";
                    }
                    else if (hour<=1 && minute >1){
                        text1 =" Alarm set for "+hour+"hour and "+minute+"minutes from now on";
                    }
                    else if (hour>1 && minute <1){
                        text1 =" Alarm set for "+hour+"hours and "+minute+"minutes from now on";
                    }
                    //startAlarm(hour,minute);
                    addNotification();
//
                  if(!(isSundayClicked&&isMondayClicked&&isTuesdayClicked&&isWednesdayClicked&&isThursdayClicked&&isFridayClicked&&isSaturdayClicked))
                  {
                      Toast.makeText(AddReminder.this.getContext(),"All selected",Toast.LENGTH_SHORT).show();
                  }
                    alarmTime = hour*3600+minute*60;
                    long xy = calendar.getTimeInMillis() + (long)(alarmTime*1000);
                    Intent intent = new Intent(getActivity(),AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                    //alarmManager.set(AlarmManager.RTC_WAKEUP,x,pendingIntent);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,xy,pendingIntent);
                    //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*24*60*60*1000, pendingIntent);
                    //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);


                }
            }
        });

        //      TimeAdapter ca = new TimeAdapter(createList(1));
//        recyclerView.setAdapter(ca);
        return view;
    }


    private void addNotification() {
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.mipmap.shell)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(getContext(), AddReminder.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.day1:            isSundayClicked = !isSundayClicked;
                v.setBackgroundResource(isSundayClicked ? R.drawable.button_normal:R.drawable.button_selected);
                Toast.makeText(AddReminder.this.getContext(),String.valueOf(isSundayClicked), Toast.LENGTH_SHORT).show();

                break;
            case R.id.day2:isMondayClicked = !isMondayClicked;
                v.setBackgroundResource(isMondayClicked ? R.drawable.button_normal:R.drawable.button_selected);

                break;
            case R.id.day3:isTuesdayClicked = !isTuesdayClicked;
                v.setBackgroundResource(isTuesdayClicked ? R.drawable.button_normal:R.drawable.button_selected);

                break;
            case R.id.day4:isWednesdayClicked = !isWednesdayClicked;
                v.setBackgroundResource(isWednesdayClicked ? R.drawable.button_normal:R.drawable.button_selected);

                break;
            case R.id.day5:isThursdayClicked = !isThursdayClicked;
                v.setBackgroundResource(isThursdayClicked ? R.drawable.button_normal:R.drawable.button_selected);

                break;
            case R.id.day6:isFridayClicked = !isFridayClicked;
                v.setBackgroundResource(isFridayClicked ? R.drawable.button_normal:R.drawable.button_selected);

                break;
            case R.id.day7:isSaturdayClicked = !isSaturdayClicked;
                v.setBackgroundResource(isSaturdayClicked ? R.drawable.button_normal:R.drawable.button_selected);

                break;
            default:break;

        }
    }



/*
    private List<ReminderDetails> createList(int size) {
        String min = " ";
        List<ReminderDetails> result = new ArrayList<ReminderDetails>();
        for (int i=1; i <= size; i++) {
            ReminderDetails ci = new ReminderDetails();
            if(hour >= 1 && hour <= 12){
                if(minute<10){
                   ci.time = hour+":0"+minute+" a.m. ";
                }
                else {
                    ci.time = hour + ":" + minute + " p.m. ";
                }
            }
            else{
                if(minute<10){
                    ci.time = hour+":0"+minute+" p.m. ";
                }
                else {
                    ci.time = hour + ":" + minute + " p.m. ";
                }
            }
            result.add(ci);
        }
        return result;
    }
*/

}
