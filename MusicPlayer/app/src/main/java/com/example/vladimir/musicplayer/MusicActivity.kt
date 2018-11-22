package com.example.vladimir.musicplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.SharedPreferences
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

import java.util.ArrayList

class MusicActivity : AppCompatActivity(), Callback, ServiceConnection, SharedPreferences.OnSharedPreferenceChangeListener {
    internal lateinit var pre: Button
    internal lateinit var play: Button
    internal lateinit var next: Button
    internal lateinit var tvName: TextView
    internal lateinit var tvSinger: TextView
    internal var curr: Int = 0
    internal var mp: MusicPlayerService? = null
    internal var tracks = MainActivity.myList

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PrefTheme.getTheme(applicationContext))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        pre = findViewById(R.id.btn_pre)
        pre.setOnClickListener {
            curr--
            curr = if (curr == -1) tracks.size - 1 else curr
            mp?.pre()
        }
        next = findViewById(R.id.btn_next)
        next.setOnClickListener {
            curr++
            curr = if (curr == tracks.size) 0 else curr
            mp?.next()
        }
        curr = intent.getIntExtra("id", 0)
        play = findViewById(R.id.btn_play)
        play.setOnClickListener { mp?.play() }
        tvName = findViewById(R.id.tv_name)
        tvSinger = findViewById(R.id.tv_singer)
        update()
        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        prefs.registerOnSharedPreferenceChangeListener(this)
        val intent = Intent(this, MusicPlayerService::class.java)
        startService(intent)
        bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        mp?.stop()
        mp = null
        super.onDestroy()
    }

    override fun songClick(id: Int) {
        curr = id
        update()
    }

    fun update() {
        tvName.text = tracks[curr].name
        tvSinger.text = tracks[curr].singer
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val mi = menu.add(0, 1, 0, "Preferences")
        mi.intent = Intent(this, SettingsActivity::class.java)
        mi.setIcon(R.drawable.ic_settings_applications_black_24dp)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onServiceConnected(name: ComponentName, service: IBinder) {
        val b = service as MusicPlayerService.MBinder
        mp = b.service
        mp?.init(this, tracks, curr)
    }

    override fun onServiceDisconnected(name: ComponentName) {
        unbindService(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        recreate()
    }
}
