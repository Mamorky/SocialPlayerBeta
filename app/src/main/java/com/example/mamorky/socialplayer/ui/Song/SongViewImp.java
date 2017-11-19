package com.example.mamorky.socialplayer.ui.Song;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSong);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.activity_name_canciones);

        presenter = new SongPresenterImp(this);
        songAdapter = new SongAdapter(this);
        listView.setAdapter(songAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu_song,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.btnBuscarMenu:
                Toast.makeText(SongViewImp.this,"Buscar Cancion",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings_order_name_song:
                songAdapter = new SongAdapter(this);
                listView.setAdapter(songAdapter);
                return true;
            case R.id.action_settings_order_id_song:
                songAdapter = new SongAdapter(this,"id");
                listView.setAdapter(songAdapter);
                return true;
            case R.id.action_settings_order_id_artist:
                songAdapter = new SongAdapter(this,"artist");
                listView.setAdapter(songAdapter);
                return true;
            case R.id.action_settings_order_id_album:
                songAdapter = new SongAdapter(this,"album");
                listView.setAdapter(songAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showAdapter(SongAdapter songAdapter) {
        listView.setAdapter(songAdapter);
    }
}
