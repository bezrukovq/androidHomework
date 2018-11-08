package com.example.vladimir.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity implements Callback {
    Button pre;
    Button play;
    Button next;
    TextView tvName;
    TextView tvSinger;
    int curr;
    MusicPlayer mp;
    ArrayList<Track> tracks = MainActivity.getMyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        mp = new MusicPlayer(tracks,this, curr);
        mp.start();
    }

    @Override
    protected void onDestroy() {
        mp.stop();
        mp = null;
        super.onDestroy();
    }

    @Override
    public void cb(int id) {
        curr=id;
        update();
    }
    public void update(){
        tvName.setText(tracks.get(curr).getName());
        tvSinger.setText(tracks.get(curr).getSinger());
    }
}
