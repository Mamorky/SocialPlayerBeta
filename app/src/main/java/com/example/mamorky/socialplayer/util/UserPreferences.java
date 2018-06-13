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
    private int num_col_portrait;
    private int num_col_land;

    public UserPreferences(){
    }

    public UserPreferences(Context context){
        this.color  = context.getResources().getColor(R.color.colorPrimary);
        this.num_col_portrait = 2;
        this.num_col_land = 4;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getNum_col_portrait() {
        if(num_col_portrait == 0)
            return 2;
        return num_col_portrait;
    }

    public void setNum_col_portrait(int num_col_portrait) {
        this.num_col_portrait = num_col_portrait;
    }

    public int getNum_col_land() {
        if(num_col_land == 0)
            return 4;
        return num_col_land;
    }

    public void setNum_col_land(int num_col_land) {
        this.num_col_land = num_col_land;
    }
}
