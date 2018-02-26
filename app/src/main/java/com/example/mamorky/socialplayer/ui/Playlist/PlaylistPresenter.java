package com.example.mamorky.socialplayer.ui.Playlist;

import com.example.mamorky.socialplayer.data.db.pojo.Playlist;

import java.util.Comparator;

import com.example.mamorky.socialplayer.adapter.PlaylistAdapter;
import com.example.mamorky.socialplayer.adapter.SongAdapter;

/**
 * Created by mamorky on 11/01/18.
 */

public interface PlaylistPresenter extends PlaylistInteractor.onLoadSuccess {
    void loadPlaylist();
    void deletePlaylist(Playlist playlist);
    void orderArticulos(Comparator<Playlist> comparator, PlaylistAdapter adapter);
}
