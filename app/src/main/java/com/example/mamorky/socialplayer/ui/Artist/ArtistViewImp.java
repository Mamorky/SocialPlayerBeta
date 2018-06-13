package com.example.mamorky.socialplayer.ui.Artist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;

import com.example.mamorky.socialplayer.adapter.ArtistAdapter;
import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.ui.PrincipalActivity;
import com.example.mamorky.socialplayer.ui.Song.SongViewImp;
import com.example.mamorky.socialplayer.util.PlayerUtils;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;
import com.example.mamorky.socialplayer.util.StyleUtils;
import com.example.mamorky.socialplayer.util.UserPreferences;
import com.futuremind.recyclerviewfastscroll.FastScroller;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mamorky.socialplayer.ui.Album.AlbumViewImp.STATETAG;
import static com.example.mamorky.socialplayer.ui.PrincipalActivity.EDITFLAG;

public class ArtistViewImp extends Fragment implements ArtistView{

    private RecyclerView recyclerArtist;
    private FastScroller fastScroller;

    private ArtistPresenter presenter;

    StyleUtils.toolBarPropieties mtoolBarPropieties;

    private Parcelable mListState;
    public static final String TAG = "ARTISTTAG";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View viewRoot = inflater.inflate(R.layout.fragment_artist,container,false);

        try {
            recyclerArtist = (RecyclerView) viewRoot.findViewById(R.id.recylcerArtist);
            recyclerArtist.setHasFixedSize(true);

            if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE)
                recyclerArtist.setLayoutManager(new GridLayoutManager(this.getActivity(), ((PrincipalActivity)getActivity()).preferences.getNum_col_land()));
            else
                recyclerArtist.setLayoutManager(new GridLayoutManager(this.getActivity(), ((PrincipalActivity)getActivity()).preferences.getNum_col_portrait()));

        }
        catch (Exception e){}


        fastScroller = viewRoot.findViewById(R.id.fastscroll);

        int color = ((PrincipalActivity)getActivity()).preferences.getColor();

        fastScroller.setHandleColor(color);
        fastScroller.setBubbleColor(color);

        return viewRoot;
    }

    public static ArtistViewImp newInstance(Bundle arguments){
        ArtistViewImp artistViewImp = new ArtistViewImp();
        if(arguments != null){
            artistViewImp.setArguments(arguments);
        }
        return artistViewImp;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mtoolBarPropieties = (StyleUtils.toolBarPropieties) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState!=null){
            restorePreviousState(savedInstanceState);
            try {
            recyclerArtist.getLayoutManager().onRestoreInstanceState(mListState);
            }
            catch (Exception e){}
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mtoolBarPropieties.changeToolbarText(getActivity().getResources().getString(R.string.activity_name_artist));
        if(mListState != null)
            recyclerArtist.getLayoutManager().onRestoreInstanceState(mListState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = new ArtistPresenterImp(this);

        RecyclerItemClickListener itemClickListener = new RecyclerItemClickListener() {
            @Override
            public void onClick(Object objeto) {
                loadSong((Artist) objeto);
            }
        };

        presenter.createAdapter(itemClickListener,((PrincipalActivity)ArtistViewImp.this.getActivity()).preferences.getColor());
        super.onViewCreated(view, savedInstanceState);
    }


    public void loadSong(Artist artist){
        Bundle bundle = new Bundle();
        bundle.putParcelable("ARTISTTAG",artist);
        SongViewImp fragment = SongViewImp.newInstance(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction().addToBackStack(null);
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    @Override
    public void putAdapter(ArtistAdapter artistAdapter) {
        recyclerArtist.setAdapter(artistAdapter);
        fastScroller.setRecyclerView(recyclerArtist);
    }

    public void restorePreviousState(Bundle savedInstanceState){
        if(savedInstanceState != null)
            mListState = savedInstanceState.getParcelable(STATETAG);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mListState = recyclerArtist.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(STATETAG, mListState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
