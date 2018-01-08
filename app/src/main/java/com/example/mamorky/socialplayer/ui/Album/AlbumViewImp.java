package com.example.mamorky.socialplayer.ui.Album;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.Artist.ArtistViewImp;

import adapter.AlbumAdapter;

/**
 * Created by mamorky on 5/11/17.
 */

public class AlbumViewImp extends Fragment implements AlbumView {

    private RecyclerView recyclerAlbum;
    private AlbumAdapter albumAdapter;
    AlbumPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.activity_album,container,false);

        Toolbar toolbar = (Toolbar) viewRoot.findViewById(R.id.toolbarAlbum);
        toolbar.inflateMenu(R.menu.activity_menu_general);
        toolbar.setTitle(R.string.activity_name_albums);

        recyclerAlbum = (RecyclerView)viewRoot.findViewById(R.id.recyclerAlbum);
        recyclerAlbum.setHasFixedSize(true);

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE)
            recyclerAlbum.setLayoutManager(new GridLayoutManager(container.getContext(),3));
        else
            recyclerAlbum.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = new AlbumPresenterImp(this);
        presenter.createAdapter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = inflater;
        menuInflater.inflate(R.menu.activity_menu_general,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.btnBuscarMenu:
                Toast.makeText(getActivity().getApplicationContext(),"Buscar Album",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showAdapter(AlbumAdapter adapter) {
        recyclerAlbum.setAdapter(adapter);
    }
}
