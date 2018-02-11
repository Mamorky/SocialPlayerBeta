package com.example.mamorky.socialplayer.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.util.StyleUtils;

/**
 * Created by mamorky on 13/11/17.
 */

public class BaseActivity extends AppCompatActivity {

    static BaseActivity baseActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.setTheme(StyleUtils.changeTheme(this));
        super.onCreate(savedInstanceState);
        baseActivity = new BaseActivity();
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
