package com.example.mamorky.socialplayer.data.db.pojo;

import java.util.ArrayList;

/**
 * Created by mamorky on 5/11/17.
 */

/**
 * Clase que de estructura de un Album*/
public class Album implements Comparable{
    private String albumName;
    private String albumImage;
    private int albumId;

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    private String albumArtist;

    public Album(int albumId, String albumName, String albumImage, String albumArtist) {
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

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    @Override
    public int compareTo(Object o) {
        return albumName.compareTo(((Album)o).getAlbumName());
    }
}