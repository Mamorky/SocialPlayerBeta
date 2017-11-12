package com.example.mamorky.socialplayer.ui.Album;

import adapter.AlbumAdapter;
import com.example.mamorky.socialplayer.data.db.repositories.AlbumRepository;

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
    public void createAdapter() {
        AlbumAdapter adapter = new AlbumAdapter(repository.getAlbums());
        presenter.showAdapter(adapter);
    }
}
