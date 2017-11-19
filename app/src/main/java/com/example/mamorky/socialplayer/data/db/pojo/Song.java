package com.example.mamorky.socialplayer.data.db.pojo;

import android.media.Image;
import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by mamorky on 30/10/17.
 */

/**
 * Creación de la clase com.example.mamorky.socialplayer.data.db.pojo con los datos */
public class Song implements Comparable{
    private String _name;
    private String _album;
    private String _artist;

    private int ImageAlbum;

    public int getImageAlbum() {
        return ImageAlbum;
    }

    public void setImageAlbum(int imageAlbum) {
        ImageAlbum = imageAlbum;
    }

    public String get_artist() {
        return _artist;
    }

    public int getIdArtists() {
        return IdArtists;
    }

    private int IdSong;
    private int IdAlbum;

    public void set_artist(String _artist) {
        this._artist = _artist;
    }

    public void setIdArtists(int idArtists) {
        IdArtists = idArtists;
    }

    private int IdArtists;
    private Image cover;

    public Song(String name, String album, String artists, int imageAlbum, int idSong, int idAlbum, int idArtists) {
        _name = name;
        _album = album;
        _artist = artists;
        ImageAlbum = imageAlbum;
        IdSong = idSong;
        IdAlbum = idAlbum;
        IdArtists = idArtists;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_album() {
        return _album;
    }

    public void set_album(String _album) {
        this._album = _album;
    }

    public int getIdSong() {
        return IdSong;
    }

    public void setIdSong(int idSong) {
        IdSong = idSong;
    }

    public int getIdAlbum() {
        return IdAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        IdAlbum = idAlbum;
    }

    public Image getCover() {
        return cover;
    }

    public void setCover(Image cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Song{" +
                "_name='" + _name + '\'' +
                ", _album='" + _album + '\'' +
                ", _artist=" + _artist +
                '}';
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return _name.compareTo(((Song)o).get_name());
    }


    public static class SongCompareById implements Comparator<Song> {

        @Override
        public int compare(Song s1, Song s2) {
            return Integer.compare(s1.getIdSong(),s2.getIdSong());
        }
    }

    public static class SongCompareByIdArtitst implements Comparator<Song> {

        @Override
        public int compare(Song s1, Song s2) {
            return Integer.compare(s1.getIdArtists(),s2.getIdArtists());
        }
    }

    public static class SongCompareByIdAlbum implements Comparator<Song> {

        @Override
        public int compare(Song s1, Song s2) {
            return Integer.compare(s1.getIdAlbum(),s2.getIdAlbum());
        }
    }
}
