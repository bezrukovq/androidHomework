package com.example.vladimir.musicplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

import java.util.ArrayList

class MusicPlayerService : Service() {
    internal var tracks: ArrayList<Track>
    internal var curr: Int = 0
    internal var callback: Callback
    internal var mp: MediaPlayer
    internal var binder = MBinder()

    override fun onDestroy() {
        super.onDestroy()
        mp.release()
    }

    fun init(callback: Callback, tracks: ArrayList<Track>, curr: Int) {
        this.tracks = tracks
        this.callback = callback
        this.curr = curr
        start()
    }

    fun start() {
        mp = MediaPlayer.create(this, tracks[curr].id)
        mp.start()
        mp.setOnCompletionListener { next() }
    }

    operator fun next() {
        curr++
        curr = if (curr == tracks.size) 0 else curr
        callback.songClick(curr)
        mp.release()
        start()
    }

    fun pre() {
        curr--
        curr = if (curr == -1) tracks.size - 1 else curr
        callback.songClick(curr)
        mp.release()
        start()
    }

    fun play() {
        if (mp.isPlaying) {
            mp.pause()
        } else {
            mp.start()
        }
    }

    fun stop() {
        mp.release()
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    inner class MBinder : Binder() {
        val service: MusicPlayerService
            get() = this@MusicPlayerService
    }
}
