package com.example.mamorky.socialplayer.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.mamorky.socialplayer.R;

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
}
