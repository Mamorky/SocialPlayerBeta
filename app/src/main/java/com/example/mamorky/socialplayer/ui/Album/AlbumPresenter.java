package com.example.mamorky.socialplayer.ui.Album;

import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Created by mamorky on 12/11/17.
 */

public interface AlbumPresenter{
    public void albumLoaded(ArrayList<Album> albumAdapter);
    public void loadAlbums();
    public void onAlbumClick(Album album);
}
