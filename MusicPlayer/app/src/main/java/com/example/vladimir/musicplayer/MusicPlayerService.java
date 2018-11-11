package com.example.vladimir.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class MusicPlayerService extends Service {
    ArrayList<Track> tracks;
    int curr;
    Callback callback;
    MediaPlayer mp;
    MBinder binder = new MBinder();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    public void init(Callback callback, ArrayList<Track> tracks, int curr) {
        this.tracks = tracks;
        this.callback = callback;
        this.curr = curr;
        start();
    }

    public void start() {
        mp = MediaPlayer.create(this, tracks.get(curr).getId());
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
        callback.songClick(curr);
        mp.release();
        start();
    }

    public void pre() {
        curr--;
        curr = curr == -1 ? tracks.size() - 1 : curr;
        callback.songClick(curr);
        mp.release();
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
        mp.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }
}
