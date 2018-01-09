package com.example.mamorky.socialplayer.ui.Song;

import android.content.Context;
import android.view.ActionMode;

import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;
import java.util.Comparator;

import adapter.SongAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public interface SongPresenter {
    void loadSong();
    void deleteSong(Song song);
    void orderArticulos(Comparator<Song> comparator, SongAdapter adapter);

    void setNewSelection(int position);

    void removeSelection(int position);

    void deleteSelection();

    void clearSelection(ActionMode mode, int count);

    boolean isPositionChecked(int position);

    void deleteSelectionDependency(ArrayList<Song> tmp);
}
