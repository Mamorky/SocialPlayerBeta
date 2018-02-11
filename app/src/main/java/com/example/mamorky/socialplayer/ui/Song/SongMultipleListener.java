package com.example.mamorky.socialplayer.ui.Song;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Toolbar;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.data.db.pojo.Song;

import java.util.ArrayList;

import adapter.SongAdapter;

/**
 * Created by usuario on 18/12/17.
 */

class SongMultipleListener implements AbsListView.MultiChoiceModeListener {
    private SongPresenter presenter;
    private ArrayList<Song> tmp = new ArrayList<>();
    private int count = 0;
    private SongAdapter adapter;
    private ActionBar actionBar;

    public SongMultipleListener(SongPresenter presenter, SongAdapter adapter, ActionBar actionBar) {
        this.presenter = presenter;
        this.adapter = adapter;
        this.actionBar = actionBar;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if(checked){
            count++;
            presenter.setNewSelection(position);
        } else{
            count--;
            presenter.removeSelection(position);
        }

        mode.setTitle(count+" seleccionados");

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.activity_menu_delete,menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        actionBar.hide();
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                presenter.deleteSelection();
                presenter.loadSong();
                break;
        }

        mode.finish();
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        count = 0;
        presenter.clearSelection(mode,count);
        actionBar.show();
    }
}
