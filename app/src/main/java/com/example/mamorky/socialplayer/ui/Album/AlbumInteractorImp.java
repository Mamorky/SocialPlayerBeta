package com.example.mamorky.socialplayer.ui.Album;

import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.data.db.repositories.AlbumRepository;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Created by mamorky on 12/11/17.
 */

public class AlbumInteractorImp implements AlbumInteractor{

    AlbumPresenter presenter;
    AlbumRepository repository;

    public AlbumInteractorImp(AlbumPresenter presenter){
        this.presenter = presenter;
        repository = new AlbumRepository();
    }

    @Override
    public void loadAlbums() {
        ArrayList<Album> albums = repository.getAlbums();
        presenter.albumLoaded(albums);
    }
}
