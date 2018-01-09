package com.example.mamorky.socialplayer.ui.Song;

import android.content.Context;

import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;

/**
 * Created by mamorky on 12/11/17.
 */

public interface SongInteractor{
        void loadSongs(onLoadSuccess onLoadSuccess);
        void deleteSong(Song song);

    void deleteSongs(ArrayList<Song> tmp);

    interface onLoadSuccess{
            void onLoadSuccess(ArrayList<Song> songs);
        }
}
