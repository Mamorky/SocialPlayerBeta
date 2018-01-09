package com.example.mamorky.socialplayer.data.db.repositories;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        for (int i = 0;i < 6;i++){
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

    public void deleteSong(Song song){
        for (int i = 0; i < songs.size(); i++) {
            if(songs.get(i).get_name().equals(song.get_name())) {
                songs.remove(i);
                return;
            }
        }
    }

    public void deleteSongs(ArrayList<Song> songs){
        for (int i = 0; i < songs.size(); i++) {
            deleteSong(songs.get(i));
        }
    }

    public ArrayList<Song> getSongs(String ordenarPor){
        if(ordenarPor == "id"){
            Collections.sort(songs, new Song.SongCompareById());
            return songs;
        }
        if (ordenarPor == "artist"){
            Collections.sort(songs, new Song.SongCompareByIdArtitst());
            return songs;
        }
        if(ordenarPor == "album"){
            Collections.sort(songs, new Song.SongCompareByIdAlbum());
            return songs;
        }
        return getSongs();}
    }
