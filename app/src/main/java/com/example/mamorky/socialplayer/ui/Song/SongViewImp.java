package com.example.mamorky.socialplayer.ui.Song;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;
import java.util.Comparator;

import adapter.SongAdapter;

public class SongViewImp extends ListFragment implements SongView{

    private ListView listView;
    private SongAdapter songAdapter;
    private SongPresenter presenter;

    private Toolbar toolbar;

    private Comparator<Song> songComparator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        View viewRoot = inflater.inflate(R.layout.activity_song,container,false);

        listView = (ListView) viewRoot.findViewById(R.id.listView);
        toolbar = (Toolbar) viewRoot.findViewById(R.id.toolbarSong);

        toolbar.setTitle(R.string.activity_name_canciones);
        toolbar.inflateMenu(R.menu.activity_menu_song);

        presenter = new SongPresenterImp(this);

        songAdapter = new SongAdapter(container.getContext(),"nombre");
        listView.setAdapter(songAdapter);

        registerForContextMenu(viewRoot);

        return viewRoot;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadSong();
        presenter.orderArticulos(songComparator,songAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView.setAdapter(songAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_menu_song, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnBuscarMenu:
                Toast.makeText(getActivity().getApplicationContext(),"Buscar Cancion",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings_order_name_song:
                songComparator = null;
                presenter.orderArticulos(songComparator,songAdapter);
                break;
            case R.id.action_settings_order_id_song:
                songComparator = new Song.SongCompareById();
                presenter.orderArticulos(songComparator,songAdapter);
                break;
            case R.id.action_settings_order_id_artist:
                songComparator = new Song.SongCompareByIdArtitst();
                presenter.orderArticulos(songComparator,songAdapter);
                break;
            case R.id.action_settings_order_id_album:
                songComparator = new Song.SongCompareByIdAlbum();
                presenter.orderArticulos(songComparator,songAdapter);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onLoadSuccess(ArrayList<Song> songs) {
        songAdapter.clear();
        songAdapter.addAll(songs);
    }
}
