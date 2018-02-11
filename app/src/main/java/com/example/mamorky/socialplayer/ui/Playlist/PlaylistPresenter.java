package com.example.mamorky.socialplayer.ui.Playlist;

import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.Comparator;

import adapter.PlaylistAdapter;
import adapter.SongAdapter;

/**
 * Created by mamorky on 11/01/18.
 */

public interface PlaylistPresenter extends PlaylistInteractor.onLoadSuccess {
    void loadPlaylist();
    void deletePlaylist(Playlist playlist);
    void orderArticulos(Comparator<Playlist> comparator, PlaylistAdapter adapter);
}
