package com.application.aayush.geeta;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Aayush on 11/12/2017.
 */

public class RingtonePlayService extends Service {
    MediaPlayer mediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent,int flags,int startid){

        Log.e("Local Service","Received start id"+startid+":"+intent);
        Toast.makeText(RingtonePlayService.this.getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();

        //create an instance of the media player
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
