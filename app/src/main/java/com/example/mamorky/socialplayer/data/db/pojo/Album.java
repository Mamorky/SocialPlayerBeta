package com.example.mamorky.socialplayer.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by mamorky on 5/11/17.
 */

/**
 * Clase que de estructura de un Album*/
public class Album implements Comparable,Parcelable{
    private String albumName;
    private String albumImage;
    private int albumId;

    protected Album(Parcel in) {
        albumName = in.readString();
        albumImage = in.readString();
        albumId = in.readInt();
        albumArtist = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.albumName);
        dest.writeString(this.albumImage);
        dest.writeString(this.albumImage);
        dest.writeInt(this.albumId);
    }
}