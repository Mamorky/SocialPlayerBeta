package com.example.mamorky.socialplayer.data.db.pojo;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.mamorky.socialplayer.data.db.repositories.AlbumRepository;
import com.example.mamorky.socialplayer.data.db.repositories.ArtistRepository;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by mamorky on 30/10/17.
 */

/**
 * Creación de la clase com.example.mamorky.socialplayer.data.db.pojo con los datos */
public class Song implements Comparable,Serializable{
    private int IdSong;
    private String pathSong;
    private String _name;
    private Date dateAdded;
    private long duration;
    private int IdArtists;
    private int IdAlbum;
    private String artist_name;
    private String album_name;
    private long size;
    private String cover;

    public Song(){

    }

    public Song(String name, int idSong, int idAlbum, int idArtists) {
        _name = name;
        IdSong = idSong;
        IdAlbum = idAlbum;
        IdArtists = idArtists;
    }

    public Song(int idSong, String pathSong, String _name, Date dateAdded, long duration, int idArtists, int idAlbum, String artist_name, String album_name,long size) {
        IdSong = idSong;
        this.pathSong = pathSong;
        this._name = _name;
        this.dateAdded = dateAdded;
        this.duration = duration;
        IdArtists = idArtists;
        IdAlbum = idAlbum;
        this.artist_name = artist_name;
        this.album_name = album_name;
        this.size = size;
    }

    public int getIdArtists() {
        return IdArtists;
    }

    public void setIdArtists(int idArtists) {
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public Album getAlbum(){
        return AlbumRepository.getInstance().getAlbumById(this.getIdAlbum());
    }

    public Artist getArtist(){
        return ArtistRepository.getInstance().getArtist(this.getIdArtists());
    }

    public String getPathSong() {
        return pathSong;
    }

    public void setPathSong(String pathSong) {
        this.pathSong = pathSong;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
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
