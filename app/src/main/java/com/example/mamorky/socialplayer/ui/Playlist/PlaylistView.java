package com.example.mamorky.socialplayer.ui.Playlist;

import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by mamorky on 11/01/18.
 */

public interface PlaylistView {
    void onLoadSuccess(ArrayList<Playlist> playlists);
}
