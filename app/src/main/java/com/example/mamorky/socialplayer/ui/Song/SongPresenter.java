package com.example.mamorky.socialplayer.ui.Song;

import android.content.Context;

import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.Comparator;

import adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public interface SongPresenter {
    void loadSong();
    void deleteArticulo(Song song);
    void orderArticulos(Comparator<Song> comparator, SongAdapter adapter);
}
