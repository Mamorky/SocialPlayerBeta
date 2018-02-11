package com.example.mamorky.socialplayer.data.db.pojo;

/**
 * Created by mamorky on 3/02/18.
 */

public class SongInnerAlbum {
    private Song song;
    private Album album;

    public SongInnerAlbum(Song song, Album album) {
        this.song = song;
        this.album = album;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
