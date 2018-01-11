package com.example.mamorky.socialplayer.ui.Artist;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;

import adapter.ArtistAdapter;

public class ArtistViewImp extends Fragment implements ArtistView{

    private RecyclerView recyclerView;
    private ArtistAdapter artistAdapter;
    private ArtistPresenter presenter;
    private FrameLayout frameLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_artist,container,false);

        recyclerView = (RecyclerView)viewRoot.findViewById(R.id.recylcerArtist);
        recyclerView.setHasFixedSize(true);

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE)
            recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),3));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.getMenu().clear();
        toolbar.setTitle(R.string.activity_name_artist);
        toolbar.inflateMenu(R.menu.activity_menu_general);

        setHasOptionsMenu(true);

        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = new ArtistPresenterImp(this);
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
                Toast.makeText(frameLayout.getContext(),"Buscar Artista",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void putAdapter(ArtistAdapter artistAdapter) {
        recyclerView.setAdapter(artistAdapter);
    }
}
