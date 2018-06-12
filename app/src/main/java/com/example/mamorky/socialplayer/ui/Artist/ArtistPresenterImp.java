package com.example.mamorky.socialplayer.ui.Artist;

import com.example.mamorky.socialplayer.adapter.ArtistAdapter;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;

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
    public void createAdapter(RecyclerItemClickListener itemClickListener, int color) {
        if(interactor != null)
            interactor.createAdapter(itemClickListener,color);
    }
}
