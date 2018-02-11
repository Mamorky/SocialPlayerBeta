package com.example.mamorky.socialplayer.ui.Playlist;

import com.example.mamorky.socialplayer.data.db.pojo.Playlist;

import java.util.ArrayList;

/**
 * Created by mamorky on 11/01/18.
 */

public interface PlaylistInteractor {
    void loadPlaylist(onLoadSuccess onLoadSuccess);
    void deletePlaylist(Playlist playlist);

    interface onLoadSuccess {
        void onLoadSuccess(ArrayList<Playlist> playlists);
    }
}
