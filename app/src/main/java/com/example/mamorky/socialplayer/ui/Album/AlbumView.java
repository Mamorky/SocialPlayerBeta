package com.example.mamorky.socialplayer.ui.Album;

import com.example.mamorky.socialplayer.data.db.pojo.Album;

import java.util.ArrayList;

/**
 * Created by mamorky on 12/11/17.
 */

public interface AlbumView {
    public void showAdapter(ArrayList<Album> adapter);
    public void onAlbumClicked(Album album);
}
