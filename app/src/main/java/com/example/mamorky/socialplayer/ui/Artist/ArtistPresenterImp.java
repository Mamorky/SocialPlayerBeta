package com.example.mamorky.socialplayer.ui.Artist;

import adapter.ArtistAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public class ArtistPresenterImp implements ArtistPresenter{

    private ArtistView view;
    private ArtistInteractor interactor;

    public ArtistPresenterImp(ArtistView view){
        this.view = view;
        this.interactor = new ArtistInteractorImp(this);
    }

    @Override
    public void putAdapter(ArtistAdapter artistAdapter) {
        if(view != null)
            view.putAdapter(artistAdapter);
    }

    @Override
    public void createAdapter() {
        if(interactor != null)
            interactor.createAdapter();
    }
}
