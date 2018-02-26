package com.example.mamorky.socialplayer.data.db.repositories;

import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.data.db.repositories.dao.PlaylistDao;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mamorky on 11/01/18.
 */

public class PlaylistRepository {
    private static PlaylistRepository playlistRepository;
    public PlaylistDao mDao;

    static{
        playlistRepository = new PlaylistRepository();
    }

    private PlaylistRepository(){
        this.mDao = new PlaylistDao();
    }

    public void addPlaylist(Playlist playlist){mDao.addPlaylist(playlist);}

    public static PlaylistRepository getInstance(){
        return playlistRepository;
    }

    public ArrayList<Playlist> getPlaylists(){
        return mDao.loadAll();
    }

    public void updatePlaylist(Playlist playlist){
        mDao.updatePlaylist(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        mDao.deletePlaylist(playlist);
    }
}
