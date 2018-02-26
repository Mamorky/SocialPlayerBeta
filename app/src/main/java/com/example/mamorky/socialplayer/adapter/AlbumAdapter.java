package com.example.mamorky.socialplayer.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;

import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.UtilsImages;

/**
 * Created by mamorky on 5/11/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private ArrayList<Album> albums;

    public AlbumAdapter(ArrayList<Album> albums){
        this.albums = albums;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_album,null);
        AlbumViewHolder albumViewHolder = new AlbumViewHolder(view);
        return albumViewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

        if(albums.get(position).getAlbumImage() != null){
            RoundedBitmapDrawable imagenDrawable = UtilsImages.roundImages(Drawable.createFromPath(albums.get(position).getAlbumImage()),8);
            holder.albumImage.setImageDrawable(imagenDrawable);
        }
        else {
            holder.albumImage.setImageDrawable(UtilsImages.roundImages(BaseContext.getContext().getResources().getDrawable(R.drawable.album1),8));
        }

        holder.albumName.setText(albums.get(position).getAlbumName());
        holder.albumArtist.setText(albums.get(position).getAlbumArtist());

        holder.albumName.setSelected(true);
        holder.albumArtist.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{
        private ImageView albumImage;
        private TextView albumName;
        private TextView albumArtist;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            albumImage = (ImageView)itemView.findViewById(R.id.imgAlbum);
            albumName = (TextView)itemView.findViewById(R.id.txvTitleAlbum);
            albumArtist = (TextView)itemView.findViewById(R.id.txvAlbumArtist);
        }
    }
}
