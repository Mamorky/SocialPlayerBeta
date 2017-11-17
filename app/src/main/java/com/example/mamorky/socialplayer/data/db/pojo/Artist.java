package com.example.mamorky.socialplayer.data.db.pojo;

import android.support.annotation.NonNull;

/**
 * Created by mamorky on 6/11/17.
 */

public class Artist implements Comparable{

    private String artistName;
    private int artistImage;

    public Artist(String artistName, int artistImage) {
        this.artistName = artistName;
        this.artistImage = artistImage;
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
