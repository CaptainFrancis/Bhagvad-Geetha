package com.application.aayush.geeta;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

/**
 * Created by Praveen K S
 */

public class AlarmReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferences;
    MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In Start Service","Alarm Receiver");
        sharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("vibrate_flag",false)) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(10000);
        }
        String alarmSound = sharedPreferences.getString("alarm_sound", "null");

        Uri mp3 = Uri.parse("android.resource://"+context.getPackageName()+"/raw/"+alarmSound);

        //Uri alarmURI = RingtoneManager.getDefaultUri(R.raw.bgita);

      /*  mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(context,mp3);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        Intent intent1 = new Intent(context,RingtonePlayService.class);
        context.startService(intent1);
       /* mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });*/
        /*//this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent1.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);*/
//        MediaPlayer mediaPlayer = MediaPlayer.create(AlarmReceiver.this.g, R.raw.bgita);
    }
}
