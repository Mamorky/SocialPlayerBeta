package com.example.mamorky.socialplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mamorky.socialplayer.R;
import com.example.mamorky.socialplayer.data.db.pojo.Playlist;
import com.example.mamorky.socialplayer.util.UtilsImages;

import java.util.ArrayList;

/**
 * Created by mamorky on 11/01/18.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>{
    private ArrayList<Playlist> playlists;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PlaylistAdapter(ArrayList<Playlist> playlists){
        this.playlists = playlists;
    }

    @Override
    public PlaylistAdapter.PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_playlist,null);
        PlaylistViewHolder playlistViewHolder = new PlaylistViewHolder(view);
        return playlistViewHolder;
    }

    @Override
    public void onBindViewHolder(final PlaylistViewHolder holder, int position) {
        if(playlists.get(position).getBitMapPlaylist() == null)
            holder.playlistImage.setImageResource(R.drawable.playlist);
        else
            holder.playlistImage.setImageBitmap(playlists.get(position).getBitMapPlaylist());

        holder.playlistImage.setBackgroundColor(UtilsImages.colorAlea());

        holder.playlistName.setText(playlists.get(position).getNamePlaylist());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private ImageView playlistImage;
        private TextView playlistName;

        public PlaylistViewHolder(View itemView) {
            super(itemView);
            playlistImage = (ImageView)itemView.findViewById(R.id.imgPlaylist);
            playlistName = (TextView)itemView.findViewById(R.id.txvTitlePlaylist);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(Menu.NONE, R.id.action_delete,
                    Menu.NONE, R.string.action_delete);
            menu.add(Menu.NONE, R.id.action_modify,
                    Menu.NONE, R.string.action_modify);
        }
    }

    public Playlist playlistSelected(int position){
        return playlists.get(position);
    }

    @Override
    public void onViewRecycled(PlaylistViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }
}
