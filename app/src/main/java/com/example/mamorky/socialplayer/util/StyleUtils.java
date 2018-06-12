package com.example.mamorky.socialplayer.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.text.format.Time;
import android.util.TypedValue;
import android.view.View;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.base.BaseContext;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mamorky.socialplayer.ui.PrincipalActivity.EDITFLAG;

/**
 * Created by mamorky on 14/01/18.
 */

public class StyleUtils {

    public static int getThemePrimarytColor (final Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme ().resolveAttribute (R.attr.colorPrimary, value, true);
        return value.data;
    }

    public static int getTransparencyColor(String texto){
        char firstCharacter = texto.substring(0,1).charAt(0);
        char lastCharacter = texto.substring(texto.length()-1,texto.length()).charAt(0);

        int transparency = (int) 255%(firstCharacter+lastCharacter);

        return transparency;
    }

    public interface listener_firebase_preferences{
        public void onChangeColor();
    }

    public interface toolBarPropieties{
        void changeToolbarText(String text);
    }
}
