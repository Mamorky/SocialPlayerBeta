package com.example.mamorky.socialplayer.data.db.repositories;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;
import java.util.Collections;

import com.example.mamorky.socialplayer.data.db.pojo.Artist;

/**
 * Created by mamorky on 6/11/17.
 */

public class ArtistRepository {
    private ArrayList<Artist> artists;
    private static ArtistRepository artistDepository;

    static {
        artistDepository = new ArtistRepository();
    }

    private ArtistRepository(){
        artists = new ArrayList<Artist>();
        inicialize();
    }

    private void inicialize() {
        for (int i = 0;i < 20;i++){
            addArtist(new Artist("Mike Williams", R.drawable.album1));
            addArtist(new Artist("Oliver Heldeens", R.drawable.album2));
            addArtist(new Artist("Pep & Rash", R.drawable.album3));
        }
    }

    public void addArtist(Artist artist){
        artists.add(artist);
    }

    public ArrayList<Artist> getArtist(){
        Collections.sort(artists);
        return artists;
    }

    public static ArtistRepository getInstance(){
        return artistDepository;
    }
}
