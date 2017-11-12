package com.example.mamorky.socialplayer.ui.Artist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mamorky.socialplayer.R;

import adapter.ArtistAdapter;

public class ArtistViewImp extends AppCompatActivity implements ArtistView{

    private RecyclerView recyclerView;
    private ArtistAdapter artistAdapter;
    private ArtistPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        recyclerView = (RecyclerView)findViewById(R.id.recylcerArtist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        presenter = new ArtistPresenterImp(this);
        presenter.createAdapter();
    }

    @Override
    public void putAdapter(ArtistAdapter artistAdapter) {
        recyclerView.setAdapter(artistAdapter);
    }
}
