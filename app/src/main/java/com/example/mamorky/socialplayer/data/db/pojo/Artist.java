package com.example.mamorky.socialplayer.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by mamorky on 6/11/17.
 */

public class Artist implements Comparable, Parcelable {
    private int idArtist;
    private String artistName;

    public Artist(int idArtist,String artistName, int artistImage) {
        this.idArtist = idArtist;
        this.artistName = artistName;
    }

    protected Artist(Parcel in) {
        idArtist = in.readInt();
        artistName = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

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

    @Override
    public int compareTo(@NonNull Object o) {
        return artistName.compareTo(((Artist)o).getArtistName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.artistName);
        dest.writeInt(this.idArtist);
    }
}
