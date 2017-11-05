package com.example.mamorky.socialplayer;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import adapter.SongAdapter;
import pojo.Song;

public class SongActivity extends AppCompatActivity {

    private ListView listView;
    private SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        listView = (ListView) findViewById(R.id.listView);

        songAdapter = new SongAdapter(this);

        listView.setAdapter(songAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu_song,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
