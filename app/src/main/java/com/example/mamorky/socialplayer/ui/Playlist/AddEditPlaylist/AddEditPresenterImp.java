package com.example.mamorky.socialplayer.ui.Playlist.AddEditPlaylist;

import android.graphics.Bitmap;

/**
 * Created by mamorky on 12/01/18.
 */

public class AddEditPresenterImp implements AddEditPresenter,AddEditInteractor.onValidatePlaylist{
    private AddEditView view;
    private AddEditInteractor interactor;

    public AddEditPresenterImp(AddEditView view) {
        this.view = view;
        this.interactor = new AddEditInteractorImp(this);
    }

    @Override
    public void addPlaylist(String nombre, Bitmap imagen) {
        interactor.addPlaylist(nombre,imagen,this);
    }

    @Override
    public void editPlaylist(int id, String nombre, Bitmap imagen) {
        interactor.editPlaylist(id,nombre,imagen,this);
    }

    @Override
    public void onNameEmpty() {
        view.onSetNameEmpty();
    }

    @Override
    public void onImageError() {
        view.onSetImageError();
    }

    @Override
    public void onSucess() {
        view.onSuccess();
    }

}
