package com.example.mamorky.socialplayer.ui.Album;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;

import com.example.mamorky.socialplayer.adapter.AlbumAdapter;
import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.ui.PrincipalActivity;
import com.example.mamorky.socialplayer.ui.Song.SongViewImp;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;
import com.example.mamorky.socialplayer.util.StyleUtils;
import com.futuremind.recyclerviewfastscroll.FastScroller;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mamorky.socialplayer.ui.PrincipalActivity.EDITFLAG;
import static com.example.mamorky.socialplayer.ui.base.BaseActivity.baseActivity;

/**
 * Created by mamorky on 5/11/17.
 */

public class AlbumViewImp extends Fragment implements AlbumView{

    public static final String TAG = "AlbumFragmentTag";
    public static final String STATETAG = "StateAlbum";

    private  static long currentVisiblePosition = 0;

    private Parcelable mRecyclerState = new Bundle();

    private RecyclerView recyclerAlbum;
    private FastScroller fastScroller;

    private AlbumAdapter albumAdapter;
    AlbumPresenter presenter;

    private Parcelable mListState;

    StyleUtils.toolBarPropieties mtoolBarPropieties;

    RecyclerItemClickListener itemClickListener;

    int color;

    private ArrayList<Album> albums;

    public static AlbumViewImp newInstance(Bundle arguments){
        AlbumViewImp albumViewImp = new AlbumViewImp();
        if(arguments != null){
            albumViewImp.setArguments(arguments);
        }

        return albumViewImp;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mtoolBarPropieties = (StyleUtils.toolBarPropieties) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View viewRoot = inflater.inflate(R.layout.fragment_album,container,false);

        recyclerAlbum = (RecyclerView)viewRoot.findViewById(R.id.recyclerAlbum);
        recyclerAlbum.setHasFixedSize(true);

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE)
            recyclerAlbum.setLayoutManager(new GridLayoutManager(this.getActivity(), ((PrincipalActivity)getActivity()).preferences.getNum_col_land()));
        else
            recyclerAlbum.setLayoutManager(new GridLayoutManager(this.getActivity(), ((PrincipalActivity)getActivity()).preferences.getNum_col_portrait()));

        recyclerAlbum.getLayoutManager().setAutoMeasureEnabled(false);

        recyclerAlbum.setHasFixedSize(true);
        recyclerAlbum.setItemViewCacheSize(20);
        recyclerAlbum.setDrawingCacheEnabled(true);
        recyclerAlbum.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        albums = new ArrayList<>();

        fastScroller = viewRoot.findViewById(R.id.fastscroll);

        color = ((PrincipalActivity)getActivity()).preferences.getColor();

        fastScroller.setHandleColor(color);
        fastScroller.setBubbleColor(color);

        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = new AlbumPresenterImp(this);

        itemClickListener = new RecyclerItemClickListener() {
            @Override
            public void onClick(Object objeto) {
                loadSong((Album) objeto);
            }
        };

        presenter.loadAlbums();

        super.onViewCreated(view, savedInstanceState);
    }

    public void loadSong(Album album){
        Bundle bundle = new Bundle();
        bundle.putParcelable("ALBUMTAG",album);
        SongViewImp fragment = SongViewImp.newInstance(bundle);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState!=null){
            restorePreviousState(savedInstanceState);
            try {
                recyclerAlbum.getLayoutManager().onRestoreInstanceState(mListState);
            }
            catch(Exception e){}
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mtoolBarPropieties.changeToolbarText(getActivity().getResources().getString(R.string.activity_name_albums));

        if (mListState != null) {
            recyclerAlbum.getLayoutManager().onRestoreInstanceState(mListState);
        }
    }

    @Override
    public void showAdapter(ArrayList<Album> albums) {
        this.albums.addAll(albums);
        albumAdapter = new AlbumAdapter(albums,itemClickListener,color);
        recyclerAlbum.setAdapter(albumAdapter);
        fastScroller.setRecyclerView(recyclerAlbum);
    }

    @Override
    public void onAlbumClicked(Album album) {
        Toast.makeText(getActivity(),album.getAlbumName(),Toast.LENGTH_LONG).show();
    }

    public void restorePreviousState(Bundle savedInstanceState){
        if(savedInstanceState != null)
            mListState = savedInstanceState.getParcelable(STATETAG);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mListState = recyclerAlbum.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(STATETAG, mListState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
