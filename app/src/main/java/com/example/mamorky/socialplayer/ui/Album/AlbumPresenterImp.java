package com.example.mamorky.socialplayer.ui.Album;

import adapter.AlbumAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public class AlbumPresenterImp implements AlbumPresenter{

    private AlbumInteractor interactor;
    private AlbumView view;

    public AlbumPresenterImp(AlbumView view){
        this.view = view;
        interactor = new AlbumInteractorImp(this);
    }

    @Override
    public void showAdapter(AlbumAdapter albumAdapter) {
        if(view != null)
            view.showAdapter(albumAdapter);
    }

    @Override
    public void createAdapter() {
        if(interactor != null)
            interactor.createAdapter();
    }
}
