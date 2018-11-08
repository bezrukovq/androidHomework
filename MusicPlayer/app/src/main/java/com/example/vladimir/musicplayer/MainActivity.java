package com.example.vladimir.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Callback {
    TrackAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        appAdapter = new TrackAdapter(new AppListDiffCallback(), this);
        mRecyclerView.setAdapter(appAdapter);
        appAdapter.submitList(getMyList());
    }

    public static ArrayList<Track> getMyList() {
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Bad Habbits", R.raw.bh, "They."));
        tracks.add(new Track("Fried Rice", R.raw.fr, "G-Easy"));
        tracks.add(new Track("Dancefloor Champion",R.raw.dc,"Yellow Claw"));
        tracks.add(new Track("Dancehal Soldier",R.raw.ds,"Yellow Claw"));
        tracks.add(new Track("Дедлайн",R.raw.ddl,"Научно-Технический реп"));
        tracks.add(new Track("Hello-World",R.raw.hw,"Научно-Технический реп"));
        return tracks;
    }

    @Override
    public void cb(int id) {
        Intent intent = new Intent(this, MusicActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
