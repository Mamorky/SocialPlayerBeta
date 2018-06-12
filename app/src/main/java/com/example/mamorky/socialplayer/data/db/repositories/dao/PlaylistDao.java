package com.example.mamorky.socialplayer.data.db.repositories.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.mamorky.socialplayer.data.db.PlayerContract;
import com.example.mamorky.socialplayer.data.db.PlayerOpenHelper;
import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.UtilsImages;

import java.util.ArrayList;

/**
 * Created by mamorky on 26/02/18.
 */

public class PlaylistDao {
    ArrayList<Playlist> mPlaylists;

    Uri uri = android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;

    /*
    public ArrayList<Playlist> loadAll() {
        mPlaylists = new ArrayList<>();

        SQLiteDatabase db = PlayerOpenHelper.getInstance().openDb();
        Cursor cursor = db.query(PlayerContract.PlaylistEntry.TABLE_NAME, PlayerContract.PlaylistEntry.ALL_COLUMN,null,null,null,null,null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int playlistId = cursor.getInt(0);
                    String playlistNombre = cursor.getString(1);

                    Bitmap bitmap = null;

                    if(cursor.getBlob(2) != null)
                       bitmap = UtilsImages.getBitmapFromByte(cursor.getBlob(2));

                    Playlist playlist;

                    if(bitmap == null)
                       playlist = new Playlist(playlistId,playlistNombre);
                    else
                        playlist = new Playlist(playlistId,playlistNombre,bitmap);

                    mPlaylists.add(playlist);
                } while (cursor.moveToNext());
            }
        }

        PlayerOpenHelper.getInstance().closeDb();

        return mPlaylists;
    };*/

    public ArrayList<Playlist> loadAll() {
        mPlaylists = new ArrayList<>();

        final Cursor cursor = BaseContext.resolver().query(uri,null,null,null,null,null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int idPlaylist = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Playlists._ID));
                    String playlistNombre = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.NAME));

                    Playlist playlist = new Playlist(idPlaylist,playlistNombre,null);

                    mPlaylists.add(playlist);
                } while (cursor.moveToNext());
            }
        }

        cursor.close();

        return mPlaylists;
    };

    public long addPlaylist(Playlist playlist){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Audio.Playlists.NAME,playlist.getNamePlaylist());

        BaseContext.resolver().insert(uri,contentValues);

        ContentValues mInserts = new ContentValues();
        mInserts.put(MediaStore.Audio.Playlists.NAME,playlist.getNamePlaylist());
        mInserts.put(MediaStore.Audio.Playlists.DATE_ADDED, System.currentTimeMillis());
        mInserts.put(MediaStore.Audio.Playlists.DATE_MODIFIED, System.currentTimeMillis());
        Uri mUri = BaseContext.resolver().insert(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, mInserts);

        /*
        if (mUri != null) {
            int mPlaylistId = -1;
            Cursor c = BaseContext.resolver().query(mUri, PROJECTION_PLAYLIST, null, null, null);
            if (c != null) {
                // Save the newly created ID so it can be selected.  Names are allowed to be duplicated,
                // but IDs can never be.
                mPlaylistId = c.getInt(c.getColumnIndex(MediaStore.Audio.Playlists._ID));
                c.close();
            }
        }

        return 1;*/

        return 0;
    }

    public long deletePlaylist(Playlist playlist){
        String whereClause = PlayerContract.PlaylistEntry._ID+"=?";
        String[] whereArgs = new String[]{String.valueOf(playlist.getIdPlaylist())};

        long rows = BaseContext.resolver().delete(uri,whereClause,whereArgs);
        return rows;
    }

    public long updatePlaylist(Playlist playlist){

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Audio.Playlists.NAME,playlist.getNamePlaylist());

        String whereClause = PlayerContract.PlaylistEntry._ID+"=?";
        String[] whereArgs = new String[]{String.valueOf(playlist.getIdPlaylist())};

        long rows = BaseContext.resolver().update(uri,contentValues,whereClause,whereArgs);
        return rows;
    }
}
