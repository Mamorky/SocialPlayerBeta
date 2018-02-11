package com.example.mamorky.socialplayer.ui.Playlist;

import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;
import java.util.Comparator;

import adapter.PlaylistAdapter;
import adapter.SongAdapter;

/**
 * Created by mamorky on 11/01/18.
 */

public class PlaylistPresenterImp implements PlaylistPresenter{
    PlaylistView view;
    PlaylistInteractor interactor;

    public PlaylistPresenterImp(PlaylistView view){
        this.view = view;
        this.interactor = new PlaylistInteractorImp(this);
    }

    @Override
    public void loadPlaylist() {
        interactor.loadPlaylist(this);
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        interactor.deletePlaylist(playlist);
        interactor.loadPlaylist(this);
    }

    @Override
    public void orderArticulos(Comparator<Playlist> comparator, PlaylistAdapter adapter) {

    }

    @Override
    public void onLoadSuccess(ArrayList<Playlist> playlists) {
        view.onLoadSuccess(playlists);
    }
}
