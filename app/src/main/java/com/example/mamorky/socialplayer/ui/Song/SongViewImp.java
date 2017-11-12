package com.example.mamorky.socialplayer.ui.Song;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.mamorky.socialplayer.R;

import adapter.SongAdapter;

public class SongViewImp extends AppCompatActivity implements SongView{

    private ListView listView;
    private SongAdapter songAdapter;
    private SongPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        listView = (ListView) findViewById(R.id.listView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new SongPresenterImp(this);
        presenter.CreateAdapter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu_song,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showAdapter(SongAdapter songAdapter) {
        listView.setAdapter(songAdapter);
    }
}
