package com.example.mamorky.socialplayer.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.ui.base.BaseContext;

import java.io.Serializable;

public class UserPreferences implements Serializable{

    private int color = -1;

    public UserPreferences(){
    }

    public UserPreferences(Context context){
        this.color  = context.getResources().getColor(R.color.colorPrimary);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
