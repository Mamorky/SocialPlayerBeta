package com.example.mamorky.socialplayer.Album;

import java.util.ArrayList;

import adapter.AlbumAdapter;

/**
 * Created by mamorky on 12/11/17.
 */

public interface AlbumPresenter{
    public void showAdapter(AlbumAdapter albumAdapter);
    public void createAdapter();
}
