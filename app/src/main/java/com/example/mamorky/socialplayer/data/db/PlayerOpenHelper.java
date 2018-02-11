package com.example.mamorky.socialplayer.data.db;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.mamorky.socialplayer.ui.GeneralPreferences;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.ui.base.BaseContext;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mamorky on 2/02/18.
 */

public class PlayerOpenHelper extends SQLiteOpenHelper{
    private static PlayerOpenHelper mInstance;
    private SQLiteDatabase mDatabase;
    private AtomicInteger mOpenCounter = new AtomicInteger();

    public PlayerOpenHelper() {
        super(BaseContext.getContext(),"external.db", null, 1);
    }


    public static PlayerOpenHelper getInstance() {
        if (mInstance == null)
            mInstance = new PlayerOpenHelper();

        return mInstance;
    }

    public synchronized SQLiteDatabase openDb(){
        if(mOpenCounter.incrementAndGet() == 1)
            mDatabase = SQLiteDatabase.openDatabase(PlayerContract.DATABASE_PATH, null, 0);

        return mDatabase;
    }

    public synchronized void closeDb(){
        if(mOpenCounter.decrementAndGet() == 0){
            mDatabase.close();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys=1");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
