package com.example.mamorky.socialplayer.data.db.pojo;

import java.util.ArrayList;

/**
 * Created by mamorky on 5/11/17.
 */

/**
 * Clase que de estructura de un Album*/
public class Album implements Comparable{
    private ArrayList<Song> songs;
    private String albumName;
    private int albumImage;
    private int albumId;

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    private String albumArtist;

    public Album(int albumId, String albumName, int albumImage, String albumArtist) {
        this.albumName = albumName;
        this.albumImage = albumImage;
        this.albumArtist = albumArtist;
        this.albumId = albumId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(int albumImage) {
        this.albumImage = albumImage;
    }

    @Override
    public int compareTo(Object o) {
        return albumName.compareTo(((Album)o).getAlbumName());
    }
}