package com.example.mamorky.socialplayer.ui.Playlist.AddEditPlaylist;

import android.graphics.Bitmap;
import android.nfc.FormatException;

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
                PlaylistRepository repository = PlaylistRepository.getInstance();
                repository.addPlaylist(new Playlist(repository.getLastId()+1,nombre,imagen));
            }
            catch (Exception e){
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
                Playlist playlistEdt = PlaylistRepository.getInstance().getPlaylist(id);
                playlistEdt.setNamePlaylist(nombre);
                playlistEdt.setBitMapPlaylist(imagen);
            }
            catch (Exception e){
                onValidatePlaylist.onImageError();
                return;
            }
            onValidatePlaylist.onSucess();
        }
    }
}
