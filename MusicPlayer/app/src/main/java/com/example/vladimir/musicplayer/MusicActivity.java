package com.example.vladimir.musicplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

public class MusicActivity extends AppCompatActivity implements Callback, ServiceConnection, SharedPreferences.OnSharedPreferenceChangeListener {
    Button pre;
    Button play;
    Button next;
    TextView tvName;
    TextView tvSinger;
    int curr;
    MusicPlayer mp;
    ArrayList<Track> tracks = MainActivity.getMyList();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(PrefTheme.getTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        pre = findViewById(R.id.btn_pre);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curr--;
                curr = curr == -1 ? tracks.size() - 1 : curr;
                mp.pre();
            }
        });
        next = findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curr++;
                curr = curr == tracks.size() ? 0 : curr;
                mp.next();
            }
        });
        curr = getIntent().getIntExtra("id", 0);
        play = findViewById(R.id.btn_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.play();
            }
        });
        tvName = findViewById(R.id.tv_name);
        tvSinger = findViewById(R.id.tv_singer);
        update();
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
        Intent intent = new Intent(this, MusicPlayer.class);
        startService(intent);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        mp.stop();
        mp = null;
        super.onDestroy();
    }

    @Override
    public void cb(int id) {
        curr = id;
        update();
    }

    public void update() {
        tvName.setText(tracks.get(curr).getName());
        tvSinger.setText(tracks.get(curr).getSinger());
    }
/*

    public void notifytest(View view) {
        invalidateOptionsMenu();
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT > 25) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1414", "channel", importance);
            notificationManager.createNotificationChannel(channel);
            channel.setSound(alarmSound, channel.getAudioAttributes());
        }
        Intent wokeint = new Intent(getApplicationContext(), MusicActivity.class);
        wokeint.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(
                getApplicationContext(),
                123,
                wokeint,
                FLAG_CANCEL_CURRENT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notify);
        remoteViews.setOnClickPendingIntent(R.id.root, pIntent);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1414")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setSound(alarmSound)
                .setCustomContentView(remoteViews);
        Notification notification = builder.build();
        notificationManager.notify(1414, notification);
    }

*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, "Preferences");
        mi.setIntent(new Intent(this, SettingsActivity.class));
        mi.setIcon(R.drawable.ic_settings_applications_black_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicPlayer.MBinder b = (MusicPlayer.MBinder) service;
        mp = b.getService();
        mp.init(this, tracks, curr);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        unbindService(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        recreate();
    }
}
