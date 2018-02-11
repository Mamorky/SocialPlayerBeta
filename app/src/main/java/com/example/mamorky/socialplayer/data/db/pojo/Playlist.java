package com.example.mamorky.socialplayer.data.db.pojo;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by mamorky on 11/01/18.
 */

public class Playlist implements Comparable<Playlist>,Parcelable{
    private int idPlaylist;
    private String namePlaylist;
    private ArrayList<Integer> songsId;
    private Bitmap playlistImage;

    public static final String TAB_OBJ = "playlistobj";

    public Playlist(int idPlaylist, String playlistName) {
        this.idPlaylist = idPlaylist;
        this.namePlaylist = playlistName;
    }

    public Playlist(int idPlaylist, String playlistName, Bitmap playlistImage) {
        this.idPlaylist = idPlaylist;
        this.namePlaylist = playlistName;
        this.playlistImage = playlistImage;
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public Bitmap getBitMapPlaylist() {
        return playlistImage;
    }

    public void setBitMapPlaylist(Bitmap playlistImage){
        this.playlistImage = playlistImage;
    }

    public ArrayList<Integer> getSongsId() {
        return songsId;
    }

    public void setSongsId(ArrayList<Integer> songsId) {
        this.songsId = songsId;
    }

    @Override
    public int compareTo(@NonNull Playlist o) {
        return namePlaylist.compareTo(((Playlist)o).getNamePlaylist());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPlaylist);
        dest.writeString(namePlaylist);
        //dest.writeParcelable(playlistImage, flags);
    }

    protected Playlist(Parcel in) {
        idPlaylist = in.readInt();
        namePlaylist = in.readString();
    }


    public static class playlistCompareById implements Comparator<Playlist> {

        @Override
        public int compare(Playlist s1, Playlist s2) {
            return Integer.compare(s1.getIdPlaylist(),s2.getIdPlaylist());
        }
    }
}
