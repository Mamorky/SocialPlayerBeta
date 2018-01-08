package com.example.mamorky.socialplayer.ui.Song;

import android.content.Context;

import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;
import java.util.Comparator;

import adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public class SongPresenterImp implements SongPresenter,SongInteractor.onLoadSuccess{

    private SongView songView;
    private SongInteractor interactor;

    public SongPresenterImp(SongView songView){
        this.songView = songView;
        this.interactor = new SongInteractorImp(this);
    }

    @Override
    public void loadSong() {
        interactor.loadSongs(this);
    }

    @Override
    public void deleteArticulo(Song song) {
        interactor.deleteArticulo(song);
    }

    @Override
    public void orderArticulos(Comparator<Song> comparator, SongAdapter adapter) {
        adapter.sort(comparator);
    }

    @Override
    public void onLoadSuccess(ArrayList<Song> songs) {
        songView.onLoadSuccess(songs);
    }
}
