package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;

import pojo.Album;

/**
 * Created by mamorky on 5/11/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private ArrayList<Album> albums;

    public AlbumAdapter(ArrayList<Album>albums){
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
        holder.albumImage.setImageResource(albums.get(position).getAlbumImage());
        holder.albumName.setText(albums.get(position).getAlbumName());
        holder.albumArtist.setText(albums.get(position).getAlbumArtist());
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
