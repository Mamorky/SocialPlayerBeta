package com.example.mamorky.socialplayer.ui.Playlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.ui.Playlist.AddEditPlaylist.AddEditViewImp;
import com.example.mamorky.socialplayer.ui.PrincipalActivity;
import com.example.mamorky.socialplayer.util.AddEdit;

import java.util.ArrayList;
import java.util.Comparator;

import com.example.mamorky.socialplayer.adapter.PlaylistAdapter;
import com.example.mamorky.socialplayer.adapter.SongAdapter;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by mamorky on 11/01/18.
 */

public class PlaylistViewImp extends Fragment implements PlaylistView{

    private RecyclerView recyclerView;
    private PlaylistAdapter playlistAdapter;
    private PlaylistPresenter presenter;
    private Toolbar toolbar;

    private Comparator<Playlist> playlistComparator;

    private FloatingActionButton fabAddEdit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        View viewRoot = inflater.inflate(R.layout.fragment_playlist,container,false);

        recyclerView = (RecyclerView)viewRoot.findViewById(R.id.recylcerPlaylist);

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE)
            recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),3));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        presenter = new PlaylistPresenterImp(this);

        fabAddEdit = viewRoot.findViewById(R.id.fabAdd);

        return viewRoot;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerItemClickListener itemClickListener;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.loadPlaylist();
        recyclerView.setAdapter(playlistAdapter);

        fabAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), AddEditViewImp.class);
                    intent.putExtra(AddEdit.TAG_ADDEDIT,AddEdit.ADD);

                    startActivity(intent);
                }
        });

        registerForContextMenu(recyclerView);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadPlaylist();
        recyclerView.setAdapter(playlistAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnBuscarMenu:
                Toast.makeText(getActivity().getApplicationContext(),"Buscar Cancion", LENGTH_LONG).show();
                break;
            case R.id.action_settings_order_name_song:
                playlistComparator = null;
                presenter.orderArticulos(playlistComparator,playlistAdapter);
                break;
            case R.id.action_settings_order_id_song:
                playlistComparator = new Playlist.playlistCompareById();
                presenter.orderArticulos(playlistComparator,playlistAdapter);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        Playlist playlistSelect;

        try {
            position = ((PlaylistAdapter)recyclerView.getAdapter()).getPosition();
            playlistSelect = ((PlaylistAdapter) recyclerView.getAdapter()).playlistSelected(position);
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.action_delete:
                presenter.deletePlaylist(playlistSelect);
                break;
            case R.id.action_modify:
                Intent intent = new Intent(getActivity().getApplicationContext(),AddEditViewImp.class);
                intent.putExtra(Playlist.TAB_OBJ,playlistSelect);
                intent.putExtra(AddEdit.TAG_ADDEDIT,AddEdit.EDIT);

                startActivity(intent);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    public void onLoadSuccess(ArrayList<Playlist> playlists) {
        playlistAdapter = new PlaylistAdapter(playlists);
    }
}
