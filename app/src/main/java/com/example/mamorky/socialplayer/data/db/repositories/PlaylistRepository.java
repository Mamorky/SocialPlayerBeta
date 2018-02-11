package com.example.mamorky.socialplayer.data.db.repositories;

import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mamorky on 11/01/18.
 */

public class PlaylistRepository {
    private ArrayList<Playlist> playlists;
    private static PlaylistRepository playlistRepository;

    static{
        playlistRepository = new PlaylistRepository();
    }

    private PlaylistRepository(){
        this.playlists = new ArrayList<Playlist>();
        inicialize();
    }

    private void inicialize(){
        addPlaylist(new Playlist(01,"playlist 1"));
        addPlaylist(new Playlist(02,"veranito"));
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    public static PlaylistRepository getInstance(){
        return playlistRepository;
    }

    public ArrayList<Playlist> getPlaylists(){
        return playlists;
    }

    public Playlist getPlaylist(int id){
        for (int i = 0; i < playlists.size(); i++) {
            if(playlists.get(i).getIdPlaylist() == id)
                return playlists.get(i);
        }

        return null;
    }

    public void deletePlaylist(Playlist playlist){
        for (int i = 0; i < playlists.size(); i++) {
            if(playlists.get(i).getIdPlaylist() == playlist.getIdPlaylist()) {
                playlists.remove(playlists.get(i));
                return;
            }
        }
    }

    public int getLastId(){
        int maxID = 0;

        for (int i = 0; i < playlists.size(); i++) {
            if(playlists.get(i).getIdPlaylist() > maxID)
                maxID = playlists.get(i).getIdPlaylist();
        }

        return maxID;
    }

    public ArrayList<Playlist> getPlaylists(String ordenarPor){
        if(ordenarPor == "id"){
            Collections.sort(playlists, new Playlist.playlistCompareById());
            return playlists;
        }
        return getPlaylists();}
}
