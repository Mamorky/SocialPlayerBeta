package com.example.mamorky.socialplayer.data.db;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.BaseColumns;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.UtilsImages;

import java.util.Arrays;

/**
 * Created by mamorky on 2/02/18.
 */

public class PlayerContract {
    public static final String DATABASE_NAME = "socialPlayer.db";
    public static final int DATABASE_VERSION = 3;

    //Por cada tabla se crea una clase que implementa la interfaz BaseColumns.
    public static class AudioEntry implements BaseColumns {

        public static final String TABLE_NAME = "audio";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATEADD = "date_added";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_ARTISTID = "artist_id";
        public static final String COLUMN_ALBUMID = "album_id";
        public static final String COLUMN_ARTIST = "artist";
        public static final String COLUMN_ALBUM = "album";


        public static final String[] ALL_COLUMN = new String[]{
                COLUMN_ID,COLUMN_DATA,COLUMN_TITLE,COLUMN_DATEADD,COLUMN_DURATION,COLUMN_ARTISTID,COLUMN_ALBUMID,COLUMN_ARTIST,COLUMN_ALBUM
        };

        public static final String ORDER_BY = "title";
    }

    public static class PlaylistEntry implements BaseColumns{
        public static final String TABLE_NAME = "playlist";
        public static final String COLUMN_NAME = "nombre";
        public static final String COLUMN_IMG = "imagen";

        public static final String[] ALL_COLUMN = new String[]{
                _ID,COLUMN_NAME,COLUMN_IMG
        };

        public static final String CREATE_TABLE = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL," +
                "%s blob)",
                TABLE_NAME,
                _ID,
                COLUMN_NAME,
                COLUMN_IMG);

        public static final String INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s) values ('%s',%s)",
                TABLE_NAME,COLUMN_NAME,COLUMN_IMG,
                "veranito",
                null);

        public static final String DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
    }
}
