package com.example.mamorky.socialplayer.ui.Song;

import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.data.db.repositories.SongRepository;

import java.util.ArrayList;

import com.example.mamorky.socialplayer.adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public class SongInteractorImp implements SongInteractor{

    private SongPresenter presenter;
    private SongAdapter adapter;

    public SongInteractorImp(SongPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void loadSongs(final onLoadSuccess onLoadSuccess) {
                ArrayList<Song> songs = SongRepository.getInstance().getSongs();
                onLoadSuccess.onLoadSuccess(songs);
    }

    @Override
    public void deleteSong(Song song) {
        SongRepository.getInstance().deleteSong(song);
    }

    @Override
    public void deleteSongs(ArrayList<Song> tmp) {
        SongRepository.getInstance().deleteSongs(tmp);
    }

    @Override
    public void loadSongs(final onLoadSuccess onLoadSuccess, Album albumtag) {
        ArrayList<Song> songs = SongRepository.getInstance().getSongs(albumtag);
        onLoadSuccess.onLoadSuccess(songs);
    }

    @Override
    public void loadSongs(final onLoadSuccess onLoadSuccess, Artist artisttag) {
        ArrayList<Song> songs = SongRepository.getInstance().getSongs(artisttag);
        onLoadSuccess.onLoadSuccess(songs);
    }
}
