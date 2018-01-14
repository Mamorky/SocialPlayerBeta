package com.example.mamorky.socialplayer.ui;


import android.app.Fragment;
import android.app.FragmentContainer;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.Album.AlbumViewImp;
import com.example.mamorky.socialplayer.ui.Artist.ArtistViewImp;
import com.example.mamorky.socialplayer.ui.Login.LoginViewImp;
import com.example.mamorky.socialplayer.ui.Playlist.PlaylistViewImp;
import com.example.mamorky.socialplayer.ui.Song.SongViewImp;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.util.MyPreferenceOption;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class PrincipalActivity extends BaseActivity {

    private BottomNavigationView mBottomNavigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        setupBottomNavigation();

        if (savedInstanceState == null) {
            loadSong();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupNavigationView();
    }

    private void setupNavigationView(){
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_about:
                        Intent intent = new Intent(getApplicationContext(),AboutActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_configuration:
                        Intent intentConfiguration = new Intent(getApplicationContext(),GeneralPreferences.class);
                        startActivity(intentConfiguration);
                        break;
                    case R.id.action_close_session:
                        Intent intentClose = new Intent(getApplicationContext(), LoginViewImp.class);
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean(MyPreferenceOption.REMEMBERME,false).commit();
                        startActivity(intentClose);
                        finish();
                        break;
                }
                item.setChecked(true);
                getSupportActionBar().setTitle(item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLastFragment();
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
                        loadPlaylist();
                        return true;
                }
                return false;
            }
        });
    }

    public void loadSong(){
        SongViewImp fragment = new SongViewImp();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    public void loadAlbum() {
        AlbumViewImp fragment = new AlbumViewImp();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    public void loadArtist() {
        ArtistViewImp fragment = new ArtistViewImp();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    public void loadPlaylist() {
        PlaylistViewImp fragment = new PlaylistViewImp();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    public void loadLastFragment(){
        Fragment fragment = getFragmentManager().findFragmentById(R.id.main_frame_layout);

        if(fragment instanceof PlaylistViewImp)
            loadPlaylist();
        else if(fragment instanceof ArtistViewImp)
            loadArtist();
        else if(fragment instanceof SongViewImp)
            loadSong();
        else if(fragment instanceof AlbumViewImp)
            loadAlbum();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
