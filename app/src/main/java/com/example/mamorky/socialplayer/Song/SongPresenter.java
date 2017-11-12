package com.example.mamorky.socialplayer.Song;

import android.content.Context;

import adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public interface SongPresenter {
    public void CreateAdapter(Context context);
    public void showAdapter(SongAdapter songAdapter);
}
