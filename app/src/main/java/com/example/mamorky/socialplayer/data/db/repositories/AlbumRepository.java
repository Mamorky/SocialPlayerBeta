package com.example.mamorky.socialplayer.data.db.repositories;

import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mamorky on 5/11/17.
 */

public class AlbumRepository {
    private ArrayList<Album> albums;
    private static AlbumRepository albumRepository;

    static {
        albumRepository = new AlbumRepository();
    }

    public AlbumRepository(){
        this.albums = new ArrayList<>();
        inicialize();
    }

    public void inicialize(){
        albums.add(new Album(01,"The best Album", R.drawable.album1,"Oliver Heldeens"));
        albums.add(new Album(02,"One day for your turn", R.drawable.album2,"Mike Williams"));
        albums.add(new Album(03,"I Know that you Know", R.drawable.album3,"Tchami"));
    }

    public void addAlbum(Album album){
        albums.add(album);
    }

    public ArrayList<Album> getAlbums(){
        Collections.sort(albums);
        return albums;
    }

    public Album getAlbumById(int id){
        for (int i = 0; i < albums.size(); i++) {
            if(albums.get(i).getAlbumId() == id)
                return albums.get(i);
        }

        return null;
    }

    public static AlbumRepository getInstance(){
        return albumRepository;
    }
}
