package com.example.vladimir.musicplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import java.util.*

class MusicPlayerService : Service() {
    private lateinit var tracks: ArrayList<Track>
    private var curr: Int = 0
    private lateinit var callback: Callback
    private lateinit var mp: MediaPlayer
    private var binder = MBinder()

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

    override fun onBind(intent: Intent) = binder

    inner class MBinder : Binder() {
        val service: MusicPlayerService
            get() = this@MusicPlayerService
    }
}
