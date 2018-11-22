package com.example.vladimir.musicplayer

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.preference.PreferenceManager
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity(), Callback, SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var appAdapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PrefTheme.getTheme(applicationContext))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appAdapter = TrackAdapter(AppListDiffCallback(), this)
        rv_songs.adapter = appAdapter
        appAdapter.submitList(myList)
        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val mi = menu.add(0, 1, 0, "Preferences")
        mi.intent = Intent(this, SettingsActivity::class.java)
        mi.setIcon(R.drawable.ic_settings_applications_black_24dp)
        return super.onCreateOptionsMenu(menu)
    }

    override fun songClick(id: Int) {
        val intent = Intent(this, MusicActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) = recreate()

    companion object {
        val myList: ArrayList<Track>
            get() {
                val tracks = ArrayList<Track>()
                tracks.add(Track("Bad Habbits", R.raw.bh, "They."))
                tracks.add(Track("Fried Rice", R.raw.fr, "G-Easy"))
                tracks.add(Track("Dancefloor Champion", R.raw.dc, "Yellow Claw"))
                tracks.add(Track("Dancehal Soldier", R.raw.ds, "Yellow Claw"))
                tracks.add(Track("Дедлайн", R.raw.ddl, "Научно-Технический реп"))
                tracks.add(Track("Hello-World", R.raw.hw, "Научно-Технический реп"))
                return tracks
            }
    }
}
