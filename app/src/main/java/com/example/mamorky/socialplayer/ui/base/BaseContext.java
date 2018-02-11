package com.example.mamorky.socialplayer.ui.base;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by mamorky on 2/02/18.
 */

public class BaseContext extends Application{
        private static Context mContext;

        public static Context getContext() {
            return mContext;
        }

        public static void setContext(Context mContexto) {
            mContext = mContexto;
        }

        public static ContentResolver resolver(){
            return mContext.getContentResolver();
        }
}
