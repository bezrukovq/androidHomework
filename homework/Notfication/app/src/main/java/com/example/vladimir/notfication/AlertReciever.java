package com.example.vladimir.notfication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;

public class AlertReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT > 25) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1414", "channel", importance);
            notificationManager.createNotificationChannel(channel);
            channel.setSound(alarmSound, channel.getAudioAttributes());
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1414")
                .setContentTitle("Вставай")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setContentText("MOTHERFUCKER")
                .setSound(alarmSound)
                .setVisibility(Notification.VISIBILITY_PRIVATE);
        Intent wokeint = new Intent(context, WokeUpActivity.class);
        wokeint.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(
                context,
                123,
                wokeint,
                FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        notificationManager.notify(1414, notification);
    }
}
