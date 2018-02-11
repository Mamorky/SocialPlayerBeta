package com.example.mamorky.socialplayer.ui.Playlist.AddEditPlaylist;

import android.graphics.Bitmap;

/**
 * Created by mamorky on 12/01/18.
 */

public interface AddEditInteractor {
    void addPlaylist(String nombre, Bitmap imagen, onValidatePlaylist onValidatePlaylist);
    void editPlaylist(int id, String Nombre,Bitmap imagen, onValidatePlaylist onValidatePlaylist);

    interface onValidatePlaylist{
        void onNameEmpty();
        void onImageError();
        void onSucess();
    }
}
