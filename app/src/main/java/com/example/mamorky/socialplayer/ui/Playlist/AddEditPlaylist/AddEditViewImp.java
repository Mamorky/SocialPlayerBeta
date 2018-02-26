package com.example.mamorky.socialplayer.ui.Playlist.AddEditPlaylist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.ui.base.BaseActivity;
import com.example.mamorky.socialplayer.util.AddEdit;
import com.example.mamorky.socialplayer.util.UtilsImages;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

/**
 * Created by mamorky on 12/01/18.
 */

public class AddEditViewImp extends BaseActivity implements AddEditView{
    private AddEdit addEdit;

    private AddEditPresenter presenter;

    private EditText edtNombrePlaylist;
    private TextInputLayout tilNombrePlaylistAddEdit;
    private ImageView imgPlaylist;
    private android.support.v7.widget.Toolbar toolbar;

    private String rutaImagen;

    private FloatingActionButton fabSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        edtNombrePlaylist = findViewById(R.id.edtNombrePlaylist);
        tilNombrePlaylistAddEdit = findViewById(R.id.tilNombrePlaylist);
        imgPlaylist = findViewById(R.id.imgPlaylistAddEdit);
        fabSave = findViewById(R.id.fabSave);

        toolbar = findViewById(R.id.toolbarPlaylistAddEdit);
        setSupportActionBar(toolbar);

        addEdit = new AddEdit();
        addEdit.modo = getIntent().getExtras().getInt(AddEdit.TAG_ADDEDIT);

        presenter = new AddEditPresenterImp(this);

        imgPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(AddEditViewImp.this)
                        .withRequestCode(1)
                        .withFilter(Pattern.compile(".*\\.(png|jpg)$"))
                        .withFilterDirectories(false) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
            }
        });


        if(addEdit.modo == AddEdit.ADD){
            getSupportActionBar().setTitle(R.string.add_playlist_string);
            fabSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.addPlaylist(edtNombrePlaylist.getText().toString(),((BitmapDrawable)imgPlaylist.getDrawable()).getBitmap());
                }
            });
        }

        else if(addEdit.modo == AddEdit.EDIT){
            getSupportActionBar().setTitle(R.string.edit_playlist_string);
            final Playlist playlistEdt = (Playlist) getIntent().getExtras().getParcelable(Playlist.TAB_OBJ);
            edtNombrePlaylist.setText(playlistEdt.getNamePlaylist());
            if(playlistEdt.getBitMapPlaylist()!= null)
                imgPlaylist.setImageBitmap(playlistEdt.getBitMapPlaylist());

            fabSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BitmapDrawable playlistImageDrawable = (BitmapDrawable) imgPlaylist.getDrawable();
                    presenter.editPlaylist(playlistEdt.getIdPlaylist(),edtNombrePlaylist.getText().toString(),playlistImageDrawable.getBitmap());
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            rutaImagen = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            imgPlaylist.setImageBitmap(BitmapFactory.decodeFile(rutaImagen));
        }
    }

    @Override
    public void onSetNameEmpty() {
        tilNombrePlaylistAddEdit.setError(getString(R.string.error_empty_name_playlist_add_edit));
    }

    @Override
    public void onSetImageError() {
        Toast.makeText(this,getString(R.string.error_image_playlist_add_edit),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        finish();
    }

}
