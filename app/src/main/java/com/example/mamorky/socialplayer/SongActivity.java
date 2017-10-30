package com.example.mamorky.socialplayer;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import adapter.SongAdapter;
import pojo.Song;

public class SongActivity extends ListActivity {

    private ArrayAdapter<Song> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        adapter = new SongAdapter(this);
        getListView().setAdapter(adapter);
    }
}
