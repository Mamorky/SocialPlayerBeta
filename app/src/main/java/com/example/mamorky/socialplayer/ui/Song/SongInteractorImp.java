package com.example.mamorky.socialplayer.ui.Song;

import android.content.Context;

import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.data.db.repositories.SongRepository;

import java.util.ArrayList;

import adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public class SongInteractorImp implements SongInteractor{

    private SongPresenter presenter;
    private SongAdapter adapter;

    public SongInteractorImp(SongPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void loadSongs(onLoadSuccess onLoadSuccess) {
        ArrayList<Song> songs = SongRepository.getInstance().getSongs();
        onLoadSuccess.onLoadSuccess(songs);
    }

    @Override
    public void deleteArticulo(Song song) {
    }
}
