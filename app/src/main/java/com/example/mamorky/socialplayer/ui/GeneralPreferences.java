package com.example.mamorky.socialplayer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.util.StyleUtils;

/**
 * Created by mamorky on 14/01/18.
 */

public class GeneralPreferences extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_general);

        Preference myPreference = (Preference) findPreference(getString(R.string.key_mode_night));
        myPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                finishAffinity();
                Intent intent = new Intent(GeneralPreferences.this,PrincipalActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }
}
