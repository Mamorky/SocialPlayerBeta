package com.example.mamorky.socialplayer.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mamorky.socialplayer.R;

import java.io.File;
import java.util.ArrayList;

import com.example.mamorky.socialplayer.data.db.pojo.Album;
import com.example.mamorky.socialplayer.data.db.repositories.dao.AlbumDao;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;
import com.example.mamorky.socialplayer.util.StyleUtils;
import com.example.mamorky.socialplayer.util.UtilsImages;
import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mamorky.socialplayer.ui.PrincipalActivity.EDITFLAG;
import static com.example.mamorky.socialplayer.ui.base.BaseActivity.baseActivity;

/**
 * Created by mamorky on 5/11/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> implements SectionTitleProvider{

    private ArrayList<Album> albums;
    private RecyclerItemClickListener listener;
    private int color;

    public AlbumAdapter(ArrayList<Album> albums, RecyclerItemClickListener listener, int color)
    {
        this.albums = albums;
        this.listener = listener;
        this.color = color;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_album,null);
        AlbumViewHolder albumViewHolder = new AlbumViewHolder(view,albums,color);
        return albumViewHolder;
    }

    @Override
    public void onBindViewHolder(final AlbumViewHolder holder, final int position){

/*
        if(albums.get(position).getAlbumImage() != null){
            RoundedBitmapDrawable imagenDrawable = UtilsImages.roundImages(Drawable.createFromPath(albums.get(position).getAlbumImage()),8);
            holder.albumImage.setImageDrawable(imagenDrawable);
        }
        else {
            holder.albumImage.setImageDrawable(UtilsImages.roundImages(BaseContext.getContext().getResources().getDrawable(R.drawable.album1),8));
        }*/

        File imgFile = null;
        try {
            imgFile = new  File(albums.get(position).getAlbumImage());
        }
        catch (Exception e){}

        if(imgFile == null)
            Picasso.with(BaseContext.getContext()).
                    load(R.drawable.ic_album_black_24dp).fit().
                    error(R.drawable.ic_album_black_24dp).
                    into(holder.albumImage);
        else
            Picasso.with(BaseContext.getContext()).
                    load(imgFile).fit().
                    error(R.drawable.ic_album_black_24dp).
                    into(holder.albumImage);

        holder.albumName.setText(albums.get(position).getAlbumName());
        holder.albumArtist.setText(albums.get(position).getAlbumArtist());

        holder.setItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView albumImage;
        private TextView albumName;
        private TextView albumArtist;

        ArrayList<Album> albumAct;
        private RecyclerItemClickListener itemClickListener;

        public void setItemClickListener(RecyclerItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(albumAct.get(this.getPosition()));
        }

        public AlbumViewHolder(View itemView, ArrayList<Album> albumAct, int color) {
            super(itemView);

            albumImage = (ImageView)itemView.findViewById(R.id.imgAlbum);
            albumName = (TextView)itemView.findViewById(R.id.txvTitleAlbum);
            albumArtist = (TextView)itemView.findViewById(R.id.txvAlbumArtist);
            this.albumAct = albumAct;

            albumImage.setBackgroundColor(color);
            albumImage.getBackground().setAlpha(99);

            itemView.setOnClickListener(this);
        }
    }

    @Override
    public String getSectionTitle(int position) {
        return albums.get(position).getAlbumName().substring(0,1);
    }

    public Album getAlbum(int pos){
        return getAlbum(pos);
    }
}
