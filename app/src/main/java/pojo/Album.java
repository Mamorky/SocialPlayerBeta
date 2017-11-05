package pojo;

import java.util.ArrayList;

/**
 * Created by mamorky on 5/11/17.
 */

/**
 * Clase que de estructura de un Album*/
public class Album {
    private ArrayList<Song> songs;
    private String albumName;
    private int albumImage;

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    private String albumArtist;

    public Album(String albumName, int albumImage,String albumArtist) {
        this.albumName = albumName;
        this.albumImage = albumImage;
        this.albumArtist = albumArtist;
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
}