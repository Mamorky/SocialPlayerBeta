package com.example.mamorky.socialplayer.ui.Song;

import android.view.ActionMode;

import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public class SongPresenterImp implements SongPresenter,SongInteractor.onLoadSuccess{

    private SongView songView;
    private SongInteractor interactor;

    public SongPresenterImp(SongView songView){
        this.songView = songView;
        this.interactor = new SongInteractorImp(this);
    }

    @Override
    public void loadSong() {
        interactor.loadSongs(this);
    }

    @Override
    public void deleteSong(Song song) {
        interactor.deleteSong(song);
    }

    @Override
    public void orderArticulos(Comparator<Song> comparator, SongAdapter adapter) {
        adapter.sort(comparator);
    }

    HashMap<Integer,Boolean> selection = new HashMap<>();

    @Override
    public void setNewSelection(int position) {
        selection.put(position, true);
    }

    @Override
    public void removeSelection(int position) {
        selection.remove(position);
    }

    @Override
    public void deleteSelection() {
        songView.deleteSelectedSongs(selection.keySet());
    }

    @Override
    public void clearSelection(ActionMode mode, int count) {
        selection.clear();
    }

    @Override
    public boolean isPositionChecked(int position) {
        return selection.get(position) == null ? false:true;
    }

    @Override
    public void deleteSelectionDependency(ArrayList<Song> tmp) {
        interactor.deleteSongs(tmp);
        this.loadSong();
    }

    @Override
    public void onLoadSuccess(ArrayList<Song> songs) {
        songView.onLoadSuccess(songs);
    }
}
