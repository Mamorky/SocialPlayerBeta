package com.example.mamorky.socialplayer.data.db.repositories.dao;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.ui.base.BaseContext;

import java.util.ArrayList;

/**
 * Created by mamorky on 22/02/18.
 */

public class ArtistDao {
    ArrayList<Artist> artists;

    public ArrayList<Artist> loadAll() {
        artists = new ArrayList<>();

        Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        Cursor cursor = BaseContext.resolver().query(uri, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int idArtist = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Artists._ID));
                    String artistName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST));

                    Artist artist = new Artist(idArtist,artistName,0);

                    artists.add(artist);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        return artists;
    };
}
