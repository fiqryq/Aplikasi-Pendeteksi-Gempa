package com.autumnleaves.pendeteksigempa;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_NAME = "Main Notifications";
    private static final String CHANNEL_DESCRIPTION = "Notification description";
    private static final String CHANNEL_ID = "NotificationChannelbaru";
    private static NotificationManager notificationManager;
    private Context base;
    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

    public NotificationHelper(Context base) {
        super(base);
        this.base = base;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {

        notificationChannel.setDescription(CHANNEL_DESCRIPTION);
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.getSound();
        notificationChannel.canShowBadge();
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//        notificationChannel.setLightColor(getResources().getColor(R.color.colorAccent));
        getNotificationManager().createNotificationChannel(notificationChannel);
    }
    public NotificationManager getNotificationManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) base.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
    // now lets create notification
    public void notify(String message, String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(base, CHANNEL_ID);
        Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);


//        Uri uri = Uri.parse("android.resource://"+this.getPackageName()+"/" + R.raw.aperturaabductores);
//
//        AudioAttributes att = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
//                .build();
//        notificationChannel.setSound(uri,att);

        builder.setContentTitle(title);
        builder.setSmallIcon(R.drawable.earthquake);
        builder.setContentText(message);
        builder.setSound(notificationUri);
        builder.setVibrate(new long[]{1000,1000,1000,1000});
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        getNotificationManager().notify(9001, builder.build());
    }
}
