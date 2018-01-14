package com.example.mamorky.socialplayer.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.mamorky.socialplayer.R;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by mamorky on 12/11/17.
 */

public class UtilsImages {
    public static RoundedBitmapDrawable roundImages(ImageView imgView,int imagePath,int corner){
        Drawable drawable = imgView.getResources().getDrawable(imagePath);
        Bitmap bitmap = (((BitmapDrawable)drawable).getBitmap());
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(imgView.getResources(),bitmap);
        roundedBitmapDrawable.setCornerRadius(corner);

        return roundedBitmapDrawable;
    }

    public static RoundedBitmapDrawable roundImages(ImageView imgView,int imagePath){
        Drawable drawable = imgView.getResources().getDrawable(imagePath);
        Bitmap bitmap = (((BitmapDrawable)drawable).getBitmap());
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(imgView.getResources(),bitmap);
        roundedBitmapDrawable.setCornerRadius(bitmap.getHeight());

        return roundedBitmapDrawable;
    }

    public static int colorAlea(){
        int r;
        int g;
        int b;

        r = (int) Math.round(Math.random()*255);
        g = (int) Math.round(Math.random()*255);
        b = (int) Math.round(Math.random()*255);

        return Color.rgb(r,b,g);
    }
}
