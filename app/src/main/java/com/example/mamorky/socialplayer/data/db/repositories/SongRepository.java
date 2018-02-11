package com.example.mamorky.socialplayer.data.db.repositories;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.data.db.repositories.dao.SongDao;

/**
 * Created by mamorky on 30/10/17.
 */

public class SongRepository {
    private ArrayList<Song> songs;
    private static SongRepository songRepository;

    private SongDao mDao;

    static{
        songRepository = new SongRepository();
    }

    private SongRepository(){
        this.songs = new ArrayList<Song>();
        this.mDao = new SongDao();
    }

    /**
    private void inicialize(){
        addSong(new Song("Melody",01,01,1));
        addSong(new Song("Koala",02,02,2));
        addSong(new Song("Konishigua",02,03,2));
    }*/

    public void addSong(Song song){
        songs.add(song);
    }

    public static SongRepository getInstance(){
        return songRepository;
    }

    public ArrayList<Song> getSongs(){
        Collections.sort(songs);
        return mDao.loadAll();
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
            return mDao.loadAll();
        }
        if (ordenarPor == "artist"){
            Collections.sort(songs, new Song.SongCompareByIdArtitst());
            return mDao.loadAll();
        }
        if(ordenarPor == "album"){
            Collections.sort(songs, new Song.SongCompareByIdAlbum());
            return mDao.loadAll();
        }

        return mDao.loadAll();
    }
}
