package com.example.mamorky.socialplayer.Song;

import android.content.Context;

import adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public class SongPresenterImp implements SongPresenter{

    private SongView songView;
    private SongInteractor interactor;

    public SongPresenterImp(SongView songView){
        this.songView = songView;
        this.interactor = new SongInteractorImp(this);
    }

    @Override
    public void CreateAdapter(Context context) {
        if(interactor != null)
            interactor.CreateAdapter(context);
    }

    @Override
    public void showAdapter(SongAdapter songAdapter) {
        if(songView != null)
            songView.showAdapter(songAdapter);
    }
}
