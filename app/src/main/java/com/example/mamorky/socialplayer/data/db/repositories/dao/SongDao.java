package com.example.mamorky.socialplayer.data.db.repositories.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaExtractor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;

import com.example.mamorky.socialplayer.data.db.PlayerContract;
import com.example.mamorky.socialplayer.data.db.PlayerOpenHelper;
import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.data.db.pojo.SongInnerAlbum;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.UtilsImages;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by mamorky on 2/02/18.
 */

public class SongDao {
    ArrayList<Song> mSongs;

    public ArrayList<Song> loadAll() {
        mSongs = new ArrayList<>();

        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        final Cursor cursor = BaseContext.resolver().query(uri, null, null, null, MediaStore.Audio.Media.TITLE);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Song song = new Song();
                    int idAlbum = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                    int songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                    String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String artistName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String pathSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    song.setIdSong(songId);
                    song.set_name(songName);
                    song.setArtist_name(artistName);
                    song.setAlbum_name(albumName);
                    song.setPathSong(pathSong);
                    song.setDuration(duration);
                    song.setIdAlbum(idAlbum);

                    //song.setCover(AlbumDao.getCover(idAlbum));

                    mSongs.add(song);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        return mSongs;
    };

    public ArrayList<Song> loadSongOfAlbum(Album albumtag) {
        mSongs = new ArrayList<>();

        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        final Cursor cursor = BaseContext.resolver().query(uri, null,MediaStore.Audio.Media.ALBUM_ID+"=?",new String[]{String.valueOf(albumtag.getAlbumId())},MediaStore.Audio.Media.TITLE);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Song song = new Song();
                    int songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                    String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String artistName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String pathSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    song.setIdSong(songId);
                    song.set_name(songName);
                    song.setArtist_name(artistName);
                    song.setAlbum_name(albumName);
                    song.setPathSong(pathSong);
                    song.setDuration(duration);
                    song.setIdAlbum(albumtag.getAlbumId());
                    song.setCover(albumtag.getAlbumImage());

                    mSongs.add(song);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        return mSongs;
    }

    public ArrayList<Song> loadSongOfArtist(Artist artisttag) {
        mSongs = new ArrayList<>();

        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        final Cursor cursor = BaseContext.resolver().query(uri, null,MediaStore.Audio.Media.ARTIST_ID+"=?",new String[]{String.valueOf(artisttag.getIdArtist())},MediaStore.Audio.Media.TITLE);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Song song = new Song();
                    int idAlbum = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                    int songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                    String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String artistName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String pathSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                    song.setIdSong(songId);
                    song.set_name(songName);
                    song.setArtist_name(artistName);
                    song.setAlbum_name(albumName);
                    song.setPathSong(pathSong);
                    song.setDuration(duration);
                    song.setIdAlbum(idAlbum);
                    //song.setCover(AlbumDao.getCover(idAlbum));

                    mSongs.add(song);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        return mSongs;
    }
}
