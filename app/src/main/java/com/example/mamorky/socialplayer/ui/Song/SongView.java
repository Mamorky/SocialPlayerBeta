package com.example.mamorky.socialplayer.ui.Song;

import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;

import adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public interface SongView {
    void onLoadSuccess(ArrayList<Song> songs);
}
