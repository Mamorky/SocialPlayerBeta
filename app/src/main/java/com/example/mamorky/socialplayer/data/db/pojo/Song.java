package com.example.mamorky.socialplayer.data.db.pojo;

import android.media.Image;

/**
 * Created by mamorky on 30/10/17.
 */

/**
 * Creación de la clase com.example.mamorky.socialplayer.data.db.pojo con los datos */
public class Song {
    private String Name;
    private String Album;
    private String Artists;

    private int ImageAlbum;

    public int getImageAlbum() {
        return ImageAlbum;
    }

    public void setImageAlbum(int imageAlbum) {
        ImageAlbum = imageAlbum;
    }

    public String getArtists() {
        return Artists;
    }

    public int getIdArtists() {
        return IdArtists;
    }

    private int IdSong;
    private int IdAlbum;

    public void setArtists(String artists) {
        Artists = artists;
    }

    public void setIdArtists(int idArtists) {
        IdArtists = idArtists;
    }

    private int IdArtists;
    private Image cover;

    public Song(String name, String album, String artists, int imageAlbum, int idSong, int idAlbum, int idArtists) {
        Name = name;
        Album = album;
        Artists = artists;
        ImageAlbum = imageAlbum;
        IdSong = idSong;
        IdAlbum = idAlbum;
        IdArtists = idArtists;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
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
                "Name='" + Name + '\'' +
                ", Album='" + Album + '\'' +
                ", Artists=" + Artists +
                '}';
    }
}
