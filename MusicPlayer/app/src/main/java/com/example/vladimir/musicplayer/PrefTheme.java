package com.example.vladimir.musicplayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

public class PrefTheme {
    public static int getTheme(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String currentTheme = sharedPref.getString("theme","def");
        int themeId = R.style.AppTheme;
        switch (currentTheme) {
            case "Yellow":
                themeId = R.style.Yellow;
                break;
            case "Dark":
                themeId = R.style.Dark;
                break;
            case "White":
                themeId = R.style.AppTheme;
                break;
        }

        return themeId;
    }
}
