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
        addArtist(new Artist(01,"Mike Williams", R.drawable.album1));
        addArtist(new Artist(02,"Oliver Heldeens", R.drawable.album2));
        addArtist(new Artist(03,"Pep & Rash", R.drawable.album3));
    }

    public void addArtist(Artist artist){
        artists.add(artist);
    }

    public ArrayList<Artist> getArtist(){
        Collections.sort(artists);
        return artists;
    }

    public Artist getArtist(int id){
        for (int i = 0; i < artists.size(); i++) {
            if(artists.get(i).getIdArtist() == id)
                return artists.get(i);
        }
        return null;
    }

    public static ArtistRepository getInstance(){
        return artistDepository;
    }
}
