package com.example.mamorky.socialplayer.data.db;

import android.provider.BaseColumns;

/**
 * Created by mamorky on 2/02/18.
 */

public class PlayerContract {
    public static final String DATABASE_PATH = "/data/data/com.android.providers.media/databases/external.db";

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
}
