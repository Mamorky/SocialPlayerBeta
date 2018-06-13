package com.example.mamorky.socialplayer.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.ui.Album.AlbumViewImp;
import com.example.mamorky.socialplayer.ui.Artist.ArtistViewImp;
import com.example.mamorky.socialplayer.ui.Login.LoginViewImp;
import com.example.mamorky.socialplayer.ui.Playlist.PlaylistViewImp;
import com.example.mamorky.socialplayer.ui.Song.SongViewImp;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.FirebaseUserManager;
import com.example.mamorky.socialplayer.util.PlayerUtils;
import com.example.mamorky.socialplayer.util.StyleUtils;
import com.example.mamorky.socialplayer.util.UserPreferences;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PrincipalActivity extends BaseActivity implements  GoogleApiClient.OnConnectionFailedListener,
                                                                StyleUtils.toolBarPropieties,
                                                                PlayerUtils.listenerPrincipalActivity
                                                               {

    private BottomNavigationView mBottomNavigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    private ConstraintLayout cly_song_actual;
    private TextView txv_song_actual;
    private TextView txv_artist_actual;
    private ImageView imgPlay_Actual_Song;

    public static final String EDITFLAG = "TipCalculator";

    private GoogleApiClient googleApiClient;
    GoogleSignInResult result;

    private static boolean firstTime = true;

    SongViewImp songFragment = new SongViewImp();
    AlbumViewImp albumFragment = new AlbumViewImp();
    ArtistViewImp artistFragment = new ArtistViewImp();
    Player playerFragment = new Player();

    private String TAG_USER_PREF = "TAG_USER_PREF";
    public static boolean existsSaveInstance = false;

    PlayerUtils playerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        BaseContext.setContext(this);

        setupBottomNavigation();

        playerUtils = PlayerUtils.getInstance();

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);

        cly_song_actual = findViewById(R.id.cly_actual_song);
        txv_song_actual = findViewById(R.id.txv_actual_song);
        txv_artist_actual = findViewById(R.id.txv_actual_artist);
        imgPlay_Actual_Song = findViewById(R.id.imgPlay_Actual_Song);

        txv_song_actual.setSelected(true);
        txv_artist_actual.setSelected(true);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        playerUtils.setListenerPrincipalActivity(this);

        songFragment = SongViewImp.newInstance(null);
        albumFragment = AlbumViewImp.newInstance(null);
        artistFragment = ArtistViewImp.newInstance(null);
        playerFragment = Player.newInstance(null);

        imgPlay_Actual_Song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerUtils.play_song();
                if(playerUtils.getMediaPlayer().isPlaying())
                    actualizarActualBarSongBoton(true);
                else
                    actualizarActualBarSongBoton(false);
            }
        });

        cly_song_actual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPlayer();
            }
        });

        if(getSupportFragmentManager().findFragmentById(R.id.main_frame_layout) == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_frame_layout,songFragment,SongViewImp.TAG).commit();
            firstTime = false;
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        if(savedInstanceState != null){
            preferences = (UserPreferences) savedInstanceState.getSerializable(TAG_USER_PREF);
            changeColorPrimaryActivity();
        }
        else {
            progressDialogPreferences.show();
            progressDialogPreferences.setMessage("Cargando Preferencias de Firebase ...");

            FirebaseUserManager.getInstance().getUserPrefences().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserPreferences preferencesTmp = dataSnapshot.getValue(UserPreferences.class);
                    if(preferencesTmp != null)
                        preferences = preferencesTmp;

                    if(preferences == null)
                        preferences = new UserPreferences(PrincipalActivity.this);
                    try {
                        changeColorPrimaryActivity();
                        changeColorFragments();
                        progressDialogPreferences.dismiss();
                        FirebaseUserManager.getInstance().setUserPreferences(preferences);
                    }catch (Exception e){
                        progressDialogPreferences.dismiss();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progressDialogPreferences.dismiss();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()){
            result = opr.get();
            setupNavigationView();
        }
        else
        {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    result = googleSignInResult;
                    setupNavigationView();
                }
            });
        }
    }

    private void setupNavigationView(){
        View headerview = navView.getHeaderView(0);

        TextView txv_username = headerview.findViewById(R.id.txv_user);
        TextView txv_email = headerview.findViewById(R.id.txv_email);
        ImageView img_user = headerview.findViewById(R.id.profile_image);


        if(result != null){
            txv_username.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            txv_email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            Glide.with(this).load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).into(img_user);
        }

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_about:
                        final Intent intent = new Intent(getApplicationContext(),AboutActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_day_night:
                        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_AUTO || AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                                Toast.makeText(PrincipalActivity.this, R.string.active_mode_day,Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(PrincipalActivity.this, R.string.modo_day_night_desactived,Toast.LENGTH_SHORT).show();
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        }
                        else{
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            Toast.makeText(PrincipalActivity.this, R.string.active_mode_night,Toast.LENGTH_SHORT).show();
                        }
                        firstTime = false;
                        recreate();

                        break;

                    case R.id.action_color:
                        int colorPref =  preferences.getColor();
                        int colorNewPref = Color.rgb(Color.red(colorPref), Color.green(colorPref), Color.blue(colorPref));

                        ColorPickerDialogBuilder
                                .with(PrincipalActivity.this)
                                .setTitle(getString(R.string.str_cambiar_color))
                                .initialColor(colorNewPref)
                                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE).showAlphaSlider(false)
                                .density(12)
                                .setOnColorSelectedListener(new OnColorSelectedListener() {
                                    @Override
                                    public void onColorSelected(int selectedColor) {
                                        toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                                    }

                                    private void toast(String s) {
                                    }
                                })
                                .setPositiveButton("ok", new ColorPickerClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                        preferences.setColor(selectedColor);
                                        FirebaseUserManager.getInstance().setUserPreferences(preferences);
                                        changeColorPrimaryActivity();
                                        changeColorFragments();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .build()
                                .show();
                        break;
                    case R.id.action_close_session:
                        FirebaseAuth.getInstance().signOut();

                        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                if(status.isSuccess()){
                                    Intent intent = new Intent(PrincipalActivity.this, LoginViewImp.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(PrincipalActivity.this, R.string.error_log_out,Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
                item.setChecked(true);
                getSupportActionBar().setTitle(item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(playerUtils.getSong() != null)
            showActualSongBar();
        cargarDatosReproActBar();

        if(playerUtils.getPlayOptionsListener() != null){
            playerUtils.getPlayOptionsListener().actualizarTextoCancion();
            playerUtils.getPlayOptionsListener().actualizarVistaBotonPlay();
        }
    }

    public void changeColorFragments(){
        getSupportFragmentManager()
                .beginTransaction()
                .detach(getSupportFragmentManager().findFragmentById(R.id.main_frame_layout))
                .attach(getSupportFragmentManager().findFragmentById(R.id.main_frame_layout))
                .commit();
    };

    private void changeColorPrimaryActivity(){
        int color = preferences.getColor();

        try {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
            mBottomNavigationView.setBackgroundColor(color);
            navView.getHeaderView(0).setBackgroundColor(color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(color);
            }

            if(playerUtils.getPlayOptionsListener() != null)
                playerUtils.getPlayOptionsListener().changeColorPlayer();

            cly_song_actual.setBackgroundColor(color);
            cly_song_actual.getBackground().setAlpha(99);
        }
        catch (Exception e){}
    }

    private void setupBottomNavigation() {

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                getSupportFragmentManager().popBackStack();

                switch (item.getItemId()) {
                    case R.id.action_song_selector:
                        loadSong();
                        return true;
                    case R.id.action_album_selector:
                        loadAlbum();
                        return true;
                    case R.id.action_artist_selector:
                        loadArtist();
                        return true;
                    /*case R.id.action_list_selector:
                        loadPlaylist();
                        return true;*/
                }

                return false;
            }
        });
    }

    public void loadSong(){
        toolbar.setTitle(R.string.activity_name_canciones);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);
        ft.replace(R.id.main_frame_layout,songFragment,SongViewImp.TAG).commit();
    }

    public void loadAlbum() {
        toolbar.setTitle(R.string.activity_name_albums);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if(getSupportFragmentManager().findFragmentById(R.id.main_frame_layout).getTag() == SongViewImp.TAG)
            ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
        else
            ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);

        ft.replace(R.id.main_frame_layout,albumFragment,AlbumViewImp.TAG).commit();
    }

    public void loadArtist() {
        toolbar.setTitle(R.string.activity_name_artist);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
        ft.replace(R.id.main_frame_layout,artistFragment,ArtistViewImp.TAG);
        ft.commit();
    }

    public void loadPlaylist() {
        toolbar.setTitle(R.string.activity_name_playlists);

        PlaylistViewImp fragment = new PlaylistViewImp();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
        ft.replace(R.id.main_frame_layout,fragment);
        ft.commit();
    }

    public void loadPlayer(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_bottom,R.anim.enter_from_top);
        ft.replace(R.id.main_frame_layout,playerFragment,Player.TAG).addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.START))
            drawerLayout.closeDrawer(Gravity.START);
        else{
            if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                super.onBackPressed();
            }
            else
            {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.quieres_salir)
                        .setMessage(R.string.quires_salir_l)
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PrincipalActivity.this.finish();
                                System.exit(1);
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        }
    }

    @Override
    public void cargarDatosReproActBar() {
        if(playerUtils.getSong() != null){
            if(playerUtils.getMediaPlayer().isPlaying())
                imgPlay_Actual_Song.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
            else
                imgPlay_Actual_Song.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
            txv_song_actual.setText(playerUtils.getSong().get_name());
            txv_artist_actual.setText(playerUtils.getSong().getArtist_name());
        }
    }

    @Override
    public void actualizarActualBarSongBoton(boolean estaRepro) {
        if(estaRepro)
            imgPlay_Actual_Song.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
        else
            imgPlay_Actual_Song.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
    }

    @Override
    public void changeToolbarText(String titulo) {
        toolbar.setTitle(titulo);
    }

    public void showActualSongBar() {
        if(cly_song_actual.getVisibility() == View.GONE)
            cly_song_actual.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(TAG_USER_PREF,preferences);
        outState.putBoolean("FIRST_TIME",firstTime);
        existsSaveInstance = true;

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        firstTime = true;
        FirebaseUserManager.destroyInstance();
        super.onDestroy();
    }
}
