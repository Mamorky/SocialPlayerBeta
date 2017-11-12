package com.example.mamorky.socialplayer.Song;

import android.content.Context;

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
    public void CreateAdapter(Context context) {
        adapter = new SongAdapter(context);
        presenter.showAdapter(adapter);
    }
}
