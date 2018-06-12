package com.example.mamorky.socialplayer.data.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

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
        super(BaseContext.getContext(),PlayerContract.DATABASE_NAME, null, PlayerContract.DATABASE_VERSION);
    }


    public static PlayerOpenHelper getInstance() {
        if (mInstance == null)
            mInstance = new PlayerOpenHelper();

        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        db.execSQL(PlayerContract.PlaylistEntry.CREATE_TABLE);
        db.execSQL(PlayerContract.PlaylistEntry.INSERT_ENTRIES);

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();

        db.execSQL(PlayerContract.PlaylistEntry.DELETE_ENTRIES);
        onCreate(db);

        db.setTransactionSuccessful();
        db.endTransaction();
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

    public synchronized SQLiteDatabase openDb(){
        if(mOpenCounter.incrementAndGet() == 1)
            mDatabase = getWritableDatabase();

        return mDatabase;
    }

    public synchronized void closeDb(){
        if(mOpenCounter.decrementAndGet() == 0){
            mDatabase.close();
        }
    }
}
