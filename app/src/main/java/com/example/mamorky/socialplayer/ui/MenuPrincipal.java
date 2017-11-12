package com.example.mamorky.socialplayer.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.Album.AlbumViewImp;
import com.example.mamorky.socialplayer.ui.Artist.ArtistViewImp;
import com.example.mamorky.socialplayer.ui.Song.SongViewImp;

public class MenuPrincipal extends AppCompatActivity {

    ImageView imgArtist,imgAlbum,imgSong,imgAcercaDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        imgArtist = (ImageView)findViewById(R.id.imgArtistMenu);
        imgArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this,ArtistViewImp.class);
                startActivity(i);
            }
        });

        imgAlbum = (ImageView)findViewById(R.id.imgAlbumMenu);
        imgAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this,AlbumViewImp.class);
                startActivity(i);
            }
        });

        imgSong = (ImageView)findViewById(R.id.imgSongMenu);
        imgSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this,SongViewImp.class);
                startActivity(i);
            }
        });

        imgAcercaDe = (ImageView)findViewById(R.id.imgAcercaDe);
        imgAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this,AboutActivity.class);
                startActivity(i);
            }
        });
    }
}
