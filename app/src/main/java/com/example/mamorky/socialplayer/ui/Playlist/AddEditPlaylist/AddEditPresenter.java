package com.example.mamorky.socialplayer.ui.Playlist.AddEditPlaylist;

import android.graphics.Bitmap;

/**
 * Created by mamorky on 12/01/18.
 */

public interface AddEditPresenter {
    void addPlaylist(String nombre, Bitmap imagen);
    void editPlaylist(int id,String nombre,Bitmap imagen);
}
