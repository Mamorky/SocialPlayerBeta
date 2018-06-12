package com.example.mamorky.socialplayer.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.data.db.repositories.dao.AlbumDao;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.PlayerUtils;
import com.example.mamorky.socialplayer.util.StyleUtils;
import com.squareup.picasso.Picasso;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mamorky.socialplayer.ui.PrincipalActivity.EDITFLAG;

public class Player extends Fragment implements PlayerUtils.listener_music_control{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TAG = "TAG_PLAYER";

    private String mParam1;
    private String mParam2;

    private FloatingActionButton fb_play,fb_next,fb_prev;
    private ImageView img_cover,img_shuffle;
    private MediaPlayer mediaPlayer;

    private TextView txv_artist,txv_song,txv_init_second,txv_end_second;

    private SeekBar skb_second;

    private StyleUtils.toolBarPropieties mToolbarPropierties;

    Handler updateHandler = new Handler();

    ConstraintLayout cly_background_player;

    int color;

    private PlayerUtils playerUtils;

    @Override
    public void actualizarVistaBotonPlay() {
        if(mediaPlayer.isPlaying())
            fb_play.setImageResource(android.R.drawable.ic_media_pause);
        else
            fb_play.setImageResource(android.R.drawable.ic_media_play);
    }

    @Override
    public void actualizarTextoCancion() {
        if(playerUtils.getSong() != null){
            File imgFile = null;
            try {
                 imgFile = new  File(AlbumDao.getCover(playerUtils.getSong().getIdAlbum()));
            }
            catch (Exception e){}

            if(imgFile == null)
                Picasso.with(BaseContext.getContext()).
                        load(R.drawable.ic_album_black_24dp).fit().
                        error(R.drawable.ic_album_black_24dp).
                        into(img_cover);
            else
                Picasso.with(BaseContext.getContext()).
                        load(imgFile).fit().
                        error(R.drawable.ic_album_black_24dp).
                        into(img_cover);


            txv_song.setText(playerUtils.getSong().get_name());
            txv_artist.setText(playerUtils.getSong().getArtist_name());
            if(txv_end_second != null)
                txv_end_second.setText(playerUtils.cadenaSegundos(playerUtils.getMediaPlayer().getDuration()));
            mToolbarPropierties.changeToolbarText(playerUtils.getSong().get_name());
        }
    }

    @Override
    public void actualizarBotonAlea(Boolean activo) {
        if(activo){
            img_shuffle.setColorFilter(color);
            img_shuffle.setAlpha(255);
        }
        else {
            img_shuffle.setColorFilter(color);
            img_shuffle.setAlpha(99);
        }
    }


    public static Player newInstance(String param1) {
        Player fragment = new Player();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerUtils = PlayerUtils.getInstance();
        playerUtils.setPlayOptionsListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_player, container, false);

        cly_background_player = viewRoot.findViewById(R.id.cly_background_player);

        fb_prev = viewRoot.findViewById(R.id.fb_ply_prev);
        fb_play = viewRoot.findViewById(R.id.fb_ply_play);
        fb_next = viewRoot.findViewById(R.id.fb_ply_next);

        skb_second = viewRoot.findViewById(R.id.skb_second_song);

        txv_artist = viewRoot.findViewById(R.id.txv_ply_artist);
        txv_song  = viewRoot.findViewById(R.id.txv_ply_song);
        txv_init_second = viewRoot.findViewById(R.id.txv_init_second);
        txv_end_second = viewRoot.findViewById(R.id.txv_end_second);

        img_cover = viewRoot.findViewById(R.id.img_ply_cover);
        img_shuffle = viewRoot.findViewById(R.id.img_shuffle_player);

        txv_artist.setSelected(true);
        txv_song.setSelected(true);

        color = ((PrincipalActivity)getActivity()).preferences.getColor();

        actualizarBotonAlea(playerUtils.isShuffleActive());

        changeColorPlayer();

        if(mediaPlayer == null){
            mediaPlayer = playerUtils.getMediaPlayer();
        }

        return viewRoot;
    }

    @Override
    public void onResume() {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.cly_actual_song).setVisibility(View.GONE);
        actualizarVistaBotonPlay();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        getActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.cly_actual_song).setVisibility(View.VISIBLE);
        getFragmentManager().beginTransaction().addToBackStack(null);
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fb_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerUtils.prev_song();
            }
        });
        fb_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerUtils.next_song();
            }
        });
        fb_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerUtils.play_song();
            }
        });
        fb_play.setImageResource(android.R.drawable.ic_media_pause);

        updateHandler.postDelayed(timerRunnable,100);

        //Llama al metodo que actualiza el texto de la cancion
        actualizarTextoCancion();

        img_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerUtils.isShuffleActive()){
                    playerUtils.setShuffleActive(false, PlayerUtils.tipeAlea.insede_player);
                }
                else{
                    playerUtils.setSong(playerUtils.getReproActual().get(playerUtils.getPosSong()));
                    playerUtils.setShuffleActive(true, PlayerUtils.tipeAlea.insede_player);
                }
            }
        });

        skb_second.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                {
                    mediaPlayer.seekTo(playerUtils.obtain_current_second(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mToolbarPropierties = (StyleUtils.toolBarPropieties) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        timerRunnable = null;
    }

    Runnable timerRunnable = new Runnable() {
        public void run() {
           skb_second.setProgress(playerUtils.obtain_skb_position());
           txv_init_second.setText(playerUtils.cadenaSegundos(playerUtils.getMediaPlayer().getCurrentPosition()));
           updateHandler.postDelayed(this, 100);
        }
    };

    @Override
    public void changeColorPlayer(){
        try {
            color = ((PrincipalActivity)getActivity()).preferences.getColor();

            fb_play.setBackgroundTintList(ColorStateList.valueOf(color));
            fb_next.setBackgroundTintList(ColorStateList.valueOf(color));
            fb_prev.setBackgroundTintList(ColorStateList.valueOf(color));

            skb_second.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
            skb_second.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_IN);

            img_cover.setBackgroundColor(color);
            img_cover.getBackground().setAlpha(99);

            cly_background_player.setBackgroundColor(color);
            cly_background_player.getBackground().setAlpha(20);

            if(playerUtils.isShuffleActive() == true)
                img_shuffle.setColorFilter(color);
            else{
                img_shuffle.setColorFilter(color);
                img_shuffle.setAlpha(99);
            }
        }
        catch (Exception e)
        {
        }
    };
}
