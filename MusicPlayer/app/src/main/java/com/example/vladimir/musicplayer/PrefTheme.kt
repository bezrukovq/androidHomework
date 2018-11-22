package com.example.vladimir.musicplayer

import android.content.Context
import android.support.v7.preference.PreferenceManager

object PrefTheme {
    fun getTheme(context: Context): Int {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val currentTheme = sharedPref.getString("theme", "def")
        var themeId = R.style.AppTheme
        when (currentTheme) {
            "Yellow" -> themeId = R.style.Yellow
            "Dark" -> themeId = R.style.Dark
            "White" -> themeId = R.style.AppTheme
        }
        return themeId
    }
}
