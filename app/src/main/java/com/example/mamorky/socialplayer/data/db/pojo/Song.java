package com.example.mamorky.socialplayer.data.db.pojo;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.mamorky.socialplayer.data.db.repositories.AlbumRepository;
import com.example.mamorky.socialplayer.data.db.repositories.ArtistRepository;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by mamorky on 30/10/17.
 */

/**
 * Creación de la clase com.example.mamorky.socialplayer.data.db.pojo con los datos */
public class Song implements Comparable,Serializable{
    private String _name;

    public int getIdArtists() {
        return IdArtists;
    }

    private int IdSong;
    private int IdAlbum;

    public void setIdArtists(int idArtists) {
        IdArtists = idArtists;
    }

    private int IdArtists;
    private Image cover;

    public Song(String name, int idSong, int idAlbum, int idArtists) {
        _name = name;
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

    public Album getAlbum(){
        return AlbumRepository.getInstance().getAlbumById(this.getIdAlbum());
    }

    public Artist getArtist(){
        return ArtistRepository.getInstance().getArtist(this.getIdArtists());
    }

    @Override
    public String toString() {
        return "Song{" +
                "_name='" + _name + '\'' +
                ", _album='" + getAlbum().getAlbumName() + '\'' +
                ", _artist=" + getArtist().getArtistName() +
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
