package com.example.mamorky.socialplayer.ui.Artist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.Song.SongViewImp;

import adapter.ArtistAdapter;

public class ArtistViewImp extends AppCompatActivity implements ArtistView{

    private RecyclerView recyclerView;
    private ArtistAdapter artistAdapter;
    private ArtistPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarArtist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.activity_name_artist);

        recyclerView = (RecyclerView)findViewById(R.id.recylcerArtist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        presenter = new ArtistPresenterImp(this);
        presenter.createAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu_general,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.btnBuscarMenu:
                Toast.makeText(ArtistViewImp.this,"Buscar Artista",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void putAdapter(ArtistAdapter artistAdapter) {
        recyclerView.setAdapter(artistAdapter);
    }
}
