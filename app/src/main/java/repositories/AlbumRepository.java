package repositories;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;

import pojo.Album;

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
        Inicialize();
    }

    public void Inicialize(){
        for (int i = 0;i < 10;i++){
            albums.add(new Album("The best Album", R.drawable.album1,"Oliver Heldeens"));
            albums.add(new Album("One day for your turn", R.drawable.album2,"Mike Williams"));
            albums.add(new Album("I Know that you Know", R.drawable.album3,"Tchami"));
        }
    }

    public void addAlbum(Album album){
        albums.add(album);
    }

    public ArrayList<Album> getAlbums(){
        return albums;
    }

    public static AlbumRepository getInstance(){
        return albumRepository;
    }
}
