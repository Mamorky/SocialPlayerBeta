package com.example.mamorky.socialplayer.ui.Artist;

import com.example.mamorky.socialplayer.adapter.ArtistAdapter;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;

/**
 * Created by mamorky on 12/11/17.
 */

public interface ArtistPresenter {
    public void putAdapter(ArtistAdapter artistAdapter);
    public void createAdapter(RecyclerItemClickListener itemClickListener, int color);
}
