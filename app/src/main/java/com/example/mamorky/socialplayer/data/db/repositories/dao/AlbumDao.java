package com.example.mamorky.socialplayer.data.db.repositories.dao;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.ui.base.BaseContext;

import java.util.ArrayList;

/**
 * Created by mamorky on 3/02/18.
 */

public class AlbumDao {
    ArrayList<Album> mAlbums;

    public ArrayList<Album> loadAll() {
        mAlbums = new ArrayList<>();

        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Cursor cursor = BaseContext.resolver().query(uri, null, null, null, MediaStore.Audio.Albums.ALBUM);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Albums._ID));
                    String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
                    String albumCover = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
                    String albumArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST));

                    Album album = new Album(albumId,albumName,albumCover,albumArtist);

                    mAlbums.add(album);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        return mAlbums;
    };

    public static String getCover(int idAlbum){
        Cursor cursor = BaseContext.resolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[] {MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                MediaStore.Audio.Albums._ID+ "=?",
                new String[] {String.valueOf(idAlbum)},
                null);

        if (cursor.moveToFirst()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
            return path;
        }

        cursor.close();

        return null;
    }
}
