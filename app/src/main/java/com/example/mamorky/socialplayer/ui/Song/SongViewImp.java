package com.example.mamorky.socialplayer.ui.Song;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import com.example.mamorky.socialplayer.adapter.SongAdapter;
import com.example.mamorky.socialplayer.ui.Player;
import com.example.mamorky.socialplayer.util.PlayerUtils;
import com.example.mamorky.socialplayer.util.StyleUtils;

public class SongViewImp extends ListFragment implements SongView{

    public static final String TAG = "SongFragmentTag";
    private ListView listView;
    private SongAdapter songAdapter;
    private SongPresenter presenter;
    private FloatingActionButton fab_alea;

    private StyleUtils.toolBarPropieties mToolbarPropierties;

    private Comparator<Song> songComparator;

    private ArrayList<Song> songTmp;

    private PlayerUtils playerUtils;

    public static SongViewImp newInstance(Bundle arguments){
        SongViewImp songViewImp = new SongViewImp();
        if(arguments != null){
            songViewImp.setArguments(arguments);
        }
        return songViewImp;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mToolbarPropierties = (StyleUtils.toolBarPropieties) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View viewRoot = inflater.inflate(R.layout.fragment_song,container,false);

        presenter = new SongPresenterImp(this);

        listView = (ListView) viewRoot.findViewById(R.id.listView);
        fab_alea = viewRoot.findViewById(R.id.fab_alea);

        songAdapter = new SongAdapter(this.getActivity(),"nombre");
        listView.setAdapter(songAdapter);

        songTmp = new ArrayList<>();
        playerUtils = PlayerUtils.getInstance();

        return viewRoot;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState != null){
            Parcelable listViewState = savedInstanceState.getParcelable("listview.state");
            try {
                listView.onRestoreInstanceState(listViewState);
            }
            catch (Exception e){}
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView.setAdapter(songAdapter);

        if(this.getArguments() != null){
            Object objectoActual;
            if((objectoActual = this.getArguments().getParcelable("ALBUMTAG"))!= null){
                mToolbarPropierties.changeToolbarText(((Album)objectoActual).getAlbumName());
                presenter.loadSong((Album)objectoActual);
            }
            else
            if((objectoActual = this.getArguments().getParcelable("ARTISTTAG"))!= null){
                mToolbarPropierties.changeToolbarText(((Artist)objectoActual).getArtistName());
                presenter.loadSong((Artist)objectoActual);
            }
        }
        else
            presenter.loadSong();

        presenter.orderArticulos(songComparator,songAdapter);

        fab_alea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerUtils.setReproActual(songTmp);

                playerUtils.getMediaPlayer().reset();
                playerUtils.setShuffleActive(true, PlayerUtils.tipeAlea.fab);
                playerUtils.play_song();
                showActualSongBar();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playerUtils.setPosSong(position);

                ArrayList<Song> songsTmp = new ArrayList<>();

                for (int i = 0; i < songAdapter.getCount(); i++) {
                    songsTmp.add((Song) parent.getItemAtPosition(i));
                }

                playerUtils.setReproActual(new ArrayList<Song>(songsTmp));

                loadPlayer();

                playerUtils.getMediaPlayer().reset();

                if(playerUtils.isShuffleActive())
                    playerUtils.setShuffleActive(true, PlayerUtils.tipeAlea.song);

                playerUtils.setNewSongPush(true);
                playerUtils.setActualTime(0);
                playerUtils.play_song();
            }
        });
    }

    public void showActualSongBar() {
        if(getActivity().findViewById(R.id.cly_actual_song).getVisibility() == View.GONE)
            getActivity().findViewById(R.id.cly_actual_song).setVisibility(View.VISIBLE);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("listview.state", listView.onSaveInstanceState());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.activity_menu_song, menu);
    }

    public void loadPlayer(){
        Player fragmentPlayer = new Player();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
        ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
        ft.replace(R.id.main_frame_layout,fragmentPlayer);
        ft.commit();
    }

    @Override
    public void onLoadSuccess(ArrayList<Song> songs) {
        //playerUtils.setReproActual(songs);
        songTmp.addAll(songs);
        playerUtils.setCopyReproActual(songs);
        songAdapter.clear();
        songAdapter.addAll(songs);
    }

    @Override
    public void deleteSelectedSongs(Set<Integer> positions) {
        Iterator<Integer> iterator = positions.iterator();
        ArrayList <Song> tmp = new ArrayList<>();
        while (iterator.hasNext()){
            tmp.add(songAdapter.getItem(iterator.next()));
        }

        presenter.deleteSelectionDependency(tmp);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
