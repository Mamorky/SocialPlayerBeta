package com.example.mamorky.socialplayer.ui.Artist;

import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.data.db.repositories.ArtistRepository;

import java.util.ArrayList;

import com.example.mamorky.socialplayer.adapter.ArtistAdapter;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;

/**
 * Created by mamorky on 12/11/17.
 */

public class ArtistInteractorImp implements ArtistInteractor{
    private ArtistPresenter presenter;

    public ArtistInteractorImp(ArtistPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void createAdapter(RecyclerItemClickListener itemClickListener, int color) {
        ArrayList<Artist> artists = ArtistRepository.getInstance().getArtist();
        ArtistAdapter adapter = new ArtistAdapter(artists,itemClickListener,color);
        presenter.putAdapter(adapter);
    }
}
