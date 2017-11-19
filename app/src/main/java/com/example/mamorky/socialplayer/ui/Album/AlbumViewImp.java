package com.example.mamorky.socialplayer.ui.Album;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.Artist.ArtistViewImp;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAlbum);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.activity_name_albums);

        recyclerAlbum = (RecyclerView)findViewById(R.id.recyclerAlbum);
        recyclerAlbum.setHasFixedSize(true);
        recyclerAlbum.setLayoutManager(new GridLayoutManager(this,2));

        presenter = new AlbumPresenterImp(this);
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
                Toast.makeText(AlbumViewImp.this,"Buscar Album",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showAdapter(AlbumAdapter adapter) {
        recyclerAlbum.setAdapter(adapter);
    }
}
