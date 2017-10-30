package pojo;

import android.media.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamorky on 30/10/17.
 */

public class Song {
    private String Name;
    private String Album;
    private String Artists;

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

    public Song(String name, String album, String artists, int idSong, int idAlbum, int idArtists) {
        Name = name;
        Album = album;
        Artists = artists;
        IdSong = idSong;
        IdAlbum = idAlbum;
        IdArtists = idArtists;
        this.cover = cover;
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
