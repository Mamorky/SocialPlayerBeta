package com.example.mamorky.socialplayer.ui.Playlist.AddEditPlaylist;

import android.graphics.Bitmap;
import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.data.db.repositories.PlaylistRepository;

/**
 * Created by mamorky on 12/01/18.
 */

public class AddEditInteractorImp implements AddEditInteractor {
    AddEditPresenter presenter;

    public AddEditInteractorImp(AddEditPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPlaylist(String nombre, Bitmap imagen, onValidatePlaylist onValidatePlaylist) {
        if(nombre.isEmpty()){
            onValidatePlaylist.onNameEmpty();
        }
        else {
            try {
                Playlist playlist = new Playlist(0,nombre,imagen);
                PlaylistRepository.getInstance().addPlaylist(playlist);
            }
            catch (Exception e){
                e.printStackTrace();
                onValidatePlaylist.onImageError();
                return;
            }
            onValidatePlaylist.onSucess();
        }
    }

    @Override
    public void editPlaylist(int id, String nombre, Bitmap imagen, onValidatePlaylist onValidatePlaylist) {
        if(nombre.isEmpty()){
            onValidatePlaylist.onNameEmpty();
        }
        else {
            try {
                PlaylistRepository.getInstance().updatePlaylist(new Playlist(id,nombre,imagen));
            }
            catch (Exception e){
                onValidatePlaylist.onImageError();
                return;
            }
            onValidatePlaylist.onSucess();
        }
    }
}
