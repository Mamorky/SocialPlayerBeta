package com.example.mamorky.socialplayer.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.view.View;

import com.example.mamorky.socialplayer.R;

/**
 * Created by mamorky on 14/01/18.
 */

public class StyleUtils {

    public static int changeTheme(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        Boolean modoNoche = sharedPreferences.getBoolean(context.getString(R.string.key_mode_night),false);

        if(modoNoche)
            return R.style.AppTheme_Dark;
        else
            return R.style.AppTheme;
    }
}
