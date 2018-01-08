package com.example.mamorky.socialplayer.ui;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.Album.AlbumViewImp;
import com.example.mamorky.socialplayer.ui.Artist.ArtistViewImp;
import com.example.mamorky.socialplayer.ui.Song.SongViewImp;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class PrincipalActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        setupBottomNavigation();

        if (savedInstanceState == null) {
            loadSong();
        }
    }

    private void setupBottomNavigation() {

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_song_selector:
                        loadSong();
                        return true;
                    case R.id.action_album_selector:
                        loadAlbum();
                        return true;
                    case R.id.action_artist_selector:
                        loadArtist();
                        return true;
                    case R.id.action_list_selector:
                        loadSong();
                        return true;
                }
                return false;
            }
        });
    }

    private void loadSong(){
        SongViewImp fragment = new SongViewImp();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    private void loadAlbum() {
        AlbumViewImp fragment = new AlbumViewImp();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    private void loadArtist() {
        ArtistViewImp fragment = new ArtistViewImp();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }
}
