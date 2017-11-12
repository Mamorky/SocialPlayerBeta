package com.example.mamorky.socialplayer.ui.Album;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mamorky.socialplayer.R;

import adapter.AlbumAdapter;

/**
 * Created by mamorky on 5/11/17.
 */

public class AlbumViewImp extends AppCompatActivity implements AlbumView {

    private RecyclerView recyclerAlbum;
    private AlbumAdapter albumAdapter;
    AlbumPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        recyclerAlbum = (RecyclerView)findViewById(R.id.recyclerAlbum);
        recyclerAlbum.setHasFixedSize(true);
        recyclerAlbum.setLayoutManager(new GridLayoutManager(this,2));
        presenter = new AlbumPresenterImp(this);
        presenter.createAdapter();
    }

    @Override
    public void showAdapter(AlbumAdapter adapter) {
        recyclerAlbum.setAdapter(adapter);
    }
}
