package com.example.mamorky.socialplayer.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.PrincipalActivity;
import com.example.mamorky.socialplayer.util.GoogleSignInUtilities;
import com.example.mamorky.socialplayer.util.UserPreferences;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

/**
 * Created by mamorky on 13/11/17.
 */

public class BaseActivity extends AppCompatActivity {
    public static BaseActivity baseActivity;

    public ProgressDialog progressDialogPreferences;
    public UserPreferences preferences;

    private String TAG_USER_PREF = "TAG_USER_PREF";
    private static boolean esPrimeraEjecucion = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY);
        if(AppCompatDelegate.getDefaultNightMode() == -1)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        super.onCreate(savedInstanceState);
        baseActivity = new BaseActivity();

        if(preferences == null){
            preferences = new UserPreferences();
            preferences.setColor(getResources().getColor(R.color.colorPrimary));
            esPrimeraEjecucion=false;
        }

        progressDialogPreferences = new ProgressDialog(this);
    }

    public void onError(String message){
        Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_SHORT).show();
    }

    public void onError(int resId){
        Snackbar.make(findViewById(android.R.id.content), getResources().getString(resId), Snackbar.LENGTH_SHORT).show();
    }

    public static Context getContext(){
        return baseActivity.getApplicationContext();
    }
}
