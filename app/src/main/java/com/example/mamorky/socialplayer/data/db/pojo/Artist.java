package com.example.mamorky.socialplayer.data.db.pojo;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by mamorky on 6/11/17.
 */

public class Artist implements Comparable{
    private int idArtist;
    private String artistName;
    private int artistImage;

    public Artist(int idArtist,String artistName, int artistImage) {
        this.idArtist = idArtist;
        this.artistName = artistName;
        this.artistImage = artistImage;
    }

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getArtistImage() {
        return artistImage;
    }

    public void setArtistImage(int artistImage) {
        this.artistImage = artistImage;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return artistName.compareTo(((Artist)o).getArtistName());
    }
}
