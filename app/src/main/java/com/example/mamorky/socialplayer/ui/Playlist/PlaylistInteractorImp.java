package com.example.mamorky.socialplayer.ui.Playlist;

import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.data.db.repositories.PlaylistRepository;

/**
 * Created by mamorky on 11/01/18.
 */

public class PlaylistInteractorImp implements PlaylistInteractor{

    PlaylistPresenter presenter;

    public PlaylistInteractorImp(PlaylistPresenter playlistPresenter) {
        this.presenter = playlistPresenter;
    }

    @Override
    public void loadPlaylist(onLoadSuccess onLoadSuccess) {
        onLoadSuccess.onLoadSuccess(PlaylistRepository.getInstance().getPlaylists());
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        PlaylistRepository.getInstance().deletePlaylist(playlist);
    }
}
