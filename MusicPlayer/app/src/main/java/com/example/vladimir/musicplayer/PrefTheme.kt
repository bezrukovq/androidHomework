package com.example.vladimir.musicplayer

import android.content.Context
import android.support.v7.preference.PreferenceManager

object PrefTheme {
    fun getTheme(context: Context): Int {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val currentTheme = sharedPref.getString("theme", "def")
        return when (currentTheme) {
            "Yellow" -> R.style.Yellow
            "Dark" -> R.style.Dark
            else -> R.style.AppTheme
        }
    }
}
