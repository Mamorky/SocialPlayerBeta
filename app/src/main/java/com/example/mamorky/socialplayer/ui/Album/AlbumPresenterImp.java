package com.example.mamorky.socialplayer.ui.Album;

import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;

import java.util.ArrayList;

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
    public void albumLoaded(ArrayList<Album> albums) {
        if(view != null)
            view.showAdapter(albums);
    }

    @Override
    public void loadAlbums() {
        if(interactor != null)
            interactor.loadAlbums();
    }

    @Override
    public void onAlbumClick(Album album) {

    }
}
