package com.example.mamorky.socialplayer.ui.Artist;

import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.data.db.repositories.ArtistRepository;

import java.util.ArrayList;

import com.example.mamorky.socialplayer.adapter.ArtistAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public class ArtistInteractorImp implements ArtistInteractor{
    private ArtistPresenter presenter;

    public ArtistInteractorImp(ArtistPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void createAdapter() {
        ArrayList<Artist> artists = ArtistRepository.getInstance().getArtist();
        ArtistAdapter adapter = new ArtistAdapter(artists);
        presenter.putAdapter(adapter);
    }
}
