package com.example.vladimir.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Callback, SharedPreferences.OnSharedPreferenceChangeListener {
    TrackAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(PrefTheme.getTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);
        /*Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);*/
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        appAdapter = new TrackAdapter(new AppListDiffCallback(), this);
        mRecyclerView.setAdapter(appAdapter);
        appAdapter.submitList(getMyList());
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    public static ArrayList<Track> getMyList() {
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Bad Habbits", R.raw.bh, "They."));
        tracks.add(new Track("Fried Rice", R.raw.fr, "G-Easy"));
        tracks.add(new Track("Dancefloor Champion", R.raw.dc, "Yellow Claw"));
        tracks.add(new Track("Dancehal Soldier", R.raw.ds, "Yellow Claw"));
        tracks.add(new Track("Дедлайн", R.raw.ddl, "Научно-Технический реп"));
        tracks.add(new Track("Hello-World", R.raw.hw, "Научно-Технический реп"));
        return tracks;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, "Preferences");
        mi.setIntent(new Intent(this, SettingsActivity.class));
        mi.setIcon(R.drawable.ic_settings_applications_black_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void cb(int id) {
        setTheme(R.style.Yellow);
        Intent intent = new Intent(this, MusicActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        recreate();
    }
}
