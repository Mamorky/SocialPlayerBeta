package com.example.mamorky.socialplayer.data.db.repositories;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;
import java.util.Collections;

import com.example.mamorky.socialplayer.data.db.pojo.Song;

/**
 * Created by mamorky on 30/10/17.
 */

public class SongRepository {
    private ArrayList<Song> songs;
    private static SongRepository songRepository;

    static{
        songRepository = new SongRepository();
    }

    private SongRepository(){
        this.songs = new ArrayList<Song>();
        inicialize();
    }

    private void inicialize(){
        for (int i = 0;i < 20;i++){
            addSong(new Song("Melody","Spinnin","Mike Williams",R.drawable.album1,i,1,1));
            addSong(new Song("Konishiwa","Spinnin","Mike Williams",R.drawable.album3,i,1,1));
            addSong(new Song("Koala","Spinnin","Oliver Heldens",R.drawable.album2,i,2,2));
        }
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public static SongRepository getInstance(){
        return songRepository;
    }

    public ArrayList<Song> getSongs(){
        Collections.sort(songs);
        return songs;
    }
}