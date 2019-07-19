package com.kmj.sunrinton19;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;

public class MyService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }

    public MyService(){
        this("");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String asdf=intent.getStringExtra("name");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel", "알람", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, "channel");
        } else {
            builder = new Notification.Builder(this);
        }
            Notification notification= builder
                    .setContentTitle(asdf)
                    .setSmallIcon(R.drawable.app_logo)
    //                .setAutoCancel(true)
    //                .setDefaults(Notification.DEFAULT_VIBRATE)
    //                .setContentText(todoText)
    //                .setContentIntent(PendingIntent.getActivity(context, index, intent1, PendingIntent.FLAG_UPDATE_CURRENT))
                    .build();
        manager.notify(100, notification);
    }
}
