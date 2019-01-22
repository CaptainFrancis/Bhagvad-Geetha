package com.application.aayush.geeta;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Praveen K S
 */

public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;
    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification("It's time to read Geeta");
    }
    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder alarmNotificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(AlarmService.this)
                .setContentTitle("Geeta")
                .setSmallIcon(R.mipmap.shell)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);



        alarmNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alarmNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }
}
