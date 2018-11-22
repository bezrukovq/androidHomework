package com.example.vladimir.musicplayer

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceManager
import android.view.Menu
import kotlinx.android.synthetic.main.activity_music.*

class MusicActivity : AppCompatActivity(), Callback, ServiceConnection, SharedPreferences.OnSharedPreferenceChangeListener {
    private var curr: Int = 0
    private var mp: MusicPlayerService? = null
    private var tracks = MainActivity.myList

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PrefTheme.getTheme(applicationContext))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        btn_pre.setOnClickListener {
            curr--
            curr = if (curr == -1) tracks.size - 1 else curr
            mp?.pre()
        }
        btn_next.setOnClickListener {
            curr++
            curr = if (curr == tracks.size) 0 else curr
            mp?.next()
        }
        curr = intent.getIntExtra("id", 0)
        btn_play.setOnClickListener { mp?.play() }
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

    private fun update() {
        tv_name.text = tracks[curr].name
        tv_singer.text = tracks[curr].singer
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
