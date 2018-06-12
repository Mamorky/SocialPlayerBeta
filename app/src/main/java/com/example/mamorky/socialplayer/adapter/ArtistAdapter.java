package com.example.mamorky.socialplayer.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;

import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.RecyclerItemClickListener;
import com.example.mamorky.socialplayer.util.StyleUtils;
import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import static android.content.Context.MODE_PRIVATE;
import static com.example.mamorky.socialplayer.ui.PrincipalActivity.EDITFLAG;

/**
 * Created by mamorky on 6/11/17.
 */

/**Adapter de Artist */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> implements SectionTitleProvider {

    private ArrayList<Artist> artists;
    private RecyclerItemClickListener listener;
    private int color;

    public ArtistAdapter(ArrayList<Artist> artists,RecyclerItemClickListener listener,int color){
        this.artists = artists;
        this.listener = listener;
        this.color = color;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_artist,null);
        ArtistViewHolder artistViewHolder = new ArtistViewHolder(view,artists);

        return artistViewHolder;
    }

    @Override
    public void onBindViewHolder(final ArtistViewHolder holder, final int position) {
        int tranparency = StyleUtils.getTransparencyColor(artists.get(position).getArtistName());

        int colorBackground = Color.argb(tranparency, Color.red(color), Color.green(color), Color.blue(color));

        holder.imgArtist.setShapeColor(colorBackground);
        holder.imgArtist.setShapeType(MaterialLetterIcon.Shape.CIRCLE);
        holder.imgArtist.setLetter(String.valueOf(artists.get(position).getArtistName().substring(0,1).charAt(0)));

        holder.txvArtistName.setText(artists.get(position).getArtistName());

        holder.setItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MaterialLetterIcon imgArtist;
        private TextView txvArtistName;
        private ArrayList<Artist> artists;

        private RecyclerItemClickListener itemClickListener;

        public ArtistViewHolder(View view,ArrayList<Artist> artists){
            super(view);

            imgArtist = view.findViewById(R.id.imgArtist);
            txvArtistName = view.findViewById(R.id.txvArtistName);
            this.artists = artists;

            view.setOnClickListener(this);
        }

        public void setItemClickListener(RecyclerItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(this.artists.get(this.getPosition()));
        }
    }

    @Override
    public String getSectionTitle(int position) {
        return artists.get(position).getArtistName().substring(0,1);
    }
}
