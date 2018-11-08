package com.example.vladimir.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class MusicPlayer {
    ArrayList<Track> tracks;
    int curr;
    Callback callback;
    MediaPlayer mp;

    public MusicPlayer(ArrayList<Track> tracks,Callback where, int curr) {
        this.tracks = tracks;
        this.curr = curr;
        this.callback = where;
    }

    public void start() {
        mp = MediaPlayer.create((Context) callback, tracks.get(curr).getId());
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                next();
            }
        });
    }

    public void next() {
        curr++;
        curr = curr == tracks.size() ? 0 : curr;
        callback.cb(curr);
        mp.stop();
        start();
    }

    public void pre() {
        curr--;
        curr = curr == -1 ? tracks.size() - 1 : curr;
        mp.stop();
        start();
    }

    public void play() {
        if (mp.isPlaying()) {
            mp.pause();
        } else {
            mp.start();
        }
    }

    public void stop() {
        mp.stop();
    }
}
