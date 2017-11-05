package com.example.mamorky.socialplayer;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import adapter.AlbumAdapter;
import pojo.Album;

/**
 * Created by mamorky on 5/11/17.
 */

public class AlbumActivity extends AppCompatActivity {

    private RecyclerView recyclerAlbum;
    private AlbumAdapter albumAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        recyclerAlbum = (RecyclerView)findViewById(R.id.recyclerAlbum);

        recyclerAlbum.setHasFixedSize(true);
        recyclerAlbum.setLayoutManager(new GridLayoutManager(this,2));

        albumAdapter = new AlbumAdapter();

        recyclerAlbum.setAdapter(albumAdapter);
    }
}
