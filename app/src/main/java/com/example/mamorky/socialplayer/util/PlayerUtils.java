package com.example.mamorky.socialplayer.util;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.ui.base.BaseContext;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class PlayerUtils {

    public enum tipeAlea {
        song,
        fab,
        insede_player
    }

    static PlayerUtils playerUtils;

    private ArrayList<Song> reproActual;
    private ArrayList<Song> copyReproActual;

    private int posSong;
    private MediaPlayer mediaPlayer;
    private int actualTime;
    private Song song;
    private boolean newSongPush;
    private boolean shuffleActive;

    private listenerPrincipalActivity listenerPrincipalActivity;
    private listener_music_control listener_music_control;
    private listener_album listener_album;
    private listener_artist listener_artist;
    private AudioManager am;

    BluetoothAdapter mBtAdapter;
    BluetoothA2dp mA2dpService;

    public interface listenerPrincipalActivity {
        void cargarDatosReproActBar();

        void actualizarActualBarSongBoton(boolean estaRepro);
    }

    public interface listener_album {
        void actualizarColorAlbum();
    }

    public interface listener_artist {
        void actualizarColorArtista();
    }

    public interface listener_music_control {
        void actualizarVistaBotonPlay();

        void actualizarTextoCancion();

        void actualizarBotonAlea(Boolean activo);

        void changeColorPlayer();
    }

    private PlayerUtils() {
        this.mediaPlayer = new MediaPlayer();
        this.song = null;
        this.listenerPrincipalActivity = null;
        this.listener_music_control = null;
        this.posSong = 0;
        this.actualTime = 0;
        this.newSongPush = false;
        this.shuffleActive = false;
        this.copyReproActual = new ArrayList<>();

        am = (AudioManager)BaseContext.getContext().getSystemService(Context.AUDIO_SERVICE);

        am.requestAudioFocus(focusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
    }

    public static PlayerUtils getInstance() {
        if (playerUtils == null) {
            playerUtils = new PlayerUtils();
        }
        return playerUtils;
    }

    public ArrayList<Song> getReproActual() {
        return reproActual;
    }

    public void setReproActual(ArrayList<Song> reproActual) {
        this.reproActual = reproActual;
    }

    public ArrayList<Song> getCopyReproActual() {
        return copyReproActual;
    }

    public void setCopyReproActual(ArrayList<Song> copyReproActual) {
        this.copyReproActual = copyReproActual;
    }

    public int getPosSong() {
        return posSong;
    }

    public void setPosSong(int posSong) {
        this.posSong = posSong;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public int getActualTime() {
        return actualTime;
    }

    public void setActualTime(int actualTime) {
        this.actualTime = actualTime;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }

    public boolean isNewSongPush() {
        return newSongPush;
    }

    public void setNewSongPush(boolean newSongPush) {
        this.newSongPush = newSongPush;
    }

    public boolean isShuffleActive() {
        return shuffleActive;
    }

    //Activar/Desactivar Shuffle
    public void setShuffleActive(boolean shuffleActive, tipeAlea tipo) {
        this.shuffleActive = shuffleActive;

        if (listener_music_control != null)
            listener_music_control.actualizarBotonAlea(shuffleActive);

        //Si esta activo
        if (this.shuffleActive) {
            if (tipo == tipeAlea.song && reproActual.size() > 1) {
                //Asignar la cancion actual
                song = reproActual.get(posSong);

                reproActual.remove(posSong);

                //Desordenamos el ArrayList
                Collections.shuffle(this.reproActual);

                this.posSong = 0;

                Song firstSong = reproActual.get(posSong);
                reproActual.add(posSong, song);
                reproActual.add(firstSong);
            } else if (tipo == tipeAlea.insede_player) {
                Collections.shuffle(reproActual);
                reproActual.remove(song);
                reproActual.add(song);
            } else if (tipo == tipeAlea.fab) {
                //Desordenamos el ArrayList
                this.copyReproActual.clear();
                this.copyReproActual = new ArrayList<>(this.reproActual);
                Collections.shuffle(this.reproActual);
                this.posSong = 0;
            }
        }
        //No esta activo
        else {
            if (copyReproActual.size() > 0) {
                //Buscamos la posicion de la cancion actual en la lista original y la guardamos
                for (int i = 0; i < copyReproActual.size(); i++) {
                    if (copyReproActual.get(i).getIdSong() == reproActual.get(posSong).getIdSong()) {
                        this.posSong = i;
                        break;
                    }
                }

                //Vaciamos la lista de la reproduccion Actual
                this.reproActual.clear();
                //Añadimos la lista inicial a la actual
                this.reproActual = new ArrayList<>(this.copyReproActual);
            }
        }
    }

    public int obtain_skb_position() {
        try {
            //Cuidado divide by zero exception, Ya probada
            return (100 * mediaPlayer.getCurrentPosition()) / mediaPlayer.getDuration();
        } catch (Exception e) {
            return 0;
        }
    }

    public int obtain_current_second(int value_act) {
        return (mediaPlayer.getDuration() * value_act) / 100;
    }

    public void prev_song() {
        if (playerUtils.getPosSong() == 0)
            playerUtils.setPosSong(playerUtils.getReproActual().size() - 1);
        else
            playerUtils.setPosSong(playerUtils.getPosSong() - 1);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(playerUtils.getReproActual().get(playerUtils.getPosSong()).getPathSong());
            mediaPlayer.prepare();
            mediaPlayer.start();
            listener_music_control.actualizarVistaBotonPlay();

            am.requestAudioFocus(focusChangeListener,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void next_song() {
        try {
            playerUtils.setPosSong((playerUtils.getPosSong() + 1) % playerUtils.getReproActual().size());
            mediaPlayer.reset();
            mediaPlayer.setDataSource(playerUtils.getReproActual().get(playerUtils.getPosSong()).getPathSong());
            mediaPlayer.prepare();
            mediaPlayer.start();
            listener_music_control.actualizarVistaBotonPlay();

            am.requestAudioFocus(focusChangeListener,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN);
            //updateSongDates()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String cadenaSegundos(int millisec) {
        try {
            Date date = new Date(millisec);
            SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
            String myTime = formatter.format(date);

            return myTime;
        } catch (Exception e) {
            return "0";
        }

    }

    public void play_song() {
        try {
            if (mediaPlayer.isPlaying() && newSongPush == false) {
                mediaPlayer.pause();
                playerUtils.setActualTime(mediaPlayer.getCurrentPosition());
                listenerPrincipalActivity.actualizarActualBarSongBoton(false);
            } else {
                listenerPrincipalActivity.actualizarActualBarSongBoton(true);
                if (playerUtils.getActualTime() != 0)
                    mediaPlayer.start();
                else {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(playerUtils.getReproActual().get(playerUtils.getPosSong()).getPathSong());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
                am.requestAudioFocus(focusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN);

                newSongPush = false;
            }
            if (listener_music_control != null) {
                listener_music_control.actualizarVistaBotonPlay();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                song = reproActual.get(posSong);
                if (listenerPrincipalActivity != null)
                    listenerPrincipalActivity.cargarDatosReproActBar();
                if (listener_music_control != null)
                    listener_music_control.actualizarTextoCancion();
            }
        });

        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                next_song();
            }
        });
    }

    public listenerPrincipalActivity getListenerPrincipalActivity() {
        return listenerPrincipalActivity;
    }

    public void setListenerPrincipalActivity(listenerPrincipalActivity listenerPrincipalActivity) {
        this.listenerPrincipalActivity = listenerPrincipalActivity;
    }

    public void setPlayOptionsListener(listener_music_control listener_music_control) {
        this.listener_music_control = listener_music_control;
    }

    public listener_music_control getPlayOptionsListener() {
        return listener_music_control;
    }

    public PlayerUtils.listener_album getListener_album() {
        return listener_album;
    }

    public void setListener_album(PlayerUtils.listener_album listener_album) {
        this.listener_album = listener_album;
    }

    public PlayerUtils.listener_artist getListener_artist() {
        return listener_artist;
    }

    public void setListener_artist(PlayerUtils.listener_artist listener_artist) {
        this.listener_artist = listener_artist;
    }

    private AudioManager.OnAudioFocusChangeListener focusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    AudioManager am =(AudioManager) BaseContext.getContext().getSystemService(Context.AUDIO_SERVICE);
                    switch (focusChange) {
                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) :
                            //PlayerUtils.getInstance().am = (AudioManager)BaseContext.getContext().getSystemService(Context.AUDIO_SERVICE);
                            if(playerUtils != null)
                                mediaPlayer.setVolume(0.2f, 0.2f);
                            break;
                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) :
                            //PlayerUtils.getInstance().am = (AudioManager)BaseContext.getContext().getSystemService(Context.AUDIO_SERVICE);
                            if(playerUtils != null)
                                mediaPlayer.pause();
                            break;

                        case (AudioManager.AUDIOFOCUS_LOSS) :
                            //PlayerUtils.getInstance().am = (AudioManager)BaseContext.getContext().getSystemService(Context.AUDIO_SERVICE);
                            if(playerUtils != null)
                                mediaPlayer.pause();
                            break;

                        case (AudioManager.AUDIOFOCUS_GAIN) :
                            //PlayerUtils.getInstance().am = (AudioManager)BaseContext.getContext().getSystemService(Context.AUDIO_SERVICE);
                            if(playerUtils != null)
                                mediaPlayer.setVolume(1f, 1f);
                            break;
                        default: break;
                    }
                }
            };
}
